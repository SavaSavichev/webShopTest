package org.demoTest.model;

import org.demoTest.model.base.BaseMainHeaderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BaseMainHeaderPage<ProductPage> {

    @FindBy(xpath = "//input[@class='button-1 add-to-cart-button']")
    private WebElement addToCardButton;

    @FindBy(xpath = "//div[@class='overview']")
    private WebElement freeShipping;

    @FindBy(xpath = "//select[@id='product_attribute_28_7_10']")
    private WebElement shoeSize;

    @FindBy(xpath = "//input[@value='Add to wishlist']")
    private WebElement addToWishListButton;

    @FindBy(xpath = "//a[text()='Add your review']")
    private WebElement addReviewButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage addToCart() {
        addToCardButton.click();
        return this;
    }

    public boolean getFreeShipping() {
        return freeShipping.getText().contains("Free shipping");
    }

    public ProductPage setShoeSize(String size) {
        Select sizeSelect = new Select(shoeSize);
        sizeSelect.selectByVisibleText(size);
        return this;
    }

    public ProductPage setShoeColor(String color) {
        getDriver().findElement(By.xpath("//span[@title='" + color + "']")).click();
        return this;
    }

    public ProductPage addToWishList() {
        addToWishListButton.click();
        return this;
    }

    public ReviewPage clickAddReviewButton() {
        addReviewButton.click();
        return new ReviewPage(getDriver());
    }
}
