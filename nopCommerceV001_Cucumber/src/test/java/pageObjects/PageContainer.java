package pageObjects;

import org.openqa.selenium.WebDriver;

/**
 * Holds one shared instance of every page object for the current scenario.
 * Step definition classes fetch pages through PageContainer.INSTANCE instead
 * of constructing their own, so all step classes see the same page objects
 * bound to the same WebDriver regardless of which class Cucumber invokes.
 */
public enum PageContainer {
    INSTANCE;

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    public void initialize(WebDriver driver) {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public ProductsPage getProductsPage() {
        return productsPage;
    }

    public ProductDetailPage getProductDetailPage() {
        return productDetailPage;
    }

    public CartPage getCartPage() {
        return cartPage;
    }

    public CheckoutPage getCheckoutPage() {
        return checkoutPage;
    }
}
