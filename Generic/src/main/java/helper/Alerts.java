package helper;

import base.CommonAPI;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class Alerts {

    private WebDriver driver;

    public Alerts(WebDriver driver) {
        this.driver = driver;
    }

    public Alert getAlert() {
        return driver.switchTo().alert();
    }

    public void acceptAlert() {
        getAlert().accept();
    }

    public void dismissAlert() {
        getAlert().dismiss();
    }

    public void acceptAlertIfPresent() {
        if (!isAlertPresent()) {
            return;
        }
        acceptAlert();
    }

    public void dismissAlertIfPresent() {
        if(!isAlertPresent()) {
            return;
        }
        dismissAlert();
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
