package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import configuration.Config;

public class ExtentManager implements Config {

    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(EXTENT_REPORTS_PATH);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(EXTENT_REPORTS_PATH);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(EXTENT_REPORTS_PATH);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }
}
