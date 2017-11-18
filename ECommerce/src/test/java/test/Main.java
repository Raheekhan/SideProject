package test;

import base.CommonAPI;
import categories.Women;
import dataprovider.DataProviders;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.Checkout;

import static org.junit.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class Main extends CommonAPI {

    @Test(dataProvider = "Credentials", dataProviderClass = DataProviders.class)
    public void loginAndChangePasswordThenLogOut(String username, String password) {
        AccountPage user = new AccountPage(driver);
        assertEquals("My Store", driver.getTitle());
        user.loggedInWith(username, password);
        assertTrue("Welcome page should be displayed", user.successfulLoginMessage());
        user.changedPassword("abc123");
        assertTrue("Successfully changed should appear", user.successfullyChangedMessage());
        user.loggedOut();
        assertTrue("Sign In button should appear", user.successfulLogoutMessage());
    }

    /**
     * The DataProvider calls on an Excel file which contains Two Items
     * so the test case will be executed twice
     * @param item
     */

    @Test(dataProvider = "ShoppingItems", dataProviderClass = DataProviders.class)
    public void shopFromWomenCategoryAndCheckOut(String item) {
        AccountPage user = new AccountPage(driver);
        Women women = new Women(driver);
        Checkout cart = new Checkout(driver);
        assertEquals("My Store", driver.getTitle());
        user.loggedInWith("ibra@live.se", "abc123");
        assertTrue("Welcome page should be displayed", user.successfulLoginMessage());
        women.womenCategory();
        assertTrue("Header Women should be displayed", women.successMsgWomenCategory());
        women.selectItem(item);
        cart.bankwirePayment();
        assertTrue("Order Confirmation should appear", cart.successMessageOrderConfirmation());
    }
}