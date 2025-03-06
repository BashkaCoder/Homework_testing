// ProductTest.java
package org.example.tests;

import org.example.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class ProductTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\1\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Увеличено время ожидания
        homePage = new HomePage(driver, wait);
        productPage = new ProductPage(driver, wait);
        cartPage = new CartPage(driver, wait);
    }

    @Test
    public void testDeleteFromCart() {
        homePage.clickFirstProduct();
        productPage.addToCart();
        cartPage.openCart();
        cartPage.deleteProduct();
        Assert.assertTrue(cartPage.isCartEmpty(), "Корзина не пуста!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}