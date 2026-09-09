package stepDefinitions;

import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

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
        System.out.println("DEBUG [" + scenario.getName() + "] current URL: " + driver.getCurrentUrl());
        String source = driver.getPageSource();
        System.out.println("DEBUG [" + scenario.getName() + "] page source (first 2000 chars):");
        System.out.println(source.substring(0, Math.min(2000, source.length())));
    }

    // Closes the shared browser after every scenario so a dangling
    // ChromeDriver never leaks into the next one.
    @After(order = 0)
    public void tearDown() {
        DriverManager.INSTANCE.quitDriver();
    }
}
