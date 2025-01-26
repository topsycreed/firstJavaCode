package pages;

import compoments.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static constants.AEOConstants.BASE_URL;

public class HomePage extends BasePage {
    Header header;

    public HomePage(WebDriver driver) {
        super(driver);
        header = new Header(driver);
    }

    //@Step("Open homePage")
    public void open() {
        driver.get(BASE_URL);
    }

    public CategoryPage openWomenCategory() {
        WebElement womenCategory = driver.findElement(By.xpath("//a[@data-text = 'Women']"));
        new Actions(driver)
                .moveToElement(womenCategory)
                .perform();
        WebElement shoesSubCategory = longWait.
                until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-text = 'Women']/..//a[@data-item-link and text() = 'Shoes']")));
        shoesSubCategory.click();
        return new CategoryPage(driver);
    }
}
