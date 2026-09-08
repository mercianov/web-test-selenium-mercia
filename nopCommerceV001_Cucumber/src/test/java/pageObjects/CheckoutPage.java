package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utilities.commonUtil.waitForElement;

public class CheckoutPage {
    private static final By FIRST_NAME = By.id("first-name");
    private static final By LAST_NAME = By.id("last-name");
    private static final By POSTAL_CODE = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By FINISH_BUTTON = By.id("finish");
    private static final By COMPLETE_HEADER = By.className("complete-header");

    private final WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME).sendKeys(firstName);
        driver.findElement(LAST_NAME).sendKeys(lastName);
        driver.findElement(POSTAL_CODE).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void finishCheckout() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public String getConfirmationMessage() {
        waitForElement(driver, COMPLETE_HEADER);
        return driver.findElement(COMPLETE_HEADER).getText();
    }
}
