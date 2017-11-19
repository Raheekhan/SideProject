package categories;

import base.CommonAPI;
import com.aventstack.extentreports.Status;
import helper.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dresses extends CommonAPI {

    private WebDriver driver;
    private Waits wait;

    public Dresses(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new Waits(driver);
    }

    @FindBy(xpath = "//a[@title='Dresses']")
    WebElement dresses;

    @FindBy(xpath = "//h2[contains(text(), 'Dresses')]")
    WebElement dressesCategoryConfirm;

    public void dressesCategory() {
        wait.waitUntilClickable(dresses, 5);
        dresses.click();
        test.log(Status.INFO, "Clicked On Dresses Category");
    }

    public boolean successMsgDressesCategory() {
        wait.waitUntilVisibilityOf(dressesCategoryConfirm, 5);
        if(dressesCategoryConfirm.isDisplayed()) {
            test.log(Status.PASS, "Successfully went to Dresses Category");
        } else {
            test.log(Status.FAIL, "Unable to find Dresses Category confirmation");
        }
        return true;
    }
}
