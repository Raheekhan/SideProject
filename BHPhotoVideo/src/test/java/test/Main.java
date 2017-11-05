package test;

import base.CommonAPI;
import homepage.TestVerification;
import org.testng.annotations.Test;

public class Main extends CommonAPI {

    @Test(enabled = true)
    public void testMethod() {
        new TestVerification(driver).registerAccount();
    }

    @Test(enabled = false)
    public void testSearchItems() {
        new TestVerification(driver).searchingItems();
    }
}