Feature: View Products

  Background:
    Given User is logged in as "standard_user"

  @tag1
  Scenario: View product list
    Then User should see 6 products listed

  @tag2
  Scenario: View product details
    When User clicks on the product "Sauce Labs Backpack"
    Then User should see the product details page for "Sauce Labs Backpack"
