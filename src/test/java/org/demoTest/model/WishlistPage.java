package org.demoTest.model;

import org.demoTest.model.base.BaseMainHeaderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishlistPage extends BaseMainHeaderPage<CartPage> {

    @FindBy(xpath = "//td[@class='product']")
    private WebElement productName;

    @FindBy(xpath = "//input[@name='removefromcart']")
    private WebElement removeButton;

    @FindBy(xpath = "//input[@name='addtocart']")
    private WebElement addToCartCheckBox;

    @FindBy(xpath = "//input[@name='addtocartbutton']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//input[@name='updatecart']")
    private WebElement updateButton;

    @FindBy(xpath = "//div[@class='wishlist-content']")
    private WebElement emptyWishlistText;

    @FindBy(xpath = "//input[@class='qty-input']")
    private WebElement quantityInput;

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return productName.getText();
    }

    public WishlistPage removeItemFromWishlist() {
        removeButton.click();
        return this;
    }

    public WishlistPage clickUpdateWishlistButton() {
        updateButton.click();
        return this;
    }

    public String getEmptyWishlistText() {
        return emptyWishlistText.getText();
    }

    public CartPage addToCartFromWishlist() {
        addToCartCheckBox.click();
        addToCartButton.click();
        return new CartPage(getDriver());
    }

    public WishlistPage setItemQuantity(int quantity) {
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
        return this;
    }

    public int getItemQuantity() {
        return Integer.parseInt(quantityInput.getAttribute("value"));
    }
}
