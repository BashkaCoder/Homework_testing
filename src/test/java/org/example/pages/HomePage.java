// HomePage.java
package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "login2")
    private WebElement loginLink;

    @FindBy(id = "nameofuser")
    private WebElement welcomeElement;

    @FindBy(css = ".hrefch:first-child")
    private WebElement firstProduct;

    @FindBy(linkText = "Laptops") // Локатор категории "Laptops"
    private WebElement laptopsCategory;

    @FindBy(css = ".hrefch")
    private List<WebElement> productLinks;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    public void clickFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
    }

    public WebElement getWelcomeElement() {
        return welcomeElement;
    }

    // Добавьте этот метод
    public String getWelcomeText() {
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
                .map(s -> s.split("'")[1]) // Извлекаем значение категории
                .collect(Collectors.toList());
    }
}