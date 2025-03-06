package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void verifySuccessfulUserLogin() {
        homePage.openLoginForm();
        loginPage.performLogin("1234", "1234");

        Assert.assertTrue(
                homePage.getWelcomeMessage().contains("Welcome 1234"),
                "Welcome message validation failed"
        );
    }
}