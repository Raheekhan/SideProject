package pages;

import base.CommonAPI;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class Checkout extends CommonAPI {

    public Checkout(WebDriver driver) {
        this.driver = driver;
    }

    By submit                       = By.xpath("//span[text()='Add to cart']");
    By proceedToCheckout            = By.xpath("//a[@title='Proceed to checkout']");
    By proceedToCheckoutSummary     = By.xpath("//span[text()='Proceed to checkout']");
    By textAreaComment              = By.name("message");
    By proceedToCheckoutAddress     = By.name("processAddress");
    By checkBoxShipping             = By.id("uniform-cgv");
    By proceedToCheckoutShipping    = By.name("processCarrier");

    By succMsgProductAddedToCard    = By.xpath("//i[@class='icon-ok']");

    By bankwire                     = By.className("bankwire");
    By check                        = By.className("cheque");
    By confirmOrder                 = By.xpath("//span[contains(text(), 'I confirm my order')]");
    By orderConfirmationMsg         = By.xpath("//h1[contains(text(), 'Order confirmation')]");


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
        switchToFrame(0);
        waitUntilClickable(submit, 5);
        click(submit);
        test.log(Status.INFO, "Clicked on 'Add To Cart Button'");
        waitUntilClickable(proceedToCheckout, 5);
        successMessageProductAddedToCard();
        click(proceedToCheckout);
        test.log(Status.INFO, "Clicked on 'Proceed To Checkout'");
    }

    private void summaryCheckout() {
        waitUntilClickable(proceedToCheckoutSummary, 5);
        click(proceedToCheckoutSummary);
        test.log(Status.INFO, "Clicked on 'Proceed To Checkout' on Summary Page");
    }

    private void addressCheckout() {
        waitUntilClickable(textAreaComment, 5);
        clear(textAreaComment);
        type("Deliver With Care!", textAreaComment);
        test.log(Status.INFO, "Sent some text to the Text Area");
        click(proceedToCheckoutAddress);
        test.log(Status.INFO, "Clicked on 'Proceed To Checkout' on Address Page");
    }

    private void shippingCheckout() {
        waitUntilClickable(checkBoxShipping, 5);
        click(checkBoxShipping);
        test.log(Status.INFO, "Clicking on 'Agree To Terms'");
        click(proceedToCheckoutShipping);
        test.log(Status.INFO, "Clicked on 'Proceed To Checkout' on Shipping Page");
    }

    private void payByBankwire() {
        waitUntilClickable(bankwire, 5);
        click(bankwire);
        test.log(Status.INFO, "Clicked on Bankwire payment");
        waitUntilClickable(confirmOrder, 5);
        click(confirmOrder);
        test.log(Status.INFO, "Clicked on 'I Confirm My Order'");
    }

    private void payByCheck() {
        waitUntilClickable(check, 5);
        click(check);
        test.log(Status.INFO, "Clicked on Check payment");
        waitUntilClickable(confirmOrder, 5);
        click(confirmOrder);
        test.log(Status.INFO, "Clicked on 'I Confirm My Order'");
    }

    public boolean successMessageProductAddedToCard() {
        if(isDisplayed(succMsgProductAddedToCard, 5)) {
            test.log(Status.PASS, "Successfully added product ");
        } else {
            test.log(Status.FAIL, "Facing issues with adding product ...");
        }
        return isDisplayed(succMsgProductAddedToCard);
    }

    public boolean successMessageOrderConfirmation() {
        if(isDisplayed(orderConfirmationMsg, 5)) {
            test.log(Status.PASS, "Successfully purchased product");
        } else {
            test.log(Status.FAIL, "Unable to purchase product");
        }
        return isSelected(orderConfirmationMsg);
    }
}