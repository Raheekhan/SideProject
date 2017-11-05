package homepage;

import base.CommonAPI;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConnectDB;


public class TestVerification extends CommonAPI {

    ConnectDB db = new ConnectDB();

    private Actions action = null;

    public TestVerification(WebDriver driver) {
        PageFactory.initElements(driver, this);
        action = new Actions(driver);
    }

    @FindBy(id = "top-search-input")
    WebElement searchBar;

    @FindBy(css = ".user.login-account")
    WebElement myAccount;

    @FindBy(xpath = "//button[text()='Create a B&H Account']")
    WebElement createAccount;

    @FindBy(id = "create-first")
    WebElement firstNameField;

    @FindBy(id = "create-last")
    WebElement lastNameField;

    @FindBy(id = "create-email")
    WebElement emailField;

    @FindBy(id = "create-password")
    WebElement passwordField;

    @FindBy(xpath = "//input[@value='Create Account']")
    WebElement createAccountBtn;

    public void methodTwo(String input) {
        waitUntilClickable(searchBar, 10);
        searchBar.sendKeys(input);
        searchBar.clear();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void registerAccount() {
        action.moveToElement(myAccount).perform();
        createAccount.click();
        test.log(Status.INFO, "Clicking On Create Account");
        firstNameField.sendKeys(randomUsernameGenerator());
        test.log(Status.INFO, "Entering Username");
        lastNameField.sendKeys(randomUsernameGenerator());
        emailField.sendKeys(randomEmailGenerator());
        passwordField.sendKeys(randomPasswordGenerator());
//        //createAccountBtn.click();
      }
}
