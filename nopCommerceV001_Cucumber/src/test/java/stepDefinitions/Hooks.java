package stepDefinitions;

import driver.DriverManager;
import io.cucumber.java.After;

public class Hooks {

    // Closes the shared browser after every scenario so a dangling
    // ChromeDriver never leaks into the next one.
    @After
    public void tearDown() {
        DriverManager.INSTANCE.quitDriver();
    }
}
