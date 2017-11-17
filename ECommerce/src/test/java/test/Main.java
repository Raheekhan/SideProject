package test;

import base.CommonAPI;
import categories.Women;
import dataprovider.DataProviders;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.Checkout;

import static org.junit.Assert.assertTrue;

public class Main extends CommonAPI {

    @Test(enabled = false, dataProvider = "Credentials", dataProviderClass = DataProviders.class)
    public void loginAndChangePasswordThenLogOut(String username, String password) {
        AccountPage user = new AccountPage(driver);
        user.loggedInWith(username, password);
        assertTrue("Welcome page should be displayed", user.successfulLoginMessage());
        user.changedPassword("abc123");
        assertTrue("Successfully changed should appear", user.successfullyChangedMessage());
        user.loggedOut();
        assertTrue("Sign In button should appear", user.successfulLogoutMessage());
    }

    /**
     * ITEMS: Faded Short Sleeve T-shirts, Blouse
     */

    @Test
    public void shopFromWomenCategoryAndCheckOut() {
        AccountPage user = new AccountPage(driver);
        Women women = new Women(driver);
        Checkout cart = new Checkout(driver);
        user.loggedInWith("ibra@live.se", "abc123");
        assertTrue("Welcome page should be displayed", user.successfulLoginMessage());
        women.womenCategory();
        assertTrue("Header Women should be displayed", women.successMsgWomenCategory());
        women.selectItem("Blouse");
        cart.addToCart();
    }
}