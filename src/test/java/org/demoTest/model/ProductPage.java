package org.demoTest.model;

import org.demoTest.model.base.BaseMainHeaderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BaseMainHeaderPage<ProductPage> {

    @FindBy(xpath = "//h1")
    private WebElement itemName;

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

    @FindBy(xpath = "//p[@class='content']")
    private WebElement noticeBar;

    @FindBy(xpath = "//div[@class='product-name']/h1")
    private WebElement productName;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage addToCart() {
        getWait5().until(ExpectedConditions.elementToBeClickable(addToCardButton)).click();
        waitNoticeBarIsVisible();
        return this;
    }

    private void waitNoticeBarIsVisible() {
        getWait5().until(ExpectedConditions.visibilityOf(noticeBar));
    }

    public boolean getFreeShipping() {
        return freeShipping.getText().contains("Free shipping");
    }

    public ProductPage setShoeSize(String size) {
        getWait10().until(ExpectedConditions.visibilityOf(shoeSize));
        Select sizeSelect = new Select(shoeSize);
        sizeSelect.selectByVisibleText(size);

        getWait5().until(driver -> sizeSelect.getFirstSelectedOption().getText().equals(size));
        return this;
    }

    public ProductPage setShoeColor(String color) {
        getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(
                By.xpath("//span[@title='" + color + "']")))).click();
        return this;
    }

    public ProductPage addToWishList() {
        getWait10().until(ExpectedConditions.elementToBeClickable(addToWishListButton)).click();
        waitNoticeBarIsVisible();
        return this;
    }

    public ReviewPage clickAddReviewButton() {
        addReviewButton.click();
        return new ReviewPage(getDriver());
    }

    public String getItemName() {
        return getWait10().until(ExpectedConditions.visibilityOf(productName)).getText().trim();
    }
}
