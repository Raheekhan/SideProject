package pages;

import com.testautomationguru.ocular.Ocular;
import com.testautomationguru.ocular.comparator.OcularResult;
import com.testautomationguru.ocular.snapshot.Snap;
import org.openqa.selenium.WebDriver;

@Snap("RichFace.png")
public class RichFace {

    private final WebDriver driver;

    public RichFace(WebDriver driver) {
        this.driver = driver;
    }

    public OcularResult compare() {
        return Ocular.snapshot().from(this) // lets Ocular read the @Snap value
                .sample().using(driver) // lets Ocular to take the current page screenshot
                // .exclude(element) This is used to exclude an Element, example dynamically changing
                .compare(); // compares the snapshot against sample and returns the result
    }
}
