package test;

import base.CommonAPI;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.HomePage;

import static org.junit.Assert.assertTrue;

public class Main extends CommonAPI {

    @Test
    public void login() {
        HomePage login = new HomePage(driver);
        login.with("ibra@live.se", "abc123");
        assertTrue("Welcome page should be displayed", login.successfulLoginMessage());
    }
}
