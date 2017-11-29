package driverfactory;

import base.CommonAPI;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory extends CommonAPI {

    public static void getChromeDriverOnMac() {
        System.setProperty("webdriver.chrome.driver", "../Generic/drivers/macdriver/chromedriver");
        driver = new ChromeDriver();
    }

    public static void getChromeDriverOnWin() {
        System.setProperty("webdriver.chrome.driver", "../Generic/drivers/macdriver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    public static void getFirefoxDriverOnMac() {
        System.setProperty("webdriver.gecko.driver", "../Generic/drivers/windowsdriver/geckodriver");
        driver = new ChromeDriver();
    }

    public static void getFirefoxDriverOnWin() {
        System.setProperty("webdriver.gecko.driver", "../Generic/drivers/windowsdriver/geckodriver.exe");
        driver = new ChromeDriver();
    }
}
