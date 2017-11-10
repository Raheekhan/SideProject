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

    By succChangedMsg = By.xpath("//p[@class='alert alert-success']");

    public void changePassword(String pwd) {
        click(personalInfo);
        type(pwd, currentPwdField);
        click(saveBtn);
    }

    public boolean successfulChangemessage() {
        test.log(Status.PASS, "Successfully Changed and Saved Personal Information");
        return isDisplayed(succChangedMsg);
    }
}
