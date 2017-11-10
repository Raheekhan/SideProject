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

    public void with(String user, String pwd) {
        click(SignInBtn);
        test.log(Status.INFO, "Clicking on Sign In");
        type(user, emailField);
        test.log(Status.INFO, "Sending " + user + " to email field");
        type(pwd, passwordField);
        test.log(Status.INFO, "Sending " + pwd + " to password field");
        click(signInBtn);
        test.log(Status.INFO, "Clicking on Sign In");
    }

    public boolean successfulLoginMessage() {
        test.log(Status.PASS, "Successfully logged in");
        return isDisplayed(successLoginMessage);
    }
}