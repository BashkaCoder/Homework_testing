package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {
    @Test
    public void verifyProductRemovalFromCart() {
        homePage.openFirstProduct();
        productPage.addProductToCart();
        cartPage.openCart();
        cartPage.removeProductFromCart();

        Assert.assertTrue(cartPage.isCartEmpty(), "Cart is not empty after removal");
    }
}