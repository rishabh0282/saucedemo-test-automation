package com.saucedemo.steps;

import com.saucedemo.pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.saucedemo.core.DriverFactory;

public class SauceDemoSteps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final LoginPage loginPage = new LoginPage();
    private final InventoryPage inventoryPage = new InventoryPage();
    private final CartPage cartPage = new CartPage();
    private final CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage();
    private final CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
    private final CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        loginPage.open();
    }

    @When("I login as {string} with password {string}")
    public void iLoginWithCredentials(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should see the inventory page")
    public void iShouldSeeTheInventoryPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "User is not on inventory page");
        Assert.assertTrue(inventoryPage.getProductCount() > 0, "No products visible on inventory page");
    }

    @Then("I should see a login error message containing {string}")
    public void iShouldSeeALoginErrorMessageContaining(String message) {
        String actual = loginPage.getErrorMessage();
        Assert.assertTrue(actual.contains(message), "Expected login error to contain: " + message + " but was: " + actual);
    }

    @Given("I am logged in as a standard user")
    public void iAmLoggedInAsAStandardUser() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login did not succeed for standard user");
    }

    @When("I add a product to the cart")
    public void iAddAProductToTheCart() {
        inventoryPage.addFirstProductToCart();
    }

    @When("I add two products to the cart")
    public void iAddTwoProductsToTheCart() {
        // Add first two visible products to cart
        var items = driver.findElements(By.className("inventory_item"));
        if (items.size() >= 2) {
            items.stream().limit(2).forEach(item ->
                    item.findElement(By.tagName("button")).click()
            );
        }
    }

    @And("I open the cart")
    public void iOpenTheCart() {
        inventoryPage.openCart();
    }

    @Then("I should see {int} item(s) in the cart")
    public void iShouldSeeItemsInTheCart(Integer expectedCount) {
        Assert.assertEquals(cartPage.getCartItemCount(), expectedCount.intValue(), "Unexpected cart item count");
    }

    @Given("I have a product in the cart as a logged in user")
    public void iHaveAProductInTheCartAsALoggedInUser() {
        iAmLoggedInAsAStandardUser();
        iAddAProductToTheCart();
        iOpenTheCart();
        Assert.assertTrue(cartPage.getCartItemCount() > 0, "Cart is empty");
    }

    @When("I checkout with first name {string}, last name {string}, and postal code {string}")
    public void iCheckoutWithInformation(String firstName, String lastName, String postalCode) {
        cartPage.proceedToCheckout();
        checkoutInformationPage.enterInformation(firstName, lastName, postalCode);
        checkoutInformationPage.continueToOverview();
        if (!firstName.isEmpty() && !lastName.isEmpty() && !postalCode.isEmpty()) {
            checkoutOverviewPage.finishCheckout();
        }
    }

    @Then("I should see the checkout complete message {string}")
    public void iShouldSeeTheCheckoutCompleteMessage(String expectedMessage) {
        String actual = checkoutCompletePage.getCompleteMessage();
        Assert.assertEquals(actual, expectedMessage, "Unexpected checkout complete message");
    }

    @Then("I should see a checkout information error containing {string}")
    public void iShouldSeeACheckoutInformationErrorContaining(String message) {
        String actual = checkoutInformationPage.getErrorMessage();
        Assert.assertTrue(actual.contains(message), "Expected checkout error to contain: " + message + " but was: " + actual);
    }

    @And("I start checkout with information:")
    public void iStartCheckoutWithInformation(io.cucumber.datatable.DataTable dataTable) {
        cartPage.proceedToCheckout();
        var map = dataTable.asMaps().get(0);
        checkoutInformationPage.enterInformation(
                map.get("firstName"),
                map.get("lastName"),
                map.get("postalCode")
        );
        checkoutInformationPage.continueToOverview();
    }

    @And("I finish checkout")
    public void iFinishCheckout() {
        checkoutOverviewPage.finishCheckout();
    }
}


