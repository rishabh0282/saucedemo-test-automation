package com.saucedemo.pages;

import com.saucedemo.core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public int getCartItemCount() {
        return cartItems.size();
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }
}


