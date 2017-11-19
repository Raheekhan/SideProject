package pages;

import base.CommonAPI;
import com.aventstack.extentreports.Status;
import helper.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends CommonAPI {

    private WebDriver driver;
    private Waits wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new Waits(driver);
    }

    String Tshirts = "T-shirts";
    String Blouses = "Blouses";
    String CasualDresses = "Casual Dresses";

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "passwd")
    WebElement passwordField;

    @FindBy(css = ".login")
    WebElement signInBtnOne;

    @FindBy(id = "SubmitLogin")
    WebElement signInBtnTwo;

    @FindBy(xpath = "//p[contains(text(), 'Welcome to your account.')]")
    WebElement successLoginMessage;

    public void loggedInWith(String username, String password) {
        signInBtnOne.click();
        test.log(Status.INFO, "Clicking on Sign In");
        emailField.sendKeys(username);
        test.log(Status.INFO, "Sending " + username + " to email field");
        passwordField.sendKeys(password);
        test.log(Status.INFO, "Sending " + password + " to password field");
        signInBtnTwo.click();
        test.log(Status.INFO, "Clicking on Sign In");
    }

    public boolean successfulLoginMessage() {
        wait.waitUntilVisibilityOf(successLoginMessage, 5);
        if(successLoginMessage.isDisplayed()) {
            test.log(Status.PASS, "Successfully logged in");
        } else {
            test.log(Status.FAIL, "Facing issues loggedInWith logging in ...");
        }
        return true;
    }
}