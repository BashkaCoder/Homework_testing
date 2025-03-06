package org.example.tests;

import org.example.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.List;

public class CategoryTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\1\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver, wait);
    }

    @Test
    public void testLaptopsCategory() {
        // Выбираем категорию "Laptops"
        homePage.selectLaptopsCategory();

        // Получаем категории всех товаров
        List<String> categories = homePage.getProductCategories();

        // Проверяем, что все товары относятся к категории 'notebook'
        boolean allNotebooks = categories.stream()
                .allMatch(cat -> cat.equals("notebook"));

        Assert.assertTrue(allNotebooks, "В категории 'Laptops' есть посторонние товары");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}