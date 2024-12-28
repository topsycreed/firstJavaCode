import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SeleniumTests {
    WebDriver driver;//instance field

    @BeforeEach
    void setup() {
        //Arrange
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        //Post-actions/tear down
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    void firstUITest() {
        //choose driver version - chrome
        //open browser
        //open URL

        //AAA - Arrange (Pre-requisites) - Act (actions) - Assert (checks)
        //WebDriver driver = new ChromeDriver();//not instance field, but local variable

        //Act
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        String url = driver.getCurrentUrl();

        //Assert
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/", url);
    }

    @Test
    void secondUITest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        WebElement webForm = driver.findElement(By.xpath("//a[@href = 'web-form.html']"));
        webForm.click();

        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/web-form.html", url);
    }

    @Test
    void xpathTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        WebElement webForm = driver.findElement(By.xpath("//a[@href = 'web-form.html']"));
        webForm.click();

        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/web-form.html", url);
    }

    @Test
    void multipleLocators() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        WebElement webForm = driver.findElement(By.className("btn-outline-primary"));//27 1st
        webForm.click();

        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/web-form.html", url);
    }

    @Test
    void multipleLocators2() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        List<WebElement> webForm = driver.findElements(By.className("btn-outline-primary"));
        webForm.get(1).click();

        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html", url);
    }

    @Test
    void multipleLocators3() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        List<WebElement> webForm = driver.findElements(By.tagName("a"));
        webForm.get(4).click();

        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html", url);
    }

    @Test
    void multipleLocators4() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        WebElement webForm = driver.findElement(By.linkText("Web form"));
        webForm.click();

        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/web-form.html", url);
    }

    @Test
    void multipleLocators5() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        List<WebElement> webForm = driver.findElements(By.partialLinkText("form"));
        webForm.get(0).click();

        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/web-form.html", url);
    }

    @Test
    void cssLocators1() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        List<WebElement> webForm = driver.findElements(By.cssSelector(".btn-outline-primary"));
        webForm.get(0).click();

        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/web-form.html", url);
    }

    @Test
    void cssLocators2() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        List<WebElement> webForm = driver.findElements(By.cssSelector("a[href ='web-form.html']"));
        if (webForm.size() > 0) {
            webForm.get(0).click();//accept all
            String url = driver.getCurrentUrl();
            Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/web-form.html", url);
        } else {
            System.out.println("There are no elements for locator");
        }
    }
}
