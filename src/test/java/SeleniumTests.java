import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
}
