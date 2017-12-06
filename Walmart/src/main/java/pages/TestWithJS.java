package pages;

import com.testautomationguru.ocular.Ocular;
import com.testautomationguru.ocular.comparator.OcularResult;
import com.testautomationguru.ocular.snapshot.Snap;
import helper.JavaScripts;
import helper.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Snap("TestWithJS.png")
public class TestWithJS {

    private WebDriver driver;
    private Waits wait;
    JavaScripts jse;

    public TestWithJS(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new Waits(driver);
        jse = new JavaScripts(driver);
    }

    @FindBy(id = "")
    WebElement a;

    @FindBy(id = "")
    WebElement b;

    @FindBy(id = "")
    WebElement c;

    @FindBy(id = "")
    WebElement d;

    public void test() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jse.executeScript("document.getElementById('global-search-input').value = 'Java'");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jse.executeScript("document.getElementById('global-search-input').value = ''");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        compare();
    }

    public OcularResult compare() {
        return Ocular.snapshot().from(this).sample().using(driver).compare();
    }
}