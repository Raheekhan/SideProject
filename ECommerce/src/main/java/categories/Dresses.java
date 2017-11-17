package categories;

import base.CommonAPI;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Dresses extends CommonAPI {

    public Dresses(WebDriver driver) {
        this.driver = driver;
    }

    By dresses                          = By.xpath("//a[@title='Dresses']");
    By dressesCategoryConfirm           = By.xpath("//h2[contains(text(), 'Dresses')]");

    public void dressesCategory() {
        waitUntilClickable(dresses, 5);
        click(dresses);
        test.log(Status.INFO, "Clicked On Dresses Category");
    }

    public boolean successMsgDressesCategory() {
        if(isDisplayed(dressesCategoryConfirm, 5)) {
            test.log(Status.PASS, "Successfully went to Dresses Category");
        } else {
            test.log(Status.FAIL, "Unable to find Dresses Category confirmation");
        }
        return isDisplayed(dressesCategoryConfirm);
    }
}
