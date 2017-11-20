package pages;

import helper.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;
    private Waits wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new Waits(driver);
    }

    @FindBy(xpath = "//button[@class='header-GlobalSearch-submit btn']")
    WebElement searchButton;

    @FindBy(id = "global-search-input")
    WebElement searchField;

    public void executeSearch(String keyword) {
        wait.waitUntilClickable(searchField, 5);
        searchField.sendKeys(keyword);
        searchButton.click();
    }
}
