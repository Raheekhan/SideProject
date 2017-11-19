package categories;

import base.CommonAPI;
import com.aventstack.extentreports.Status;
import helper.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Tshirts extends CommonAPI {

    private WebDriver driver;
    private Waits wait;

    public Tshirts(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new Waits(driver);
    }

    @FindBy(xpath = "//a[@title='T-shirts']")
    WebElement tshirts;

    @FindBy(xpath = "//div[@class='cat_desc']/span[contains(text(), 'T-shirts')]")
    WebElement tshirtsCategoryConfirm;

    public void tshirtsCategory() {
        wait.waitUntilClickable(tshirts, 5);
        tshirts.click();
        test.log(Status.INFO, "Clicked On Tshirts Category");
    }

    public boolean successMsgTshirtsCategory() {
        wait.waitUntilVisibilityOf(tshirtsCategoryConfirm, 5);
        if(tshirtsCategoryConfirm.isDisplayed()) {
            test.log(Status.PASS, "Successfully went to T-shirts Category");
        } else {
            test.log(Status.FAIL, "Unable to find T-shirts Category confirmation");
        }
        return true;
    }
}
