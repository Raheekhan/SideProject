package dataprovider;

import org.testng.annotations.DataProvider;
import utils.DataReader;

public class DataProviders {

    @DataProvider(name = "Credentials")
    public Object[][] credentials() {
        return DataReader.readExcelData(FilePaths.CREDENTIALS_FILE, "Credentials");
    }

    @DataProvider(name = "ShoppingItems")
    public Object[][] shoppingItems() {
        return DataReader.readExcelData(FilePaths.CREDENTIALS_FILE, "ShopItems");
    }
}
