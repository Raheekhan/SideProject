package pages;

import helper.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultsPage {

    private WebDriver driver;
    private Waits wait;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new Waits(driver);
    }

    @FindBy(xpath = "//p[text()='Refine']")
    WebElement refineSearch;

    @FindBy(xpath = "//button[text()='Price']")
    WebElement price;

    @FindBy(id = "search-utilbar-price-min")
    WebElement minimumPriceRange;

    @FindBy(id = "search-utilbar-price-max")
    WebElement maximumPriceRange;

    @FindBy(xpath = "//button[@class='price-refine-btn btn btn-mini']")
    WebElement goButtonPrice;

    @FindBy(xpath = "//span[@class='zero-results-message alert active alert-warning']")
    WebElement noResultsPrice;

    public boolean isRefineSearchPresent() {
        wait.waitUntilVisibilityOf(refineSearch, 10);
        if (refineSearch.isDisplayed()) {
            System.out.println("Refine Is Present");
            return true;
        }
        System.out.println("Refine Is Not Present");
        return false;
    }

    public void refinePrice(String min, String max) {
        wait.waitUntilClickable(refineSearch, 5);
        price.click();
        minimumPriceRange.sendKeys(min);
        maximumPriceRange.sendKeys(max);
        goButtonPrice.click();
    }

    public boolean checkIfResultsFound() {
        wait.waitUntilVisibilityOf(noResultsPrice, 10);
        if (!noResultsPrice.isDisplayed()) {
            System.out.println("Items Found After Search");
            return true;
        }
        System.out.println("No Items Found After Search");
        return false;
    }
}
