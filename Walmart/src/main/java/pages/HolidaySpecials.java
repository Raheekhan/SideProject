package pages;

import helper.JavaScripts;
import helper.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HolidaySpecials {

    private WebDriver driver;
    private Waits wait;

    public HolidaySpecials(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new Waits(driver);
    }

    @FindBy(id = "")
    WebElement a;

    public void selectColor(String color) {
        WebElement colors = driver.findElement(By.xpath("//label[@title='" + color + "']/span"));
        wait.waitUntilClickable(colors, 5);
        if (color.equals("Black") || color.equals("Multi") || color.equals("White")
                || color.equals("Blue") || color.equals("Gray") || color.equals("Silver")
                || color.equals("Red") || color.equals("Brown") || color.equals("Other")) {
            colors.click();
        } else {
            System.out.println("Unable to find color");
        }
    }
}