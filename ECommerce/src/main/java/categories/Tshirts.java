package categories;

import base.CommonAPI;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Tshirts extends CommonAPI {

    public Tshirts(WebDriver driver) {
        this.driver = driver;
    }

    By tshirts = By.xpath("//a[@title='T-shirts']");
    By tshirtsCategoryConfirm = By.xpath("//div[@class='cat_desc']/span[contains(text(), 'T-shirts')]");

    public void tshirtsCategory() {
        waitUntilClickable(tshirts, 5);
        click(tshirts);
        test.log(Status.INFO, "Clicked On Tshirts Category");
    }

    public boolean successMsgTshirtsCategory() {
        if(isDisplayed(tshirtsCategoryConfirm, 5)) {
            test.log(Status.PASS, "Successfully went to T-shirts Category");
        } else {
            test.log(Status.FAIL, "Unable to find T-shirts Category confirmation");
        }
        return isDisplayed(tshirtsCategoryConfirm);
    }
}
