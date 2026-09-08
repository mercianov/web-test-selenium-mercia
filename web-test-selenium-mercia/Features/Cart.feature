Feature: Shopping Cart

  Background:
    Given User is logged in as "standard_user"

  @tag1
  Scenario: View cart after adding a product
    When User adds "Sauce Labs Backpack" to the cart
    And User views the cart
    Then the cart should contain "Sauce Labs Backpack"

  @tag2
  Scenario: Edit cart by removing a product
    Given User has added "Sauce Labs Backpack" to the cart
    When User views the cart
    And User removes "Sauce Labs Backpack" from the cart
    Then the cart should be empty
