package pages;

import compoments.Header;
import configs.TestConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AllureSteps;

import java.time.Duration;

import static constants.AEOConstants.BASE_URL;

public class HomePage extends BasePage {
    Header header;
    AllureSteps allureSteps = new AllureSteps();
    TestConfig testConfig = ConfigFactory.create(TestConfig.class, System.getProperties());

    public HomePage(WebDriver driver) {
        super(driver);
        header = new Header(driver);
    }

    @Step("Open homePage")
    public void open() {
        driver.get(testConfig.getBaseUrl());
    }

    @Step("Open Women Category page")
    public CategoryPage openWomenCategory() {
        WebElement womenCategory = driver.findElement(By.xpath("//a[@data-text = 'Women']"));
        new Actions(driver)
                .moveToElement(womenCategory)
                .perform();
        WebElement shoesSubCategory = longWait.
                until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-text = 'Women']/..//a[@data-item-link and text() = 'Shoes']")));
        shoesSubCategory.click();
        return new CategoryPage(driver);
    }
}
