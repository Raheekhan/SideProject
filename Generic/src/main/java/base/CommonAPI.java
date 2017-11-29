package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import configuration.Config;
import driverfactory.DriverFactory;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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

public class CommonAPI implements Config {

    private static ExtentReports extent;
    public static ExtentTest test;

    public static WebDriver driver;

    @BeforeSuite
    protected void startExtentReporting() {
        extent = ExtentManager.createInstance();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(EXTENT_REPORTS_PATH);
        extent.attachReporter(htmlReporter);
    }

    @Parameters({"useHeadlessEnv", "useGridEnv", "nodeURL", "useLocalEnv", "useCloudEnv",
            "cloudEnvName", "platform", "platformVersion", "browserName", "browserVersion",
            "url", "sauceUser", "saucePass"})
    @BeforeMethod
    protected void setUp(@Optional boolean useHeadlessEnv, @Optional boolean useGridEnv, @Optional String nodeURL,
                         @Optional boolean useLocalEnv, @Optional boolean useCloudEnv, @Optional String cloudEnvName,
                         @Optional String platform, @Optional String platformVersion, @Optional String browserName,
                         @Optional String browserVersion, @Optional String url, @Optional Method method,
                         @Optional String sauceUser, @Optional String saucePass) {

        test = extent.createTest(method.getName());

        if (useCloudEnv) {
            if (cloudEnvName.equalsIgnoreCase("Saucelabs")) {
                getCloudDriver(cloudEnvName, sauceUser, saucePass,
                        platform, platformVersion, browserName, browserVersion);
            } else {
                test.log(Status.FAIL, "Invalid Choice Of Cloud Environment");
            }
        } else if (useLocalEnv) {
            //getLocalDriver(platform, browserName);
            getLocalDriver(platform, browserName);
        } else if (useGridEnv) {
            getGridDriver(platform, browserName, nodeURL);
        }
//        else if (useHeadlessEnv) {
//            getHeadlessDriver(platform);
//        }
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

    public WebDriver getLocalDriver(String platform, String browserName) {
        if (platform.contains("mac")) {
            if (browserName.equalsIgnoreCase("Chrome")) {
                DriverFactory.getChromeDriverOnMac();
            } else if (browserName.equalsIgnoreCase("Firefox")) {
                DriverFactory.getFirefoxDriverOnMac();
            }
        } else if (platform.contains("win")) {
            if (browserName.equalsIgnoreCase("Chrome")) {
                DriverFactory.getChromeDriverOnWin();
            } else if (browserName.equalsIgnoreCase("Firefox")) {
                DriverFactory.getFirefoxDriverOnWin();
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

        if(cloudEnvName.equalsIgnoreCase("Saucelabs")) {
            try {
                driver = new RemoteWebDriver(new URL(sauceURL), cap);
                test.log(Status.INFO, "Environment: 'CLOUD', Remote Web Driver Launched With Saucelabs");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

//    public WebDriver getHeadlessDriver(String platform) {
//
//        DesiredCapabilities cap = new DesiredCapabilities();
//        cap.setJavascriptEnabled(true); // * Ignoring Web Security
//        cap.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {"--web-security=no", "--ignore-ssl-errors=yes"});
//        // Setting Capabilities to ignore HTTPS and go with HTTP, al though less secure, PhantomJS
//        // sometimes fails with HTTPS Requests. *
//
//        if(platform.contains(MAC)) {
//            System.setProperty(PHANTOMJS_PATH, PHANTOMJS);
//            driver = new PhantomJSDriver(cap);
//            test.log(Status.INFO, "Environment: 'GHOST', Launched PhantonJS Driver For Mac");
//        } else if (platform.contains(WIN)) {
//            System.setProperty(PHANTOMJS_PATH, PHANTOMJS_EXE);
//            driver = new PhantomJSDriver(cap);
//            test.log(Status.INFO, "Environment: 'GHOST', Launched PhantonJS Driver for Windows");
//        }
//        return driver;
//    }

    protected WebDriver getGridDriver(String platform, String browserName, String nodeURL) {
        DesiredCapabilities cap = new DesiredCapabilities();

        if (platform.contains(MAC)) {
            cap.setPlatform(Platform.MAC.family());
            test.log(Status.INFO, "Environment: 'GRID', Running Grid On Mac Configuration");
            if (browserName.equalsIgnoreCase(CHROME)) {
                cap.setBrowserName(browserName);
                try {
                    driver = new RemoteWebDriver(new URL(nodeURL), cap);
                    test.log(Status.INFO, "Environment: 'GRID', Launching Chrome Browser For Grid");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else if (browserName.equalsIgnoreCase(FIREFOX)) {
                cap.setBrowserName(browserName);
                try {
                    driver = new RemoteWebDriver(new URL(nodeURL), cap);
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
                    driver = new RemoteWebDriver(new URL(nodeURL), cap);
                    test.log(Status.INFO, "Environment: 'GRID', Launching Chrome Browser For Grid");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else if (browserName.equalsIgnoreCase(FIREFOX)) {
                cap.setBrowserName(browserName);
                try {
                    driver = new RemoteWebDriver(new URL(nodeURL), cap);
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
        File screenshotFile = new File(System.getProperty("user.dir") +
                "/screenshots/" + screenshotName + " " + dateFormat.format(date) + ".png");
        try {
            FileUtils.copyFile(file, screenshotFile);
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            e.printStackTrace();
        }
        return screenshotName;
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

    public static WebElement getElement(String locator) {
        boolean flag = false;
        if (locator.contains("/")) flag = true;
        if (driver.findElements(By.id(locator)).size() == 1) {
            return driver.findElement(By.id(locator));
        } else if (driver.findElements(By.name(locator)).size() == 1) {
            return driver.findElement(By.name(locator));
        } else if (!flag && driver.findElements(By.cssSelector(locator)).size() == 1) {
            return driver.findElement(By.cssSelector(locator));
        } else if (driver.findElements(By.xpath(locator)).size() == 1) {
            return driver.findElement(By.xpath(locator));
        } else {
            throw new NoSuchElementException("No Such Element: " + locator);
        }
    }
}