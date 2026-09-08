package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utilities.commonUtil.waitForElement;

public class LoginPage {
    private static final String URL = "https://www.saucedemo.com/";

    private static final By USERNAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
        waitForElement(driver, USERNAME);
    }

    public void login(String username, String password) {
        driver.findElement(USERNAME).clear();
        driver.findElement(USERNAME).sendKeys(username);
        driver.findElement(PASSWORD).clear();
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getErrorMessage() {
        waitForElement(driver, ERROR_MESSAGE);
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
