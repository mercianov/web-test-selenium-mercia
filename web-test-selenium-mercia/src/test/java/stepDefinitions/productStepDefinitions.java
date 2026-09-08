package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObjects.PageContainer;

public class productStepDefinitions {

    @Then("User should see {int} products listed")
    public void user_should_see_products_listed(int expectedCount) {
        Assert.assertEquals(expectedCount, PageContainer.INSTANCE.getProductsPage().getProductCount());
    }

    @When("User clicks on the product {string}")
    public void user_clicks_on_the_product(String productName) {
        PageContainer.INSTANCE.getProductsPage().openProductDetails(productName);
    }

    @Then("User should see the product details page for {string}")
    public void user_should_see_the_product_details_page_for(String productName) {
        Assert.assertEquals(productName, PageContainer.INSTANCE.getProductDetailPage().getProductName());
    }
}
