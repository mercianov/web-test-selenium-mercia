package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class CartPage {
    private static final By CART_ITEM = By.className("cart_item");
    private static final By ITEM_NAME = By.className("inventory_item_name");
    private static final By REMOVE_BUTTON = By.cssSelector("button.cart_button");
    private static final By CHECKOUT_BUTTON = By.id("checkout");

    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getCartItems() {
        return driver.findElements(CART_ITEM);
    }

    public int getItemCount() {
        return getCartItems().size();
    }

    public boolean containsProduct(String productName) {
        return getCartItems().stream()
                .anyMatch(item -> item.findElement(ITEM_NAME).getText().equals(productName));
    }

    public void removeProduct(String productName) {
        getCartItems().stream()
                .filter(item -> item.findElement(ITEM_NAME).getText().equals(productName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product not in cart: " + productName))
                .findElement(REMOVE_BUTTON)
                .click();
    }

    public void checkout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }
}
