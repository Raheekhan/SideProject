package testng;

import base.CommonAPI;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExcelUtil;

import java.io.File;
import java.io.IOException;

public class TestListener extends CommonAPI implements ITestListener {

    /**
     * The ExcelUtil methods can be used as seen below if the framework
     * is DataDriven, given you the advantage of not having to worry about
     * manually setting statuses to 'PASSED', 'FAILED' or 'SKIPPED' every time.
     *
     * The only thing user have to worry about is in the Tests, writing:
     *  eg. ExcelUtil.setRowNumber(1) & ExcelUtil.setColumnNumber(5).
     *
     *  This indicated that you want to set a status to Column Number
     *  5 in Row Number 1, if the Data in the Excel File has to do with
     *  the first row.
     *
     *  If user wants to set status to second row, simply change from
     *  ExcelUtil.setRowNumber(1) to ExcelUtil.setRowNumber(2).
     *  setColumnNumber usually remains same as it ends as the same
     *  Column.
     */

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        ExcelUtil.setCellData("PASSED", ExcelUtil.getRowNumber(), ExcelUtil.getColumnNumber());
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        ExcelUtil.setCellData("FAILED", ExcelUtil.getRowNumber(), ExcelUtil.getColumnNumber());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
//        ExcelUtil.setCellData("SKIPPED", ExcelUtil.getRowNumber(), ExcelUtil.getColumnNumber());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
