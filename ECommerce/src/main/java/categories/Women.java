package categories;

import base.CommonAPI;
import com.aventstack.extentreports.Status;
import helper.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Women extends CommonAPI {

    private WebDriver driver;
    private Waits wait;

    public Women(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new Waits(driver);
    }

    @FindBy(xpath = "//a[@title='Women']")
    WebElement women;

    @FindBy(xpath = "//h2[contains(text(), 'Women')]")
    WebElement womenCategoryConfirm;

    public void womenCategory() {
        wait.waitUntilClickable(women, 5);
        women.click();
        test.log(Status.INFO, "Clicked On Women Category");
    }

    public void selectItem(String item) {
        WebElement element = driver.findElement(By.xpath("//a[@title='" + item + "']"));
        wait.waitUntilClickable(element, 5);
        element.click();
        test.log(Status.INFO, "Selected " + item);
    }

    public boolean successMsgWomenCategory() {
        wait.waitUntilVisibilityOf(womenCategoryConfirm, 5);
        if (womenCategoryConfirm.isDisplayed()) {
            test.log(Status.PASS, "Successfully went to Womens Category");
        } else {
            test.log(Status.FAIL, "Unable to find Womens Category confirmation");
        }
        return true;
    }
}
