package org.demoTest;

import io.qameta.allure.*;
import org.demoTest.model.CartPage;
import org.demoTest.model.MainPage;
import org.demoTest.runner.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private final static String EMAIL = "testovoemylo@mulo.com";
    private final static String PASSWORD = "123456";

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verifying that the user can add an item(laptop) to the cart from the main page")
    public void testAddLaptopToCardFromMainPage() {
        final String itemName = "14.1-inch Laptop";

        String itemInCartName = new MainPage(getDriver())
                .clickLaptopFromFeatured()
                .addToCart()
                .getHeader()
                .clickShoppingCart()
                .getProductName();

        Assert.assertEquals(itemInCartName, itemName);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verifying that the user can add an item(laptop) to the cart from the product page")
    public void testAddLaptopToCardFromProductPage() {
        final String itemName = "14.1-inch Laptop";

        String itemInCartName = new MainPage(getDriver())
                .clickComputersButtonSideMenu()
                .clickNotebooksButton()
                .clickLaptopButton()
                .addToCart()
                .getHeader()
                .clickShoppingCart()
                .getProductName();

        Assert.assertEquals(itemInCartName, itemName);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verifying that the user can increase the number of items in the cart")
    public void testIncreaseQuantity() {
        final int itemQuantity = 2;

        int actualItemQuantity = new MainPage(getDriver())
                .clickLaptopFromFeatured()
                .addToCart()
                .getHeader()
                .clickShoppingCart()
                .setItemQuantity(itemQuantity)
                .clickUpdateCartButton()
                .getItemQuantity();

        Assert.assertEquals(actualItemQuantity, itemQuantity);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verifying that the total price will increase as the number of items in the cart increases")
    public void testIncreaseTotalPriceDependsQuantity() {
        final int itemQuantity = 2;

        double unitPrice = new MainPage(getDriver())
                .clickLaptopFromFeatured()
                .addToCart()
                .getHeader()
                .clickShoppingCart()
                .setItemQuantity(itemQuantity)
                .clickUpdateCartButton()
                .getUnitItemPrice();

        double actualTotalPrice = new CartPage(getDriver())
                .getTotalItemPrice();

        double expectedTotalPrice = unitPrice * itemQuantity;

        Assert.assertEquals(actualTotalPrice, expectedTotalPrice);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Verifying that the user sees a warning box on the cart page")
    public void testWarningTermsBox() {
        final String expectedResultText = "Please accept the terms of service before the next step.";

        String actualAlertText = new MainPage(getDriver())
                .getHeader()
                .clickLoginButton()
                .loginUser(EMAIL, PASSWORD)
                .clickLaptopFromFeatured()
                .addToCart()
                .getHeader()
                .clickShoppingCart()
                .clickCheckoutButton()
                .getWarningBoxText();

        Assert.assertEquals(actualAlertText, expectedResultText);
    }
}
