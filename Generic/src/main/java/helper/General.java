package helper;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class General {

    private WebDriver driver;

    public General(WebDriver driver) {
        this.driver = driver;
    }

    public String randomUsernameGenerator() {
        String username = RandomStringUtils.randomAlphabetic(8);
        return username;
    }

    public String randomPasswordGenerator() {
        String password = RandomStringUtils.randomAlphanumeric(4) + RandomStringUtils.randomAlphabetic(4);
        return password;
    }

    public String randomEmailGenerator() {
        String username = RandomStringUtils.randomAlphabetic(8) + "@gmail.com";
        return username;
    }

    public void findIfLinksAreBroken() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total links in " + driver.getCurrentUrl() + " are: " + links.size());
        System.out.println("==================================================================");
        for(int i = 0; i < links.size(); i++) {
            WebElement element = links.get(i);
            String url = element.getAttribute("href");
            verifyLinkActive(url);
        }
    }

    private static void verifyLinkActive(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
            httpURLConnect.setConnectTimeout(3000);
            httpURLConnect.connect();
            if (httpURLConnect.getResponseCode() == 200) {
                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
            }
            if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - " + HttpURLConnection.HTTP_NOT_FOUND);
            }
        } catch (Exception e) {

        }
    }
}
