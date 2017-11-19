package helper;

import base.CommonAPI;
import org.openqa.selenium.WebDriver;

import java.util.LinkedList;
import java.util.Set;

public class Browsers {

    private WebDriver driver;

    public Browsers(WebDriver driver) {
        this.driver = driver;
    }

    public void goBack() {
        driver.navigate().back();
    }

    public void goForward() {
        driver.navigate().forward();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public void switchToWindow(int index) {
        LinkedList<String> windowsId = new LinkedList<>(getWindowHandles());
        if (index < 0 || index > windowsId.size()) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
        driver.switchTo().window(windowsId.get(index));
    }

    public void switchToParentWindow() {
        LinkedList<String> windowsId = new LinkedList<>(getWindowHandles());
        driver.switchTo().window(windowsId.get(0));
    }

    public void switchToFrame(String frame) {
        driver.switchTo().frame(frame);
    }

    public void switchToFrame(int frame) {
        driver.switchTo().frame(frame);
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void switchToDefaultFrame() {
        driver.switchTo().defaultContent();
    }
}
