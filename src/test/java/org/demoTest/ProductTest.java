package org.demoTest;

import org.demoTest.model.MainPage;
import org.demoTest.runner.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test
    public void testFreeShippingLabelDisplay() {
        boolean isFreeShippingLabelDisplay = new MainPage(getDriver())
                .clickOwnCheapComputer()
                .getFreeShipping();

        Assert.assertTrue(isFreeShippingLabelDisplay);
    }

    @Test
    public void testCheckBackToMainPage() {
        final String welcomeText = "Welcome to our store";

        String textFromMainPage = new MainPage(getDriver())
                .clickOwnCheapComputer()
                .getHeader()
                .clickLogo()
                .getContentHeader();

        Assert.assertEquals(textFromMainPage, welcomeText);
    }

    @Test
    public void testSetShoeSize() {
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
    public void testAddReview() {
        final String email = "testovoemylo@mulo.com";
        final String password = "123456";
        final String reviewTitle = "New title";
        final String reviewText = "New review text";
        final String expectedMessageText = "Product review is successfully added.";

        String actualMessageText = new MainPage(getDriver())
                .getHeader()
                .clickLoginButton()
                .login(email, password)
                .clickLaptopFromFeatured()
                .clickAddReviewButton()
                .addReview(reviewTitle, reviewText)
                .getReviewAddResultMessageText();

        Assert.assertEquals(actualMessageText, expectedMessageText);
    }
}

