package dataprovider;

import org.testng.annotations.DataProvider;
import utils.DataReader;

public class DataProviders {

    @DataProvider(name = "Test")
    public Object[][] middleMan() throws Exception {
        return DataReader.readExcelData(FilePaths.EXPEDIA_SEARCH, "Sheet1");
    }
}
