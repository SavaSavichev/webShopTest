package org.demoTest.model;

import org.demoTest.model.base.BaseMainHeaderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends BaseMainHeaderPage<ProductPage> {

    @FindBy(xpath = "//strong[@class='warning']")
    private WebElement warningSearchMessage;

    @FindBy(xpath = "//h2[@class='product-title']")
    private List<WebElement> listOfSearchedItems;

    @FindBy(xpath = "//h2[@class='product-title']")
    private WebElement firstItemFromSearchList;

    @FindBy(xpath = "//select[@id='products-orderby']")
    private WebElement sortBySelect;

    @FindBy(xpath = "//input[@class='button-2 product-box-add-to-cart-button']")
    private WebElement addToCartFirstItemButton;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public SearchPage clickAddToCart() {
        getWait10().until(ExpectedConditions.visibilityOf(sortBySelect));
        getWait10().until(ExpectedConditions.visibilityOf(addToCartFirstItemButton)).click();

        return this;
    }

    public String getWarningSearchMessage() {
        return getWait5().until(ExpectedConditions.visibilityOf(warningSearchMessage)).getText().trim();
    }

    public String getFirstItemFromSearchList() {
        getWait10().until(ExpectedConditions.visibilityOf(sortBySelect));

        return getWait10().until(ExpectedConditions.visibilityOf(firstItemFromSearchList)).getText().trim();
    }

    public ProductPage clickFirstItemFromSearchList() {
        getWait10().until(ExpectedConditions.visibilityOf(sortBySelect));
        getWait10().until(ExpectedConditions.visibilityOf(firstItemFromSearchList)).click();

        return new ProductPage(getDriver());
    }

    public List<String> getListOfSearchedItems() {
        getWait10().until(ExpectedConditions.visibilityOf(sortBySelect));

        List<WebElement> elements = getWait10()
                .until(ExpectedConditions.visibilityOfAllElements(listOfSearchedItems));

        List<String> resultItemsList = new ArrayList<>();
        for (WebElement element : elements) {
            resultItemsList.add(element.getText().trim());
        }

        return resultItemsList;
    }

    public boolean isItemPresentInSearchResults(String itemName) {
        getWait10().until(ExpectedConditions.visibilityOf(sortBySelect));

        getWait10().until(ExpectedConditions.visibilityOf(firstItemFromSearchList));

        List<String> titles = getWait10().until(ExpectedConditions.visibilityOfAllElements(listOfSearchedItems))
                .stream()
                .map(e -> e.getText().trim())
                .toList();

        return titles.contains(itemName);
    }

    public SearchPage sortByForSearchList(String param) {
        getWait10().until(ExpectedConditions.visibilityOf(sortBySelect));
        Select sizeSelect = new Select(sortBySelect);
        sizeSelect.selectByVisibleText(param);

        getWait5().until(driver -> sizeSelect.getFirstSelectedOption().getText().equals(param));
        return this;
    }
}
