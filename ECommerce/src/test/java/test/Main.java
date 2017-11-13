package test;

import base.CommonAPI;
import dataprovider.DataProviders;
import org.testng.annotations.Test;
import pages.AccountPage;

import static org.junit.Assert.assertTrue;

public class Main extends CommonAPI {

    @Test(dataProvider = "Credentials", dataProviderClass = DataProviders.class)
    public void loginAndChangePasswordThenLogOut(String username, String password) {
        AccountPage user = new AccountPage(driver);
        user.loggedInWith(username, password);
        assertTrue("Welcome page should be displayed", user.successfulLoginMessage());
        user.changedPassword("abc123");
        assertTrue("Successfully changed should appear", user.successfullyChangedMessage());
        user.loggedOut();
        assertTrue("Sign In button should appear", user.successfulLogoutMessage());
    }
}