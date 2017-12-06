package test;

import base.CommonAPI;
import pages.TestWithJS;

public class Test extends CommonAPI {

    @org.testng.annotations.Test
    public void testOne() {
        new TestWithJS(driver).test();
    }
}
