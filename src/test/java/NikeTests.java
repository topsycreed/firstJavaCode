import dto.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

class NikeTests {
    WebDriver driver;

    @BeforeEach
    void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void openTest() {
        driver.get("https://www.nike.com/");
        List<WebElement> africanCountries = driver.findElements(
                By.xpath("//div[@data-testid = 'languageContentWrapper' and not(contains(@class, 'is-hidden'))]" +
                        "//ul[@data-testid = 'Africa-list']//h4/.."));

        Assertions.assertEquals(4, africanCountries.size());
        List<Country> expectedAfricanCountries = List.of(new Country("Egypt", "English", "1"), new Country("Morocco", "English", "1"),
                new Country("Maroc", "Français", "1"), new Country("South Africa", "English", "1"));
        List<Country> actualAfricanCountries = new ArrayList<>();
        for (WebElement country : africanCountries) {
            String name = country.findElement(By.xpath("./h4")).getText();
            String lang = country.findElement(By.xpath("./p")).getText();
            actualAfricanCountries.add(new Country(name, lang, "2"));
        }
        Assertions.assertEquals(expectedAfricanCountries.size(), africanCountries.size());
        Assertions.assertEquals(expectedAfricanCountries, actualAfricanCountries);
        System.out.println(actualAfricanCountries.size());
        for (int i = 0; i < actualAfricanCountries.size(); i++) {
            org.assertj.core.api.Assertions.assertThat(actualAfricanCountries.get(i))
                    .usingRecursiveComparison()
                    .ignoringFields("date")
                    .isEqualTo(expectedAfricanCountries.get(i));
        }
    }
}
