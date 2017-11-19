package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import configuration.Config;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class CommonAPI implements Config {

    private static ExtentReports extent;
    public static ExtentTest test;

    public WebDriver driver;

    public JavascriptExecutor jse = (JavascriptExecutor) driver;

    @BeforeSuite
    protected void startExtentReporting() {
        extent = ExtentManager.createInstance();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(EXTENT_REPORTS_PATH);
        extent.attachReporter(htmlReporter);
    }

    @Parameters({"useHeadlessEnv", "useGridEnv", "useLocalEnv", "useCloudEnv",
            "cloudEnvName", "platform", "platformVersion", "browserName", "browserVersion" , "url"})
    @BeforeMethod
    protected void setUp(@Optional boolean useHeadlessEnv, @Optional boolean useGridEnv,
                         @Optional boolean useLocalEnv, @Optional boolean useCloudEnv, @Optional String cloudEnvName,
                         @Optional String platform, @Optional String platformVersion, @Optional String browserName,
                         @Optional String browserVersion, @Optional String url, @Optional Method method) {

        test = extent.createTest(method.getName());

        if (useCloudEnv) {
            if (cloudEnvName.equalsIgnoreCase(SAUCELABS)) {
                getCloudDriver(cloudEnvName, SAUCELABS_USERNAME, SAUCELABS_ACCESSKEY,
                        platform, platformVersion, browserName, browserVersion);
            } else {
                test.log(Status.FAIL, "Invalid Choice Of Cloud Environment");
            }
        } else if (useLocalEnv) {
            getLocalDriver(platform, browserName);
        } else if (useGridEnv) {
            getGridDriver(platform, browserName);
        } else if (useHeadlessEnv) {
            getHeadlessDriver(platform);
        }
        driver.manage().window().maximize();
        test.log(Status.INFO, "Browser Maximized");
        driver.navigate().to(url);
        test.log(Status.INFO, "Navigated To " + driver.getCurrentUrl());
    }

//    private WebDriver getProxy(String url) {
//
//        /**
//         * Just a Protocol, It is not set up to work right now
//         */
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        BrowserMobProxy proxy = getProxyServer(); //getting browsermob proxy
//        Proxy seleniumProxy = getSeleniumProxy(proxy);
//        seleniumProxy.setHttpProxy("localhost:8080");
//        seleniumProxy.setSslProxy("trustAllSSLCertificates");
//        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
//        driver = new ChromeDriver(capabilities);
//        proxy.newHar(); // creating new HAR
//        driver.manage().window().maximize();
//        test.log(Status.INFO, "Browser Maximized");
//        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.navigate().to(url);
//        test.log(Status.INFO, "Navigated To URL");
//        List<HarEntry> entries = proxy.getHar().getLog().getEntries();
//        for (HarEntry entry : entries) {
//            System.out.println(entry.getRequest().getUrl());
//        }
//
//        Har har = proxy.newHar();
//        File file = new File(System.getProperty("user.dir") + "/Results.har");
//        FileOutputStream fos;
//        try {
//            fos = new FileOutputStream(file);
//            har.writeTo(fos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        proxy.stop();
//        return driver;
//    }

    protected WebDriver getLocalDriver(String platform, String browserName) {

        if (platform.contains(MAC)) {
            if (browserName.equalsIgnoreCase(FIREFOX)) {
                System.setProperty(GECKODRIVER_PATH, GECKODRIVER);
                driver = new FirefoxDriver();
                test.log(Status.INFO, "Environment: 'LOCAL', Firefox Driver For Mac Executed");
            } else if (browserName.equalsIgnoreCase(CHROME)) {
                System.setProperty(CHROMEDRIVER_PATH, CHROMEDRIVER);
                driver = new ChromeDriver();
                test.log(Status.INFO, "Environment: 'LOCAL', Chrome Driver For Mac Executed");
            } else {
                System.err.println("ERROR: Choose from: Firefox/Chrome");
                test.log(Status.FAIL, "Invalid Choice Of Driver");
            }
        } else if (platform.contains(WIN)) {
            if (browserName.equalsIgnoreCase(FIREFOX)) {
                System.setProperty(GECKODRIVER_PATH, GECKODRIVER_EXE);
                driver = new FirefoxDriver();
                test.log(Status.INFO, "Environment: 'LOCAL', Firefox Driver For Windows Executed");
            } else if (browserName.equalsIgnoreCase(CHROME)) {
                System.setProperty(CHROMEDRIVER_PATH, CHROMEDRIVER_EXE);
                driver = new ChromeDriver();
                test.log(Status.INFO, "Environment: 'LOCAL', Chrome Driver For Windows Executed");
            } else {
                System.err.println("ERROR: Choose from: Firefox/Chrome");
                test.log(Status.FAIL, "Invalid Choice Of Driver");
            }
        }
        return driver;
    }

    protected WebDriver getCloudDriver(String cloudEnvName, String envUsername, String envAccessKey, String platform,
                                       String platformVersion, String browserName, String browserVersion) {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browserName", browserName);
        cap.setCapability("browserVersion", browserVersion);
        cap.setCapability("platform", platform);
        cap.setCapability("platformVersion", platformVersion);

        String sauceURL = String.format("http://%s:%s@ondemand.saucelabs.com:80/wd/hub", envUsername, envAccessKey);

        if(cloudEnvName.equalsIgnoreCase(SAUCELABS)) {
            try {
                driver = new RemoteWebDriver(new URL(sauceURL), cap);
                test.log(Status.INFO, "Environment: 'CLOUD', Remote Web Driver Launched With Saucelabs");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public WebDriver getHeadlessDriver(String platform) {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setJavascriptEnabled(true); // * Ignoring Web Security
        cap.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {"--web-security=no", "--ignore-ssl-errors=yes"});
        // Setting Capabilities to ignore HTTPS and go with HTTP, al though less secure, PhantomJS
        // sometimes fails with HTTPS Requests. *

        if(platform.contains(MAC)) {
            System.setProperty(PHANTOMJS_PATH, PHANTOMJS);
            driver = new PhantomJSDriver(cap);
            test.log(Status.INFO, "Environment: 'GHOST', Launched PhantonJS Driver For Mac");
        } else if (platform.contains(WIN)) {
            System.setProperty(PHANTOMJS_PATH, PHANTOMJS_EXE);
            driver = new PhantomJSDriver(cap);
            test.log(Status.INFO, "Environment: 'GHOST', Launched PhantonJS Driver for Windows");
        }
        return driver;
    }

    protected WebDriver getGridDriver(String platform, String browserName) {
        DesiredCapabilities cap = new DesiredCapabilities();

        if (platform.contains(MAC)) {
            cap.setPlatform(Platform.MAC.family());
            test.log(Status.INFO, "Environment: 'GRID', Running Grid On Mac Configuration");
            if (browserName.equalsIgnoreCase(CHROME)) {
                cap.setBrowserName(browserName);
                try {
                    driver = new RemoteWebDriver(new URL(NODEURL), cap);
                    test.log(Status.INFO, "Environment: 'GRID', Launching Chrome Browser For Grid");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else if (browserName.equalsIgnoreCase(FIREFOX)) {
                cap.setBrowserName(browserName);
                try {
                    driver = new RemoteWebDriver(new URL(NODEURL), cap);
                    test.log(Status.INFO, "Environment: 'GRID', Launching Firefox Browser For Grid");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }

        if (platform.contains(WIN)) {
            cap.setPlatform(Platform.WINDOWS.family());
            test.log(Status.INFO, "Environment: 'GRID', Running Grid On Windows Configuration");
            if (browserName.equalsIgnoreCase(CHROME)) {
                cap.setBrowserName(browserName);
                try {
                    driver = new RemoteWebDriver(new URL(NODEURL), cap);
                    test.log(Status.INFO, "Environment: 'GRID', Launching Chrome Browser For Grid");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else if (browserName.equalsIgnoreCase(FIREFOX)) {
                cap.setBrowserName(browserName);
                try {
                    driver = new RemoteWebDriver(new URL(NODEURL), cap);
                    test.log(Status.INFO, "Environment: 'GRID', Launching Firefox Browser For Grid");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDown(ITestResult result) throws IOException {
        String path;

        if (result.getStatus() == ITestResult.FAILURE) {
            path = captureScreenshot(driver, result.getName());
            test.log(Status.FAIL, "Screenshot: ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            path = captureScreenshot(driver, result.getName());
            test.log(Status.SKIP, "Screenshot: ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            test.skip(result.getThrowable());
        }
        driver.quit();
        test.log(Status.INFO, "Quitting Driver");
    }

    @AfterSuite
    protected void EndExtentReporting() {
        extent.flush();
    }

    public String captureScreenshot(WebDriver driver, String screenshotName) {

        DateFormat dateFormat = new SimpleDateFormat("(MM.dd.yyyy-HH:mma)");
        Date date = new Date();
        dateFormat.format(date);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        File screenshotFile = new File(System.getProperty("user.dir") +
//                "/screenshots/" + screenshotName + " " + dateFormat.format(date) + ".png");
        String destination = System.getProperty("user.dir") +
                "/screenshots/" + screenshotName + " " + dateFormat.format(date) + ".png";
        File screenshotFile = new File(destination);
        try {
            FileUtils.copyFile(file, screenshotFile);
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            e.printStackTrace();
        }
        return destination;
    }

    public String randomUsernameGenerator() {
        String username = RandomStringUtils.randomAlphabetic(8);
        return username;
    }

    public String randomPasswordGenerator() {
        String password = RandomStringUtils.randomAlphanumeric(4) + RandomStringUtils.randomAlphabetic(4);
        return password;
    }

    public String randomEmailGenerator() {
        String username = RandomStringUtils.randomAlphabetic(8) + "@gmail.com";
        return username;
    }

    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public void click(By locator) {
        find(locator).click();
    }

    public void clear(By locator) {
        find(locator).clear();
    }

    public void type(String inputText, By locator) {
        find(locator).sendKeys(inputText);
    }

    public boolean isSelected(By locator) {
        try {
            return find(locator).isSelected();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isSelected(By locator, Integer timeout) {
        try {
            new WebDriverWait(driver, timeout)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public boolean isDisplayed(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isDisplayed(By locator, Integer timeout) {
        try {
            new WebDriverWait(driver, timeout)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public Proxy getSeleniumProxy(BrowserMobProxy proxyServer) {
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyServer);
        try {
            String hostIp = Inet4Address.getLocalHost().getHostAddress();
            seleniumProxy.setHttpProxy(hostIp + ":" + proxyServer.getPort());
            seleniumProxy.setSslProxy(hostIp + ":" + proxyServer.getPort());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Assert.fail("invalid Host Address");
        }
        return seleniumProxy;
    }

    public BrowserMobProxy getProxyServer() {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true);
        proxy.start();
        return proxy;
    }

    public void findIfLinksAreBroken() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total links in " + driver.getCurrentUrl() + " are: " + links.size());
        System.out.println("==================================================================");
        for(int i = 0; i < links.size(); i++) {
            WebElement element = links.get(i);
            String url = element.getAttribute("href");
            verifyLinkActive(url);
        }
    }

    public static void verifyLinkActive(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
            httpURLConnect.setConnectTimeout(3000);
            httpURLConnect.connect();
            if (httpURLConnect.getResponseCode() == 200) {
                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
            }
            if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - " + HttpURLConnection.HTTP_NOT_FOUND);
            }
        } catch (Exception e) {

        }
    }
}