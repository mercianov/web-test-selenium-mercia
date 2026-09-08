Feature: Checkout

  Background:
    Given User is logged in as "standard_user"
    And User has added "Sauce Labs Backpack" to the cart

  @tag1
  Scenario: Successfully checkout a product
    When User views the cart
    And User proceeds to checkout
    And User fills in checkout information "John" "Doe" "12345"
    And User completes the checkout
    Then User should see the order confirmation "Thank you for your order!"
