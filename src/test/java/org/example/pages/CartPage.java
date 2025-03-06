package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {
    @FindBy(id = "cartur") private WebElement cartLink;
    @FindBy(id = "tbodyid") private WebElement cartTable;
    @FindBy(css = "a[onclick*='deleteItem']") private WebElement deleteButton;

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    public void removeProductFromCart() {
        deleteProduct();
        handleAlert();
        waitForCartUpdate();
    }

    public boolean isCartEmpty() {
        return wait.until(d -> cartTable.findElements(By.tagName("tr")).isEmpty());
    }

    private void deleteProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
    }

    private void handleAlert() {
        try {
            wait.withTimeout(Duration.ofSeconds(3))
                    .until(ExpectedConditions.alertIsPresent())
                    .accept();
        } catch (TimeoutException e) {
            System.out.println("Alert not present");
        }
    }

    private void waitForCartUpdate() {
        wait.until(d -> cartTable.findElements(By.tagName("tr")).isEmpty());
    }
}