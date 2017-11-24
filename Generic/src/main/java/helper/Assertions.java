package helper;

import base.CommonAPI;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assertions extends CommonAPI {

    public static synchronized boolean verifyElementPresent(WebElement element) {
        boolean isDisplayed = false;
        try {
            isDisplayed = element.isDisplayed();
            test.log(Status.INFO, element.getText() + " is displayed");
        } catch (Exception e) {
            test.error("Element not found " + e);
        }
        return isDisplayed;
    }

    public static synchronized boolean verifyElementNotPresent(WebElement element) {
        boolean isDisplayed = false;
        try {
            element.isDisplayed();
            test.log(Status.INFO, element.getText() + " is displayed");
        } catch (Exception e) {
            test.error("Element not found" + e);
            isDisplayed = true;
        }
        return isDisplayed;
    }
}