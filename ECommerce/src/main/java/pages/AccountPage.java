package pages;

import base.BaseUtil;
import base.CommonAPI;
import com.aventstack.extentreports.Status;
import helper.Assertions;
import helper.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage extends BaseUtil {

    private WebDriver driver;
    private Waits wait;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new Waits(driver);
    }

    @FindBy(xpath = "//*[contains(text(),'Welcome to your account')]")
    WebElement myPersonalInformation;

    @FindBy(xpath = "//*[contains(text(),'Order history and details')]")
    WebElement orderHistoryAndDetails;

    @FindBy(xpath = "//*[contains(text(),'My personal information')]")
    WebElement successfullyLoggedIn;

    @FindBy(xpath = "//i[@class='icon-user']")
    WebElement personalInfo;

    @FindBy(id = "old_passwd")
    WebElement currentPwdField;

    @FindBy(name = "submitIdentity")
    WebElement saveBtn;

    @FindBy(xpath = "//a[@class='logout']")
    WebElement logoutBtn;

    @FindBy(xpath = "//p[@class='alert alert-success']")
    WebElement succChangedMsg;

    @FindBy(xpath = "//a[@class='login']")
    WebElement succLogoutMsg;

    public void changedPassword(String oldPwd) {
        personalInfo.click();
        test.log(Status.INFO, "Clicking on Personal Information");
        currentPwdField.sendKeys(oldPwd);
        test.log(Status.INFO, "Passing old password");
        saveBtn.click();
        test.log(Status.INFO, "Clicking on Save Button");
    }

    public void loggedOut() {
        logoutBtn.click();
        test.log(Status.INFO, "Clicking on Sign Out");
    }

    public boolean successfulLogoutMessage() {
        wait.waitUntilVisibilityOf(succLogoutMsg, 5);
        if(succLogoutMsg.isDisplayed()) {
            test.log(Status.PASS, "Successfully logged out");
        } else {
            test.log(Status.FAIL, "Facing issues loggedInWith logging out ...");
        }
        return true;
    }

    public boolean successfullyChangedMessage() {
        wait.waitUntilVisibilityOf(succChangedMsg, 5);
        if(succChangedMsg.isDisplayed()) {
            test.log(Status.PASS, "Successfully Changed and Saved Personal Information");
        } else {
            test.log(Status.FAIL, "Facing issues loggedInWith changing password ...");
        }
        return true;
    }

    public boolean verifySuccessloginMsg() {
        return Assertions.verifyElementPresent(successfullyLoggedIn);
    }
}
