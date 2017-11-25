package driverfactory;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class FirefoxDriverManager extends DriverManager {

    private GeckoDriverService ffService;
    private String driverPath = "../Generic/drivers/macdriver/geckodriver";

    @Override
    public void startService() {
        if (null == ffService) {
            try {
                ffService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(new File(driverPath))
                        .usingAnyFreePort()
                        .build();
                ffService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != ffService && ffService.isRunning()) {
            ffService.stop();
        }
    }

    @Override
    public void createDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("test-type");
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
        driver = new FirefoxDriver(ffService, capabilities);
    }
}
