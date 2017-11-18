package base;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private static DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance() {
        return instance;
    }

    ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    public void removeDriver() {
        driver.get().quit();
        driver.remove();
    }
}
