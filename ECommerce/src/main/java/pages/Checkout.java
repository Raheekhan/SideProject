package pages;

import base.CommonAPI;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Checkout extends CommonAPI {

    public Checkout(WebDriver driver) {
        this.driver = driver;
    }

    By addToCartBtn = By.xpath("//button[@name='Submit']");
    By submit = By.xpath("//span[text()='Add to cart']");
    By proceedCheckout = By.xpath("//a[@title='Proceed to checkout']");

    By summary = By.xpath("//p[@class='cart_navigation clearfix']//a[@title='Proceed to checkout']");
    By address = By.xpath("//button[@name='processAddress']");

    public void addToCart() {
        switchToFrame("fancybox-iframe");
        waitUntilClickable(submit, 5);
        click(submit);

        test.log(Status.INFO, "Clicked on Add To Cart Button");
    }
}
