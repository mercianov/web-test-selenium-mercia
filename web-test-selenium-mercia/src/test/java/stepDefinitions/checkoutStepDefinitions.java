package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObjects.PageContainer;

public class checkoutStepDefinitions {

    @When("User proceeds to checkout")
    public void user_proceeds_to_checkout() {
        PageContainer.INSTANCE.getCartPage().checkout();
    }

    @When("User fills in checkout information {string} {string} {string}")
    public void user_fills_in_checkout_information(String firstName, String lastName, String postalCode) {
        PageContainer.INSTANCE.getCheckoutPage().fillCheckoutInfo(firstName, lastName, postalCode);
    }

    @When("User completes the checkout")
    public void user_completes_the_checkout() {
        PageContainer.INSTANCE.getCheckoutPage().finishCheckout();
    }

    @Then("User should see the order confirmation {string}")
    public void user_should_see_the_order_confirmation(String message) {
        Assert.assertEquals(message, PageContainer.INSTANCE.getCheckoutPage().getConfirmationMessage());
    }
}
