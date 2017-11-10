package pages;

import base.CommonAPI;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends CommonAPI {

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By emailField = By.id("email");
    By passwordField = By.id("passwd");
    By SignInBtn = By.cssSelector(".login");
    By signInBtn = By.id("SubmitLogin");
    By successLoginMessage = By.xpath("//p[contains(text(), 'Welcome to your account.')]");

    public void with(String username, String password) {
        click(SignInBtn);
        test.log(Status.INFO, "Clicking on Sign In");
        type(username, emailField);
        test.log(Status.INFO, "Sending " + username + " to email field");
        type(password, passwordField);
        test.log(Status.INFO, "Sending " + password + " to password field");
        click(signInBtn);
        test.log(Status.INFO, "Clicking on Sign In");
    }

    public boolean successfulLoginMessage() {
        if(isDisplayed(successLoginMessage, 5)) {
            test.log(Status.PASS, "Successfully logged in");
        } else {
            test.log(Status.FAIL, "Facing issues with logging in ...");
        }
        return isDisplayed(successLoginMessage);
    }
}