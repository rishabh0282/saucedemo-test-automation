Feature: SauceDemo checkout flows
  As a SauceDemo customer
  I want to log in, add products to cart, and complete checkout
  So that I can purchase items successfully

  @smoke @login @functional
  Scenario: Successful login with standard user
    Given I am on the login page
    When I login as "standard_user" with password "secret_sauce"
    Then I should see the inventory page

  @regression @login @negative
  Scenario: Login with invalid password
    Given I am on the login page
    When I login as "standard_user" with password "wrong_password"
    Then I should see a login error message containing "Username and password do not match"

  @regression @login @negative
  Scenario: Login with locked out user
    Given I am on the login page
    When I login as "locked_out_user" with password "secret_sauce"
    Then I should see a login error message containing "Sorry, this user has been locked out"

  @smoke @cart @functional
  Scenario: Add single item to cart
    Given I am logged in as a standard user
    When I add a product to the cart
    And I open the cart
    Then I should see 1 item in the cart

  @regression @cart
  Scenario: Add multiple items to cart
    Given I am logged in as a standard user
    When I add two products to the cart
    And I open the cart
    Then I should see 2 items in the cart

  @smoke @checkout @functional
  Scenario: Complete checkout with valid customer information
    Given I have a product in the cart as a logged in user
    When I checkout with first name "John", last name "Doe", and postal code "12345"
    Then I should see the checkout complete message "Thank you for your order!"

  @regression @checkout @negative
  Scenario: Checkout with missing first name
    Given I have a product in the cart as a logged in user
    When I checkout with first name "", last name "Doe", and postal code "12345"
    Then I should see a checkout information error containing "First Name is required"

  @regression @checkout @negative
  Scenario: Checkout with missing last name
    Given I have a product in the cart as a logged in user
    When I checkout with first name "John", last name "", and postal code "12345"
    Then I should see a checkout information error containing "Last Name is required"

  @regression @checkout @negative
  Scenario: Checkout with missing postal code
    Given I have a product in the cart as a logged in user
    When I checkout with first name "John", last name "Doe", and postal code ""
    Then I should see a checkout information error containing "Postal Code is required"

  @sanity @smoke @end2end
  Scenario: Complete end-to-end purchase flow
    Given I am on the login page
    When I login as "standard_user" with password "secret_sauce"
    And I add a product to the cart
    And I open the cart
    And I start checkout with information:
      | firstName | lastName | postalCode |
      | John      | Doe      | 12345      |
    And I finish checkout
    Then I should see the checkout complete message "Thank you for your order!"


