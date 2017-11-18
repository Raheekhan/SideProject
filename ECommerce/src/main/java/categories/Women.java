package categories;

import base.CommonAPI;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Women extends CommonAPI {

    public Women(WebDriver driver) {
        this.driver = driver;
    }

    By women                    = By.xpath("//a[@title='Women']");
    By womenCategoryConfirm     = By.xpath("//h2[contains(text(), 'Women')]");

    public void womenCategory() {
        waitUntilClickable(women, 5);
        click(women);
        test.log(Status.INFO, "Clicked On Women Category");
    }

    public void selectItem(String item) {
        By shopItem = By.xpath("//a[@title='" + item + "']");
        waitUntilClickable(shopItem, 5);
        click(shopItem);
        test.log(Status.INFO, "Selected " + item);
    }

    public boolean successMsgWomenCategory() {
        if(isDisplayed(womenCategoryConfirm, 5)) {
            test.log(Status.PASS, "Successfully went to Womens Category");
        } else {
            test.log(Status.FAIL, "Unable to find Womens Category confirmation");
        }
        return isDisplayed(womenCategoryConfirm);
    }
}
