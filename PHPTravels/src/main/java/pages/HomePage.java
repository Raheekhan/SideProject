package pages;

import base.CommonAPI;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends CommonAPI {

    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = (new WebDriverWait(driver, 10));
    }

    @FindBy(id = "tab-flight-tab-hp")
    WebElement flightsTab;

    @FindBy(id = "flight-origin-hp-flight")
    WebElement origin;

    @FindBy(id = "flight-destination-hp-flight")
    WebElement destination;

    @FindBy(id = "flight-departing-hp-flight")
    WebElement departureDate;

    @FindBy(id = "flight-returning-hp-flight")
    WebElement returnDate;

    @FindBy(id = "flight-adults-hp-flight")
    WebElement adult;

    @FindBy(xpath = ".//*[@id='gcw-flights-form-hp-flight']/div[8]/label/button")
    WebElement searchFlightBtn;

    @FindBy(id = "priceChangeAlertBtn")
    WebElement successButton;

    public void flightDeals(String orig, String dest, String departdate, String returndate, String adults) {
        flightsTab.click();
        origin.sendKeys(orig);
        destination.sendKeys(dest);
        departureDate.sendKeys(departdate);
        returnDate.clear();
        returnDate.sendKeys(returndate);
        Select adultOptions = new Select(adult);
        adultOptions.selectByVisibleText(adults);
        searchFlightBtn.click();
    }
}
