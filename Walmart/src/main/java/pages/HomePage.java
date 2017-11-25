package pages;

import com.testautomationguru.ocular.Ocular;
import com.testautomationguru.ocular.comparator.OcularResult;
import com.testautomationguru.ocular.snapshot.Snap;
import helper.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Snap("HomePage.png")
public class HomePage {

    private WebDriver driver;
    private Waits wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new Waits(driver);
    }

    @FindBy(xpath = "//span[@class='elc-icon elc-icon-search']")
    WebElement searchButton;

    @FindBy(id = "global-search-input")
    WebElement searchField;

    public void executeSearch(String keyword) {
        wait.waitUntilClickable(searchField, 5);
        searchField.sendKeys(keyword);
        searchButton.click();
        compare();
    }

    public OcularResult compare() {
        return Ocular.snapshot().from(this) // lets Ocular read the @Snap value
                .sample().using(driver) // lets Ocular to take the current page screenshot
                .compare(); // compares the snapshot against sample and returns the result
    }
}
