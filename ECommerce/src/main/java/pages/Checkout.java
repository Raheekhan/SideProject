package pages;

import base.BaseUtil;
import base.CommonAPI;
import com.aventstack.extentreports.Status;
import helper.Browsers;
import helper.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

public class Checkout extends BaseUtil {

    private WebDriver driver;
    private Waits wait;
    private Browsers helper;

    public Checkout(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new Waits(driver);
        helper = new Browsers(driver);
    }

    @FindBy(xpath = "//span[text()='Add to cart']")
    WebElement submit;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    WebElement proceedToCheckout;

    @FindBy(xpath = "//span[text()='Proceed to checkout']")
    WebElement proceedToCheckoutSummary;

    @FindBy(name = "message")
    WebElement textAreaComment;

    @FindBy(name = "processAddress")
    WebElement proceedToCheckoutAddress;

    @FindBy(id = "uniform-cgv")
    WebElement checkBoxShipping;

    @FindBy(name = "processCarrier")
    WebElement proceedToCheckoutShipping;

    @FindBy(xpath = "//i[@class='icon-ok']")
    WebElement succMsgProductAddedToCard;

    @FindBy(className = "bankwire")
    WebElement bankwire;

    @FindBy(className = "cheque")
    WebElement check;

    @FindBy(xpath = "//span[contains(text(), 'I confirm my order')]")
    WebElement confirmOrder;

    @FindBy(xpath = "//h1[contains(text(), 'Order confirmation')]")
    WebElement orderConfirmationMsg;

    public void bankwirePayment() {
        addToCart();
        summaryCheckout();
        addressCheckout();
        shippingCheckout();
        payByBankwire();
    }

    public void checkPayment() {
        addToCart();
        summaryCheckout();
        addressCheckout();
        shippingCheckout();
        payByCheck();
    }

    private void addToCart() {
        helper.switchToFrame(0);
        wait.waitUntilClickable(submit, 5);
        submit.click();
        test.log(Status.INFO, "Clicked on 'Add To Cart Button'");
        wait.waitUntilClickable(proceedToCheckout, 5);
        successMessageProductAddedToCard();
        proceedToCheckout.click();
        test.log(Status.INFO, "Clicked on 'Proceed To Checkout'");
    }

    private void summaryCheckout() {
        wait.waitUntilClickable(proceedToCheckoutSummary, 5);
        proceedToCheckoutSummary.click();
        test.log(Status.INFO, "Clicked on 'Proceed To Checkout' on Summary Page");
    }

    private void addressCheckout() {
        wait.waitUntilClickable(textAreaComment, 5);
        textAreaComment.click();
        textAreaComment.sendKeys("Deliver With Care!");
        test.log(Status.INFO, "Sent some text to the Text Area");
        proceedToCheckoutAddress.click();
        test.log(Status.INFO, "Clicked on 'Proceed To Checkout' on Address Page");
    }

    private void shippingCheckout() {
        wait.waitUntilClickable(checkBoxShipping, 5);
        checkBoxShipping.click();
        test.log(Status.INFO, "Clicking on 'Agree To Terms'");
        proceedToCheckoutShipping.click();
        test.log(Status.INFO, "Clicked on 'Proceed To Checkout' on Shipping Page");
    }

    private void payByBankwire() {
        wait.waitUntilClickable(bankwire, 5);
        bankwire.click();
        test.log(Status.INFO, "Clicked on Bankwire payment");
        wait.waitUntilClickable(confirmOrder, 5);
        confirmOrder.click();
        test.log(Status.INFO, "Clicked on 'I Confirm My Order'");
    }

    private void payByCheck() {
        wait.waitUntilClickable(check, 5);
        check.click();
        test.log(Status.INFO, "Clicked on Check payment");
        wait.waitUntilClickable(confirmOrder, 5);
        confirmOrder.click();
        test.log(Status.INFO, "Clicked on 'I Confirm My Order'");
    }

    public boolean successMessageProductAddedToCard() {
        wait.waitUntilVisibilityOf(succMsgProductAddedToCard, 5);
        if(succMsgProductAddedToCard.isDisplayed()) {
            test.log(Status.PASS, "Successfully added product ");
        } else {
            test.log(Status.FAIL, "Facing issues with adding product ...");
        }
        return true;
    }

    public boolean successMessageOrderConfirmation() {
        wait.waitUntilVisibilityOf(orderConfirmationMsg, 5);
        if(orderConfirmationMsg.isDisplayed()) {
            test.log(Status.PASS, "Successfully purchased product");
        } else {
            test.log(Status.FAIL, "Unable to purchase product");
        }
        return true;
    }
}