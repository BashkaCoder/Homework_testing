package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    @FindBy(id = "loginusername") private WebElement usernameField;
    @FindBy(id = "loginpassword") private WebElement passwordField;
    @FindBy(xpath = "//button[contains(text(),'Log in')]") private WebElement loginButton;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void performLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        submitLogin();
    }

    private void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
    }

    private void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
    }

    private void submitLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
}