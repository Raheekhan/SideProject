package helper;

import base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Elements extends CommonAPI {

    public static void clickLink(String linkText) {
        if(driver.findElements(By.linkText(linkText)).size() == 1) {
            driver.findElement(By.linkText(linkText)).click();
        } else if (driver.findElements(By.partialLinkText(linkText)).size() == 1) {
            driver.findElement(By.partialLinkText(linkText)).click();
        }
    }

    public static void typeInTextBox(String locator, String value) {
        WebElement ele = getElement(locator);
        ele.sendKeys(value);
    }

    public static void clear(String locator) {
        WebElement ele = getElement(locator);
        ele.clear();
    }

    public static void clickButton(String locator) {
        WebElement ele = getElement(locator);
        ele.click();
    }
}
