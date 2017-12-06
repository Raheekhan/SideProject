package test;

import base.CommonAPI;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Main extends CommonAPI {

    /**
     * To get the desired Department & Category, please refer
     * to 'departments.properties' file which is in the
     * resources folder, to gain a better understand on Departments.
     */

    @Test(enabled = false)
    public void selectCategoryAndRefinePrice() {
        AllDepartments all = new AllDepartments(driver);
        ResultsPage resultsPage = new ResultsPage(driver);
        all.selectCategory("Electronic", "iPad");
        assertTrue("Refine is not present", resultsPage.isRefineSearchPresent());
        resultsPage.refinePrice("50", "100");
        assertFalse("Results not found", resultsPage.checkIfResultsFound());
    }

    @Description("User searches for an item and is able to refine search")
    @Story("Execute search and refine price")
    @Severity(SeverityLevel.BLOCKER)
    @Test(enabled = true)
    public void executeSearchAndRefinePrice() {
        HomePage homePage = new HomePage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);
        homePage.executeSearch("ipad");
        assertTrue("Refine is not present", resultsPage.isRefineSearchPresent());
        resultsPage.refinePrice("50", "100");
        assertFalse("Results not found", resultsPage.checkIfResultsFound());
    }

    @Test(enabled = false)
    public void checkIfListViewIsDisplaying() {
        HomePage homePage = new HomePage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);
        homePage.executeSearch("ipad");
        assertTrue("Refine is not present", resultsPage.isRefineSearchPresent());
        resultsPage.setListView();
        assertTrue("List View not present", resultsPage.isListViewPresent());
    }

    @Test(enabled = false)
    public void colorSelectionHolidaySpecials() {
        AllDepartments all = new AllDepartments(driver);
        HolidaySpecials hs = new HolidaySpecials(driver);
        all.getHolidaySpecials();
        hs.selectColor("Blue");
    }
}
