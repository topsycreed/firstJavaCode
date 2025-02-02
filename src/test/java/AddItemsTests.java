import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CategoryPage;
import pages.HomePage;
import tests.BaseTest;

import java.time.Duration;
import java.util.List;

import static constants.AEOConstants.BASE_URL;
import static constants.AEOConstants.HOME_PAGE_TITLE;
import static io.qameta.allure.SeverityLevel.CRITICAL;

@Tags({@Tag("ui"), @Tag("smoke")})
@Epic("Automation")
@Feature("AEO UI tests")
@Story("Add item")
@Owner("Gennadii Chursov")
class AddItemsTests extends BaseTest {
    @Test
    void openHomePagePOMTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();

        Assertions.assertEquals(BASE_URL, homePage.getUrl());
        Assertions.assertEquals(HOME_PAGE_TITLE, homePage.getTitle());
    }

    @Test
    @DisplayName("Open category page")
    @Description("Open category page desc")
    void openCategoryPagePOMTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        closeOneTrustCookies();
        CategoryPage categoryPage = homePage.openWomenCategory();
//        homePage.openMenCategory();
//        homePage.openJeanCategory();
//        homePage.openCategory("Women");
        categoryPage.checkUrl();
//        categoryPage.getUrl();
//        Assertions.assertEquals(BASE_URL + "c/women/womens?pagetype=clp", homePage.getUrl());
    }

    @Test
    void openHomePageTest() {
        WebDriver driver = getDriver();
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
    @DisplayName("Add women category item test and check payPal windows")
    @Description("Description of the test")
    @Tag("ShoppingBag")
    @Severity(CRITICAL)
    void addItemTest() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //find locator accept all to remove
        closeOneTrustCookies();
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

        closeWeekendPopupWorking();
        Thread.sleep(5000);

        selectFirstAvailableSize();
        driver.findElement(By.xpath("//button[@name = 'add-to-bag']")).click();
        WebElement title = longWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-test-sidetray = 'added-to-bag']//h2")));

        //assertions
        Assertions.assertEquals("Added to bag!", title.getText());
        driver.findElement(By.name("viewBag")).click();
        longWait.until(ExpectedConditions.textToBe(By.xpath("//h1"), "Shopping Bag"));

        WebElement iframe = driver.findElement(By.xpath("//iframe[@title = 'PayPal']"));
        driver.switchTo().frame(iframe);
        WebElement payPalButton = driver.findElement(By.xpath("//div[@data-funding-source = 'paypal']"));
        new Actions(driver).moveToElement(payPalButton).perform();
        Thread.sleep(1000);
        payPalButton.click();
        //"//img[contains(@role, 'paypal-logo')]"
        Thread.sleep(5000);
    }

    private void selectFirstAvailableSize() {
        WebDriver driver = getDriver();
        driver.findElement(By.xpath("//div[@data-test-select-size]")).click();
        List<WebElement> allSizes = driver.findElements(By.xpath("//li[@data-value]"));
        for (WebElement size : allSizes) {
            if (!size.getAttribute("class").contains("visually-disabled")) {
                size.click();
                break;
            }
        }
    }

    @SneakyThrows
    private void closeOneTrustCookies() {
        Thread.sleep(2000);
        WebDriver driver = getDriver();
        List<WebElement> popapClose = driver.findElements(By.id("onetrust-accept-btn-handler"));
        if (popapClose.size() > 0) {
            popapClose.get(0).click();
        }
    }

    private void closeWeekendPopupWorking() throws InterruptedException {
        Thread.sleep(3000);
        WebDriver driver = getDriver();
        try {
            WebElement parentForShadowRoot = driver.findElement(By.xpath("//div"));
            SearchContext shadowRoot = parentForShadowRoot.getShadowRoot();
            shadowRoot.findElement(By.cssSelector("button.close")).click();
        } catch (Exception ignore) {
        }
    }

    private void closeWeekendPopup() throws InterruptedException {
        Thread.sleep(5000);
        WebDriver driver = getDriver();
        WebElement shadowDiv = driver.findElement(By.xpath("//body/div"));
        SearchContext shadowRoot = shadowDiv.getShadowRoot();
//        List<WebElement> closePopapShadowDom = shadowRoot.findElements(By.xpath(".//button[@aria-label='Close']"));
        List<WebElement> closePopapShadowDom = shadowRoot.findElements(By.cssSelector("button[@aria-label='Close']"));
        if (!closePopapShadowDom.isEmpty()) {
            closePopapShadowDom.get(0).click();
        }
    }
}
