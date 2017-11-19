package helper;

import base.CommonAPI;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScripts {

    private WebDriver driver;

    public JavaScripts(WebDriver driver) {
        this.driver = driver;
    }

    public Object executeScript(String script) {
        return ((JavascriptExecutor)driver).executeScript(script);
    }

    public Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor)driver).executeScript(script);
    }

    public void scrollToElement(WebElement element) {
        executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
    }

    public void scrollToElementAndClick(WebElement element) {
        scrollToElement(element);
        element.click();
    }

    public void mouseScroll(int x, int y) {
        executeScript("window.scrollBy(" + x + "," + y + ")");
    }
}
