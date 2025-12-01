package com.saucedemo.pages;

import com.saucedemo.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    private WebDriverWait wait() {
        return new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
    }

    public int getProductCount() {
        return inventoryItems.size();
    }

    public void addFirstProductToCart() {
        // Wait for at least one inventory item to be visible
        wait().until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item")));
        if (!inventoryItems.isEmpty()) {
            WebElement firstItem = inventoryItems.get(0);
            WebElement addButton = firstItem.findElement(By.tagName("button"));
            addButton.click();
        }
    }

    public void openCart() {
        cartLink.click();
    }
}


