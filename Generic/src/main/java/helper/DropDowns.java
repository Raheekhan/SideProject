package helper;

import base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDowns {

    private WebDriver driver;

    public DropDowns(WebDriver driver) {
        this.driver = driver;
    }

    public void selectByVisibleText(WebElement element, String visibleText) {
        new Select(element).selectByVisibleText(visibleText);
    }

    public void selectByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    public String getSelectedValue(WebElement element) {
        return new Select(element).getFirstSelectedOption().getText();
    }
}
