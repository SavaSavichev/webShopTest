package org.demoTest.model;

import org.demoTest.model.base.BaseMainHeaderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BaseMainHeaderPage<ProductPage> {

    @FindBy(xpath = "//strong[@class='warning']")
    private WebElement warningSearchMessage;

    @FindBy(xpath = "//h2[@class='product-title']")
    private List<WebElement> listOfSearchedItems;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public String getWarningSearchMessage() {
        return getWait5().until(ExpectedConditions.visibilityOf(warningSearchMessage)).getText().trim();
    }

    public String getFirstItemFromSearchList() {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[@class='product-title']"))).getText().trim();
    }

    public List<String> getListOfSearchedItems() {
        List<String> resultItemsList = new ArrayList<>();
        getWait5().until(ExpectedConditions.visibilityOfAllElements(listOfSearchedItems));

        for (WebElement listOfSearchedItem : listOfSearchedItems) {
            resultItemsList.add(listOfSearchedItem.getText().trim());
        }

        return resultItemsList;
    }

    public boolean isItemPresentInSearchResults(String itemName) {
        List<WebElement> searchElements = getWait10().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//h2[@class='product-title']")));
        return searchElements.stream()
                .map(e -> e.getText().trim())
                .anyMatch(name -> name.equals(itemName));
    }
}
