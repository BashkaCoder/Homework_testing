// ProductPage.java
package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = ".btn-success")
    private WebElement addToCartButton;

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        // Ожидаем алерт и принимаем его
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }
}