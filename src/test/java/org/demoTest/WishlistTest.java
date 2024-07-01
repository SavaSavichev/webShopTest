package org.demoTest;

import org.demoTest.model.MainPage;
import org.demoTest.runner.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WishlistTest extends BaseTest {
    @Test
    public void testAddItemToWishList() {
        final String expectedProductName = "Smartphone";

        String actualProductName = new MainPage(getDriver())
                .getHeader()
                .clickCameraPhotoButtonTopMenu()
                .clickSmartphoneButton()
                .addToWishList()
                .getHeader()
                .clickWishlist()
                .getProductName();

        Assert.assertEquals(actualProductName, expectedProductName);
    }

    @Test
    public void testDeleteItemFromWishList() {
        final String expectedText = "The wishlist is empty!";

        String actualText = new MainPage(getDriver())
                .getHeader()
                .clickCameraPhotoButtonTopMenu()
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
