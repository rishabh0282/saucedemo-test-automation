package com.saucedemo.pages;

import com.saucedemo.core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    public int getProductCount() {
        return inventoryItems.size();
    }

    public void addFirstProductToCart() {
        if (!inventoryItems.isEmpty()) {
            WebElement firstItem = inventoryItems.get(0);
            WebElement addButton = firstItem.findElement(org.openqa.selenium.By.tagName("button"));
            addButton.click();
        }
    }

    public void openCart() {
        cartLink.click();
    }
}


