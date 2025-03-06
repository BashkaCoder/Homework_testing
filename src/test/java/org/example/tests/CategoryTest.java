package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class CategoryTest extends BaseTest {
    @Test
    public void verifyLaptopsCategoryProducts() {
        homePage.selectLaptopsCategory();
        List<String> categories = homePage.getProductCategories();

        Assert.assertTrue(
                categories.stream().allMatch(cat -> cat.equals("notebook")),
                "Non-laptop products found in Laptops category"
        );
    }
}