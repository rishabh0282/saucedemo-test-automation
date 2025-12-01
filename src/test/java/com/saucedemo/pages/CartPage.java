package com.saucedemo.pages;

import com.saucedemo.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    private WebDriverWait wait() {
        return new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
    }

    public int getCartItemCount() {
        // Ensure cart items are loaded before counting
        wait().until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_item")));
        return cartItems.size();
    }

    public void proceedToCheckout() {
        wait().until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutButton.click();
    }
}


