package test;

import base.CommonAPI;
import homepage.TestVerification;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class Main extends CommonAPI {

    @Test(enabled = true)
    public void testMethod() {
        new TestVerification(driver).registerAccount();
    }
}