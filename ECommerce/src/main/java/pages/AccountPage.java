package pages;

import base.CommonAPI;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends CommonAPI {

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    By personalInfo = By.xpath("//i[@class='icon-user']");
    By currentPwdField = By.id("old_passwd");
    By saveBtn = By.name("submitIdentity");

    By logoutBtn = By.xpath("//a[@class='loggedOut']");

    By succChangedMsg = By.xpath("//p[@class='alert alert-success']");
    By succLogoutMsg = By.xpath("//a[@class='login']");

    public void changedPassword(String oldPwd) {
        click(personalInfo);
        test.log(Status.INFO, "Clicking on Personal Information");
        type(oldPwd, currentPwdField);
        test.log(Status.INFO, "Passing old password");
        click(saveBtn);
        test.log(Status.INFO, "Clicking on Save Button");
    }

    public void loggedOut() {
        click(logoutBtn);
        test.log(Status.INFO, "Clicking on Sign Out");
    }

    public boolean successfulLogoutMessage() {
        if(isDisplayed(succLogoutMsg, 5)) {
            test.log(Status.PASS, "Successfully logged out");
        } else {
            test.log(Status.FAIL, "Facing issues loggedInWith logging out ...");
        }
        return isDisplayed(succLogoutMsg);
    }

    public boolean successfullyChangedMessage() {
        if(isDisplayed(succChangedMsg, 5)) {
            test.log(Status.PASS, "Successfully Changed and Saved Personal Information");
        } else {
            test.log(Status.FAIL, "Facing issues loggedInWith changing password ...");
        }
        return isDisplayed(succChangedMsg);
    }

    public void loggedInWith(String username, String password) {
        new HomePage(driver).loggedInWith(username, password);
    }

    public boolean successfulLoginMessage() {
        return new HomePage(driver).successfulLoginMessage();
    }
}
