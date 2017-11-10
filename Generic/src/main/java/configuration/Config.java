package configuration;

public interface Config {
    String SAUCELABS              = "Saucelabs";
    String SAUCELABS_USERNAME     = "ibrahimk";
    String SAUCELABS_ACCESSKEY    = "d5900da9-fd61-4576-b9fc-221575b0a7fe";
    String NODEURL                = "http://192.168.0.19:5555/wd/hub";

    String FIREFOX                = "Firefox";
    String CHROME                 = "Chrome";

    String MAC                    = "mac";
    String WIN                    = "win";

    String EXTENT_REPORTS_PATH    = System.getProperty("user.dir") + "/Extent-Reports/ExtentReport.html";

    String DBURL                  = "jdbc:mysql://127.0.0.1:3306/bhphotovideo?useSSL=false";
    String DBUSER                 = "student";
    String DBPASS                 = "student";
}