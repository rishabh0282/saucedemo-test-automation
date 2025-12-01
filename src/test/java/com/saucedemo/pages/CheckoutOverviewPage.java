package com.saucedemo.pages;

import com.saucedemo.core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOverviewPage extends BasePage {

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    public void finishCheckout() {
        finishButton.click();
    }

    public String getTotalText() {
        return totalLabel.getText();
    }
}


