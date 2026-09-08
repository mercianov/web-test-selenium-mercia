package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObjects.PageContainer;

public class cartStepDefinitions {

    @Given("User has added {string} to the cart")
    @When("User adds {string} to the cart")
    public void user_adds_product_to_the_cart(String productName) {
        PageContainer.INSTANCE.getProductsPage().addProductToCart(productName);
    }

    @When("User views the cart")
    public void user_views_the_cart() {
        PageContainer.INSTANCE.getProductsPage().openCart();
    }

    @Then("the cart should contain {string}")
    public void the_cart_should_contain(String productName) {
        Assert.assertTrue(PageContainer.INSTANCE.getCartPage().containsProduct(productName));
    }

    @When("User removes {string} from the cart")
    public void user_removes_product_from_the_cart(String productName) {
        PageContainer.INSTANCE.getCartPage().removeProduct(productName);
    }

    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {
        Assert.assertEquals(0, PageContainer.INSTANCE.getCartPage().getItemCount());
    }
}
