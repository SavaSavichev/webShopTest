package org.demoTest;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.demoTest.model.MainPage;
import org.demoTest.runner.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WishlistTest extends BaseTest {
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Checking the user's ability to add a product to a wishlist from the product page")
    public void testAddItemToWishList() {
        final String expectedProductName = "Smartphone";

        String actualProductName = new MainPage(getDriver())
                .getHeader()
                .clickCameraPhotoButtonTopMenu()
                .clickCellPhoneButton()
                .clickSmartphoneButton()
                .addToWishList()
                .getHeader()
                .clickWishlist()
                .getProductName();

        Assert.assertEquals(actualProductName, expectedProductName);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Checking the user's ability to remove a product from the wishlist from the wishlist page")
    public void testDeleteItemFromWishList() {
        final String expectedText = "The wishlist is empty!";

        String actualText = new MainPage(getDriver())
                .getHeader()
                .clickCameraPhotoButtonTopMenu()
                .clickCellPhoneButton()
                .clickSmartphoneButton()
                .addToWishList()
                .getHeader()
                .clickWishlist()
                .removeItemFromWishlist()
                .clickUpdateWishlistButton()
                .getEmptyWishlistText();

        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Checking the user's ability to add an item from the wishlist to the cart")
    public void testAddItemFromWishListToCart() {
        final String expectedText = "Black & White Diamond Heart";

        String actualText = new MainPage(getDriver())
                .getHeader()
                .clickJewelryButtonTopMenu()
                .clickDiamondHeartButton()
                .addToWishList()
                .getHeader()
                .clickWishlist()
                .addToCartFromWishlist()
                .getProductName();

        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Checking the user's ability to increase the quantity of a product on the wishlist page")
    public void testIncreaseQuantity() {
        final int itemQuantity = 2;

        int actualItemQuantity = new MainPage(getDriver())
                .getHeader()
                .clickJewelryButtonTopMenu()
                .clickDiamondHeartButton()
                .addToWishList()
                .getHeader()
                .clickWishlist()
                .setItemQuantity(itemQuantity)
                .clickUpdateWishlistButton()
                .getItemQuantity();

        Assert.assertEquals(actualItemQuantity, itemQuantity);
    }
}
