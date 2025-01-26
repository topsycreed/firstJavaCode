package compoments;

import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class Header extends BasePage {
    public Header(WebDriver driver) {
        super(driver);
    }

    public int getCountOfItems() {
        driver.getTitle();
        return 1;
    }
}
