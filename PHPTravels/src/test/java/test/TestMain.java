package test;

import base.CommonAPI;

import dataprovider.DataProviders;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.DataReader;

import static org.junit.Assert.assertTrue;

public class TestMain extends CommonAPI {

    @Test(dataProvider = "Test", dataProviderClass = DataProviders.class)
    public void testFlightsDeal(String orig, String dest, String departdate, String returndate, String adults) throws Exception {
        HomePage hp = new HomePage(driver);
        hp.flightDeals(orig, dest, departdate, returndate, adults);
    }
}
