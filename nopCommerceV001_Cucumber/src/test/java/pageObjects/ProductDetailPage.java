package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utilities.commonUtil.waitForElement;

public class ProductDetailPage {
    private static final By PRODUCT_NAME = By.className("inventory_details_name");

    private final WebDriver driver;

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductName() {
        waitForElement(driver, PRODUCT_NAME);
        return driver.findElement(PRODUCT_NAME).getText();
    }
}
