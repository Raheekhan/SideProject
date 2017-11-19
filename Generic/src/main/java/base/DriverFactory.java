package base;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    /**
     * Not currently using this class, it's purpose was
     * to have it work with multiple threads by using the
     * ThreadLocal class.
     */

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
