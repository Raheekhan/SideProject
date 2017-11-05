package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import configuration.Config;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonAPI implements Config {

    private static ExtentReports extent;
    public static ExtentTest test;

    public WebDriver driver = null;

    public JavascriptExecutor jse = (JavascriptExecutor) driver;

    @BeforeSuite
    protected void startExtentReporting() {
        extent = ExtentManager.createInstance();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(EXTENT_REPORTS_PATH);
        extent.attachReporter(htmlReporter);
    }

    @Parameters({"useHeadlessEnv", "useGridEnv", "useLocalEnv", "useCloudEnv", "cloudEnvName", "platform", "platformVersion", "browserName", "browserVersion" , "url"})
    @BeforeMethod
    protected void setUp(@Optional boolean useHeadlessEnv, @Optional boolean useGridEnv, @Optional boolean useLocalEnv,
                         @Optional boolean useCloudEnv, @Optional String cloudEnvName, @Optional String platform, @Optional String platformVersion,
                         @Optional String browserName, @Optional String browserVersion, @Optional String url, @Optional Method method) {

        test = extent.createTest(method.getName());

        if (useCloudEnv == true) {
            if (cloudEnvName.equalsIgnoreCase(SAUCELABS)) {
                getCloudDriver(cloudEnvName, SAUCELABS_USERNAME, SAUCELABS_ACCESSKEY, platform, platformVersion, browserName, browserVersion);
            } else {
                test.log(Status.FAIL, "Invalid Choice Of Cloud Environment.");
            }
        } else if (useLocalEnv == true) {
            getLocalDriver(platform, browserName);
        } else if (useGridEnv == true) {
            getGridDriver(platform, browserName);
        } else if (useHeadlessEnv == true) {
            getHeadlessDriver();
        }
        driver.manage().window().maximize();
        test.log(Status.INFO, "Browser Maximized.");
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.navigate().to(url);
        test.log(Status.INFO, "Navigated To URL.");
    }

    protected WebDriver getLocalDriver(String platform, String browserName) {

        // Must be in similar fashion 'Mac OS X', in the TestNG.xml for each Module.
        // As System.getPropery("os.name") Method grabs the actual OS name from the Machine.
        if (OS_NAME.contains(platform)) {
            if (browserName.equalsIgnoreCase(FIREFOX)) {
                System.setProperty("webdriver.gecko.driver", "../Generic/macdriver/geckodriver");
                driver = new FirefoxDriver();
                test.log(Status.INFO, "Environment: 'LOCAL', Firefox Driver For Mac Executed.");
            } else if (browserName.equalsIgnoreCase(CHROME)) {
                System.setProperty("webdriver.chrome.driver", "../Generic/macdriver/chromedriver");
                driver = new ChromeDriver();
                test.log(Status.INFO, "Environment: 'LOCAL', Chrome Driver For Mac Executed.");
            } else {
                System.err.println("ERROR: Choose from: Firefox/Chrome.");
                test.log(Status.FAIL, "Invalid Choice Of Driver.");
            }
            // Must be in similar fashion 'Windows', in the TestNG.xml for each Module
            // As System.getPropery("os.name") Method grabs the actual OS name from the Machine.
        } else if (OS_NAME.contains(platform)) {
            if (browserName.equalsIgnoreCase(FIREFOX)) {
                System.setProperty("webdriver.gecko.driver", "../Generic/driver/geckodriver.exe");
                driver = new FirefoxDriver();
                test.log(Status.INFO, "Environment: 'LOCAL', Firefox Driver For Windows Executed.");
            } else if (browserName.equalsIgnoreCase(CHROME)) {
                System.setProperty("webdriver.chrome.driver", "../Generic/driver/chromedriver.exe");
                driver = new ChromeDriver();
                test.log(Status.INFO, "Environment: 'LOCAL', Chrome Driver For Windows Executed.");
            } else {
                System.err.println("ERROR: Choose from: Firefox/Chrome/IE/Opera.");
                test.log(Status.FAIL, "Invalid Choice Of Driver.");
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

        if(cloudEnvName.equalsIgnoreCase(SAUCELABS)) {
            try {
                driver = new RemoteWebDriver(new URL
                        ("http://" + envUsername + ":" + envAccessKey + "@ondemand.saucelabs.com:80/wd/hub"), cap);
                test.log(Status.INFO, "Environment: 'REMOTE', Remote Web Driver Launched With Saucelabs.");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public WebDriver getHeadlessDriver() {

        return driver;
    }

    protected WebDriver getGridDriver(String platform, String browserName) {
        DesiredCapabilities cap = new DesiredCapabilities();

        if(platform.contains(MAC)) {
            cap.setPlatform(Platform.MAC.family());
            test.log(Status.INFO, "Environment: 'GRID', Running Grid On Mac Configuration");
            if(browserName.equalsIgnoreCase(CHROME)) {
                cap.setBrowserName(browserName);
                try {
                    driver = new RemoteWebDriver(new URL(NODEURL), cap);
                    test.log(Status.INFO, "Environment: 'GRID', Launching Chrome Browser For Grid");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            if (browserName.equalsIgnoreCase(FIREFOX)) {
                cap.setBrowserName(browserName);
                try {
                    driver = new RemoteWebDriver(new URL(NODEURL), cap);
                    test.log(Status.INFO, "Environment: 'GRID', Launching Firefox Browser For Grid");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }

        if(platform.contains(WINDOWS)) {
            cap.setPlatform(Platform.WINDOWS.family());
            test.log(Status.INFO, "Environment: 'GRID', Running Grid On Windows Configuration");
            if(browserName.equalsIgnoreCase(CHROME)) {
                cap.setBrowserName("chrome");
                try {
                    driver = new RemoteWebDriver(new URL(NODEURL), cap);
                    test.log(Status.INFO, "Environment: 'GRID', Launching Chrome Browser For Grid");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            if(browserName.equalsIgnoreCase(FIREFOX)) {
                cap.setBrowserName("firefox");
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
            test.fail("Screenshot: ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            path = captureScreenshot(driver, result.getName());
            test.skip("Screenshot: ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            test.skip(result.getThrowable());
        }
        driver.quit();
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
        File screenshotFile = new File(System.getProperty("user.dir") + "/screenshots/" + screenshotName + " " + dateFormat.format(date) + ".png");
        try {
            FileUtils.copyFile(file, screenshotFile);
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            e.printStackTrace();
        }
        return screenshotFile.getName();
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

    public void mouseScroll(int x, int y) {
        String script = "window.scrollBy(" + x + "," + y + ")";
        jse.executeScript(script);
    }

    public void testFluentWait(WebDriver driver, long time, WebElement ele) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(time, TimeUnit.SECONDS)
                .pollingEvery(3, TimeUnit.SECONDS);
//        WebElement element =
//        wait.until(driver1 -> driver1.findElement(By.id(ele)));
    }

    public void waitUntilClickable(WebElement element, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilSelectable(WebElement element, long time) {
        new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeSelected(element));
    }

    public void textToBePresentInElement(WebElement element, long time, String input) {
        new WebDriverWait(driver, time).until(ExpectedConditions.textToBePresentInElement(element, input));
    }

    public void checkIfElementIsEmpty(WebElement element, long time) {
        new WebDriverWait(driver, time).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, "")));
    }

    public void enterInput(String element, String input) {
        driver.findElement(By.id(element)).sendKeys(input);
    }

    public void clearInput(String element) {
        driver.findElement(By.id(element)).clear();
    }
}