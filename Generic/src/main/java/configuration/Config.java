package configuration;

public interface Config {

    /**
     * All Credentials are temporarily
     * put here to avoid dealing with Properties
     */

    String FIREFOX                = "firefox";
    String CHROME                 = "chrome";

    String MAC                    = "mac";
    String WIN                    = "win";

    String EXTENT_REPORTS_PATH    = System.getProperty("user.dir") + "/reports/ExtentReport.html";

    String DBURL                  = "jdbc:mysql://127.0.0.1:3306/bhphotovideo?useSSL=false";
    String DBUSER                 = "student";
    String DBPASS                 = "student";

    /**
     * WebDriver Paths
     */

    String GECKODRIVER          = "../Generic/drivers/macdriver/geckodriver";
    String GECKODRIVER_EXE      = "../Generic/drivers/windowsdriver/geckodriver.exe";
    String CHROMEDRIVER         = "../Generic/drivers/macdriver/chromedriver";
    String CHROMEDRIVER_EXE     = "../Generic/drivers/windowsdriver/chromedriver.exe";

    String PHANTOMJS            = "../Generic/drivers/headlessdriver/phantomjs";
    String PHANTOMJS_EXE        = "../Generic/drivers/headlessdriver/phantomjs.exe";

    String GECKODRIVER_PATH     = "webdriver.gecko.driver";
    String CHROMEDRIVER_PATH    = "webdriver.chrome.driver";
    String PHANTOMJS_PATH       = "phantomjs.binary.path";
}