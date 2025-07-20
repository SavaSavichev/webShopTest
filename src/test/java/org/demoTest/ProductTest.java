package org.demoTest;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.demoTest.model.MainPage;
import org.demoTest.runner.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("UI")
    @Description("Verifying that the user sees the free shipping label on the product page")
    public void testFreeShippingLabelDisplay() {
        boolean isFreeShippingLabelDisplay = new MainPage(getDriver())
                .clickOwnCheapComputer()
                .getFreeShipping();

        Assert.assertTrue(isFreeShippingLabelDisplay, "The free shipping label does not appear on the product page!");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verifying that the user can return to the main page from the product page")
    public void testReturnBackToMainPage() {
        final String welcomeText = "Welcome to our store";

        String textFromMainPage = new MainPage(getDriver())
                .clickOwnCheapComputer()
                .getHeader()
                .clickLogo()
                .getContentHeader();

        Assert.assertEquals(textFromMainPage, welcomeText);
    }

    @Ignore
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verifying that the user can set the size of product(shoes) on the product page")
    public void testSetShoeSize() {
        final String value = "27";
        final String size = "10";

        String actualProductSize = new MainPage(getDriver())
                .clickApparelShoesButtonSideMenu()
                .clickSneakersButton()
                .setShoeSize(size)
                .addToCart()
                .getHeader()
                .clickShoppingCart()
                .getProductSize();

        Assert.assertEquals(actualProductSize, size);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verifying that the user can set the color of product(shoes) on the product page")
    public void testSetShoeColor() {
        final String color = "Green";

        String actualProductColor = new MainPage(getDriver())
                .clickApparelShoesButtonSideMenu()
                .clickSneakersButton()
                .setShoeColor(color)
                .addToCart()
                .getHeader()
                .clickShoppingCart()
                .getProductColor();

        Assert.assertEquals(actualProductColor, color);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verifying that the user can add a review about the product on the review page")
    public void testAddReview() {
        final String email = "testovoemylo@mulo.com";
        final String password = "123456";
        final String reviewTitle = "New title";
        final String reviewText = "New review text";
        final String expectedMessageText = "Product review is successfully added.";

        String actualMessageText = new MainPage(getDriver())
                .getHeader()
                .clickLoginButton()
                .loginUser(email, password)
                .clickLaptopFromFeatured()
                .clickAddReviewButton()
                .addReview(reviewTitle, reviewText)
                .getReviewAddResultMessageText();

        Assert.assertEquals(actualMessageText, expectedMessageText);
    }
}

