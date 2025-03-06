package org.example.tests;

import org.example.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected ProductPage productPage;
    protected CartPage cartPage;

    @BeforeMethod
    public void testSetup() {
        initializeDriver();
        initializePages();
        maximizeWindow();
        navigateToHomePage();
    }

    private void initializeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\1\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private void initializePages() {
        homePage = new HomePage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        productPage = new ProductPage(driver, wait);
        cartPage = new CartPage(driver, wait);
    }

    private void maximizeWindow() {
        driver.manage().window().maximize();
    }

    private void navigateToHomePage() {
        driver.get("https://www.demoblaze.com");
    }

    @AfterMethod
    public void testCleanup() {
        if (driver != null) {
            driver.quit();
        }
    }
}