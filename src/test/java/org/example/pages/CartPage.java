// CartPage.java
package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "cartur")
    private WebElement cartLink;

    @FindBy(id = "tbodyid")
    private WebElement cartTable;

    @FindBy(css = "a[onclick*='deleteItem']")
    private WebElement deleteButton;

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    public void deleteProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();

        try {
            // Ожидаем алерт не более 3 секунд
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            shortWait.until(ExpectedConditions.alertIsPresent()).accept();
        } catch (TimeoutException e) {
            System.out.println("Алерт не появился");
        }

        // Ожидаем обновление корзины
        wait.until(d -> cartTable.findElements(By.tagName("tr")).isEmpty());
    }

    public boolean isCartEmpty() {
        return cartTable.findElements(By.tagName("tr")).isEmpty();
    }
}