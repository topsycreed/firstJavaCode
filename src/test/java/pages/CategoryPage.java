package pages;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static constants.AEOConstants.BASE_URL;

public class CategoryPage extends BasePage {
    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get current url")
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    @SneakyThrows
    @Step("Check current url is women category")
    public void checkUrl() {
        Thread.sleep(1000);
        Assertions.assertEquals(BASE_URL + "/c/women/shoes/cat4840021?pagetype=plp", getUrl());
    }
}
