package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

import static utilities.commonUtil.waitForElement;

public class ProductsPage {
    private static final By PAGE_TITLE = By.className("title");
    private static final By INVENTORY_ITEM = By.className("inventory_item");
    private static final By ITEM_NAME = By.className("inventory_item_name");
    private static final By ADD_TO_CART_BUTTON = By.cssSelector("button.btn_inventory");
    private static final By CART_LINK = By.className("shopping_cart_link");
    private static final By CART_BADGE = By.className("shopping_cart_badge");

    private final WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        waitForElement(driver, PAGE_TITLE);
        return driver.findElement(PAGE_TITLE).getText();
    }

    public int getProductCount() {
        return driver.findElements(INVENTORY_ITEM).size();
    }

    public void addProductToCart(String productName) {
        findProductItem(productName).findElement(ADD_TO_CART_BUTTON).click();
    }

    public void openProductDetails(String productName) {
        findProductItem(productName).findElement(ITEM_NAME).click();
    }

    public int getCartBadgeCount() {
        List<WebElement> badge = driver.findElements(CART_BADGE);
        return badge.isEmpty() ? 0 : Integer.parseInt(badge.get(0).getText());
    }

    public void openCart() {
        waitForElement(driver, CART_LINK);
        driver.findElement(CART_LINK).click();
    }

    private WebElement findProductItem(String productName) {
        return driver.findElements(INVENTORY_ITEM).stream()
                .filter(item -> item.findElement(ITEM_NAME).getText().equals(productName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product not found: " + productName));
    }
}
