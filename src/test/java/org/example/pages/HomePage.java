package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HomePage extends BasePage {
    @FindBy(id = "login2") private WebElement loginLink;
    @FindBy(id = "nameofuser") private WebElement welcomeElement;
    @FindBy(css = ".hrefch:first-child") private WebElement firstProduct;
    @FindBy(linkText = "Laptops") private WebElement laptopsCategory;
    @FindBy(css = ".hrefch") private List<WebElement> productLinks;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openLoginForm() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    public void openFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
    }

    public String getWelcomeMessage() {
        return wait.until(ExpectedConditions.visibilityOf(welcomeElement)).getText();
    }

    public void selectLaptopsCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(laptopsCategory)).click();
    }

    public List<String> getProductCategories() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productLinks));
        return productLinks.stream()
                .map(e -> e.getAttribute("onclick"))
                .filter(Objects::nonNull)
                .map(s -> s.split("'")[1])
                .collect(Collectors.toList());
    }
}