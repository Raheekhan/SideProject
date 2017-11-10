package test;

import base.CommonAPI;
import dataprovider.DataProviders;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AccountPage;
import utils.DataReader;

import static org.junit.Assert.assertTrue;

public class Main extends CommonAPI {

    @Test(dataProvider = "Credentials", dataProviderClass = DataProviders.class)
    public void loginAndChangePasswordThenLogOut(String username, String password) {
        AccountPage loggedIn = new AccountPage(driver);
        loggedIn.with(username, password);
        assertTrue("Welcome page should be displayed", loggedIn.successfulLoginMessage());
        loggedIn.changePassword("abc123");
        assertTrue("Successfully changed should appear", loggedIn.successfullyChangedMessage());
        loggedIn.logout();
        assertTrue("Sign In button should appear", loggedIn.successfulLogoutMessage());
    }
}