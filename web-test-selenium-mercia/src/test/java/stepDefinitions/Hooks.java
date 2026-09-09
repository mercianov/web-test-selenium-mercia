package stepDefinitions;

import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class Hooks {

    // Diagnostic: dump URL/page source to the build log on failure so a CI-only
    // failure (e.g. the live demo site serving something different to the
    // runner's IP) can be understood without needing an interactive browser.
    @After(order = 100)
    public void dumpStateOnFailure(Scenario scenario) {
        if (!scenario.isFailed()) {
            return;
        }
        WebDriver driver = DriverManager.INSTANCE.getDriver();
        if (driver == null) {
            return;
        }
        String prefix = "DEBUG [" + scenario.getName() + "] ";
        System.out.println(prefix + "current URL: " + driver.getCurrentUrl());
        String source = driver.getPageSource();
        System.out.println(prefix + "page source length: " + source.length());
        System.out.println(prefix + "contains 'checkout': " + source.contains("checkout"));
        System.out.println(prefix + "contains 'cart_list': " + source.contains("cart_list"));
        int idx = source.indexOf("cart_contents_container");
        if (idx < 0) {
            idx = source.indexOf("cart_list");
        }
        if (idx >= 0) {
            int end = Math.min(source.length(), idx + 3000);
            System.out.println(prefix + "page source around cart contents:");
            System.out.println(source.substring(idx, end));
        }
        try {
            for (LogEntry entry : driver.manage().logs().get(LogType.BROWSER)) {
                System.out.println(prefix + "console: " + entry);
            }
        } catch (Exception e) {
            System.out.println(prefix + "could not read browser console logs: " + e);
        }
    }

    // Closes the shared browser after every scenario so a dangling
    // ChromeDriver never leaks into the next one.
    @After(order = 0)
    public void tearDown() {
        DriverManager.INSTANCE.quitDriver();
    }
}
