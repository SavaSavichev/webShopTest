package org.demoTest.model;

import org.demoTest.model.base.BaseMainHeaderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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
        getWait5().until(ExpectedConditions.elementToBeClickable(addToCardButton)).click();
        return this;
    }

    public boolean getFreeShipping() {
        return freeShipping.getText().contains("Free shipping");
    }

    public ProductPage setShoeSize(String size) {
        getWait10().until(ExpectedConditions.visibilityOf(shoeSize));

        Select select = new Select(shoeSize);
        int indexToSelect = -1;
        List<WebElement> options = select.getOptions();
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).getText().trim().equals(size)) {
                indexToSelect = i;
                break;
            }
        }

        if (indexToSelect == -1) {
            throw new RuntimeException("Size option not found: " + size);
        }

        String script = """
        arguments[0].selectedIndex = arguments[1];
        arguments[0].dispatchEvent(new Event('input', { bubbles: true }));
        arguments[0].dispatchEvent(new Event('change', { bubbles: true }));
    """;

        ((JavascriptExecutor) getDriver()).executeScript(script, shoeSize, indexToSelect);

        getWait5().until(driver -> new Select(shoeSize).getFirstSelectedOption().getText().equals(size));

        return this;
    }

    public ProductPage setShoeColor(String color) {
        getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(
                By.xpath("//span[@title='" + color + "']")))).click();
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
