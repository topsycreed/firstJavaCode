import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Objects;

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
        Thread.sleep(5000);
        //find locator accept all to remove
        List<WebElement> popapClose = driver.findElements(By.id("onetrust-accept-btn-handler"));
        if (popapClose.size() > 0) {
            popapClose.get(0).click();
        }
        //hovering
        WebElement womenCategory = driver.findElement(By.cssSelector("a[data-text = 'Women']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenCategory).perform();
        Thread.sleep(2000);
        WebElement shoesSubCategory = driver.findElement(By.xpath("//a[@data-text = 'Women']/..//a[@data-item-link and text() = 'Shoes']"));
        shoesSubCategory.click();
        Thread.sleep(5000);
    }


}
