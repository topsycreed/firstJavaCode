package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    WebDriverWait wait;
    WebDriverWait longWait;
    int timeoutSec = 2;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
        longWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void quit() {
        driver.quit();
    }

    public HomePage backToHomePage() {
        //driver.findElement()
        return new HomePage(driver);
    }
}
