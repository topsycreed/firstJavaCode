import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static constants.AEOConstants.BASE_URL;

public class AddItemsTests {
    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    void openHomePageTest() {
        driver.get(BASE_URL);

        String currentUrl = driver.getCurrentUrl();
        WebElement aeoLogo = driver.findElement(By.xpath("//a[@data-test-logo]/span[@data-test-logo = 'active-logo-aeo']"));

//        WebElement aeoLogo = null;
//        List<WebElement> elementList = driver.findElements(By.xpath("//a[@title]/span[@data-test-logo = 'active-logo-aeo']"));
        //echanced for
        //for (int i = 0; i < elementList.size(); i++)
//        for (WebElement currentElement : elementList) {
//            System.out.println(currentElement.getCssValue("height"));
//            System.out.println(currentElement.getCssValue("width"));
//            Assertions.assertTrue(currentElement.isDisplayed());
//            if (currentElement.getCssValue("width").equals("215px")) {
//                aeoLogo = currentElement;
//            }
//        }
        Assertions.assertEquals(BASE_URL, currentUrl);
        Assertions.assertTrue(aeoLogo.isDisplayed());
//        Assertions.assertTrue(Objects.requireNonNull(aeoLogo).isDisplayed());
    }

    @Test
    void addItemTest() throws InterruptedException {
        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //find locator accept all to remove
        List<WebElement> popapClose = driver.findElements(By.id("onetrust-accept-btn-handler"));
        if (popapClose.size() > 0) {
            popapClose.get(0).click();
        }
        //hovering
        WebElement womenCategory = driver.findElement(By.xpath("//a[@data-text = 'Women']"));
        new Actions(driver)
                .moveToElement(womenCategory)
                .perform();
        WebElement shoesSubCategory = longWait.
                until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-text = 'Women']/..//a[@data-item-link and text() = 'Shoes']")));
        shoesSubCategory.click();

        List<WebElement> products = driver.findElements(By.xpath("//div[@data-product-id]"));
        WebElement firstProduct = products.get(0);
        WebElement firstProductName = firstProduct.findElement(By.xpath(".//h3[@data-test-name]"));
        WebElement firstProductListPrice = firstProduct.findElement(By.xpath(".//div[@data-test-list-price]"));

        //Need to be used to compare in future
        String firstProductNameValue = firstProductName.getText();
        String firstProductListPriceValue = firstProductListPrice.getText();

        new Actions(driver)
                .moveToElement(firstProduct)
                .perform();
        WebElement quickShop = firstProduct.findElement(By.xpath(".//a[text() = 'Quick Shop']"));
        quickShop.click();
    }


}
