Feature: Login

  @tag1
  Scenario: Login success with valid credentials
    Given User is on the SauceDemo login page
    When User logs in with username "standard_user" and password "secret_sauce"
    Then User should see the products page

  @tag2
  Scenario: Login fail with invalid credentials
    Given User is on the SauceDemo login page
    When User logs in with username "invalid_user" and password "wrong_password"
    Then User should see an error message "Epic sadface: Username and password do not match any user in this service"
