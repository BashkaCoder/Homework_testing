package org.example.tests;

import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\1\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Опционально: для запуска без GUI
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver, wait);
        loginPage = new LoginPage(driver, wait);
    }

    @Test
    public void testSuccessfulLogin() {
        // Открытие формы авторизации
        homePage.clickLoginLink();

        // Заполнение данных
        loginPage.enterUsername("1234");
        loginPage.enterPassword("1234");
        loginPage.clickLoginButton();

        // Проверка приветствия
        wait.until(ExpectedConditions.visibilityOf(homePage.getWelcomeElement()));
        Assert.assertTrue(homePage.getWelcomeText().contains("Welcome 1234"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}