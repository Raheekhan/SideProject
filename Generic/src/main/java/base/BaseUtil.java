package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import driverfactory.DriverManager;
import driverfactory.DriverManagerFactory;
import driverfactory.DriverType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static configuration.Config.EXTENT_REPORTS_PATH;

public class BaseUtil {

    private DriverManager driverManager;
    public WebDriver driver;

    private static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    protected void startExtentReporting() {
        extent = ExtentManager.createInstance();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(EXTENT_REPORTS_PATH);
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    @Parameters({"useLocalEnv", "browserName", "url"})
    public void beforeMethod(boolean useLocalEnv, String browserName, String url, @Optional Method method) {

        test = extent.createTest(method.getName());

        if (useLocalEnv) {
            getLocalDriver(browserName);
        }

        driver.manage().window().maximize();
        driver.navigate().to(url);
        test.log(Status.INFO, "Navigated To " + driver.getCurrentUrl());
    }

    public void getLocalDriver(String browserName) {
        if (browserName.equalsIgnoreCase("Chrome")) {
            driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
            driver = driverManager.getDriver();
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            driverManager = DriverManagerFactory.getManager(DriverType.FIREFOX);
            driver = driverManager.getDriver();
        } else {
            System.out.println("Invalid choice of browser");
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getName());
        }
        driverManager.quitDriver();
    }

    @AfterSuite
    protected void EndExtentReporting() {
        extent.flush();
    }

    public String captureScreenshot(String screenshotName) {

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
}
