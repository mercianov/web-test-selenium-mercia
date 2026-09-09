package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Single shared WebDriver instance for the whole test run.
 * Cucumber-java creates one instance per glue class per scenario, so a
 * driver field on a step definition class is never visible to another
 * step definition class. Routing every class through this singleton
 * instead of through per-class fields is what lets loginStepDefinition,
 * customerStepDefinitions and viewProductStepDefinitions share one browser.
 */
public enum DriverManager {
    INSTANCE;

    private WebDriver driver;

    public void setupDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            // CI runners are slower/headless-only; GitHub Actions sets CI=true by default.
            if (Boolean.parseBoolean(System.getenv("CI"))) {
                options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
            }
            driver = new ChromeDriver(options);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
