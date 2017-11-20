package test;

import base.CommonAPI;
import org.testng.annotations.Test;
import pages.AllDepartments;
import pages.HomePage;
import pages.ResultsPage;

import static org.junit.Assert.assertTrue;

public class Main extends CommonAPI {

    /**
     * To get the desired Department & Category, please refer
     * to 'Departments.properties' file which is in the
     * resources folder, to gain a better understand on Departments.
     */

    @Test(enabled = false)
    public void selectCategoryAndRefinePrice() {
        AllDepartments all = new AllDepartments(driver);
        ResultsPage resultsPage = new ResultsPage(driver);
        all.selectCategory("Electronic", "iPad");
        assertTrue("Refine is not present", resultsPage.isRefineSearchPresent());
        resultsPage.refinePrice("50", "100");
        resultsPage.checkIfResultsFound();
    }

    @Test(enabled = false)
    public void executeSearchAndRefinePrice() {
        HomePage homePage = new HomePage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);
        homePage.executeSearch("ipad");
        assertTrue("Refine is not present", resultsPage.isRefineSearchPresent());
        resultsPage.refinePrice("50", "100");
        resultsPage.checkIfResultsFound();
    }

    @Test
    public void checkIfListViewIsDisplaying() {
        HomePage homePage = new HomePage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);
        homePage.executeSearch("ipad");
        assertTrue("Refine is not present", resultsPage.isRefineSearchPresent());
        resultsPage.setListView();
        assertTrue("List View not present", resultsPage.isListViewPresent());
    }
}
