package test;

import base.CommonAPI;
import homepage.TestVerification;
import org.testng.annotations.Test;

public class Main extends CommonAPI {

    @Test(enabled = false)
    public void testMethod() {
        new TestVerification(driver).registerAccount();
    }

    @Test(enabled = true)
    public void testMethodTwo() {
        new TestVerification(driver).methodTwo("Laptop");
    }
}