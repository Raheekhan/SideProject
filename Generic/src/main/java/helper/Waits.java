package helper;

import base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Waits {

    private WebDriver driver;

    public Waits(WebDriver driver) {
        this.driver = driver;
    }

    public void fluent(By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS);
        WebElement waitingElement = wait.until(driver -> driver.findElement(locator));
        waitingElement.click();
    }
    public WebElement findClickableElement(By locator, long timeout) {
        WebElement element = new WebDriverWait(driver, timeout)
                .until(elementToBeClickable(locator));
        return element;
    }

    public void waitUntilClickable(WebElement element, long time) {
        new WebDriverWait(driver, time).until(elementToBeClickable(element));
    }

    public void waitUntilClickable(By locator, long time) {
        new WebDriverWait(driver, time).until(elementToBeClickable(locator));
    }

    public void waitUntilPresenceOfElement(By locator, long time) {
        new WebDriverWait(driver, time).until(presenceOfElementLocated(locator));
    }

    public void waitUntilVisibilityOf(WebElement element, long time) {
        new WebDriverWait(driver, time).until(visibilityOf(element));
    }

    public void checkIfElementIsEmpty(WebElement element, long time) {
        new WebDriverWait(driver, time)
                .until(ExpectedConditions.
                        not(ExpectedConditions.textToBePresentInElement(element, "")));
    }
}
