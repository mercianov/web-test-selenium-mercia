package stepDefinitions;

import driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObjects.PageContainer;

public class loginStepDefinition {

    @Given("User is on the SauceDemo login page")
    public void user_is_on_the_saucedemo_login_page() {
        DriverManager.INSTANCE.setupDriver();
        PageContainer.INSTANCE.initialize(DriverManager.INSTANCE.getDriver());
        PageContainer.INSTANCE.getLoginPage().open();
    }

    @Given("User is logged in as {string}")
    public void user_is_logged_in_as(String username) {
        DriverManager.INSTANCE.setupDriver();
        PageContainer.INSTANCE.initialize(DriverManager.INSTANCE.getDriver());
        PageContainer.INSTANCE.getLoginPage().open();
        PageContainer.INSTANCE.getLoginPage().login(username, "secret_sauce");
    }

    @When("User logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String username, String password) {
        PageContainer.INSTANCE.getLoginPage().login(username, password);
    }

    @Then("User should see the products page")
    public void user_should_see_the_products_page() {
        Assert.assertEquals("Products", PageContainer.INSTANCE.getProductsPage().getPageTitle());
    }

    @Then("User should see an error message {string}")
    public void user_should_see_an_error_message(String message) {
        Assert.assertEquals(message, PageContainer.INSTANCE.getLoginPage().getErrorMessage());
    }
}
