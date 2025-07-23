package org.demoTest;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.demoTest.model.MainPage;
import org.demoTest.runner.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchTest extends BaseTest {
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Search")
    @Description("Verify that a warning message is displayed when the search query is shorter than 3 characters")
    public void testSearchWithShortText() {
        final String searchText = "it";
        final String expectedWarningMessage = "Search term minimum length is 3 characters";

        String actualSearchMessage = new MainPage(getDriver())
                .getHeader()
                .searchItem(searchText)
                .getWarningSearchMessage();

        Assert.assertEquals(actualSearchMessage, expectedWarningMessage);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Search")
    @Description("Verify that a product can be found using its full name in the search input")
    public void testSearchItemWithFullName() {
        final String expectedItem = "High Definition 3D Camcorder";

        String actualItem = new MainPage(getDriver())
                .getHeader()
                .searchItem(expectedItem)
                .getFirstItemFromSearchList();

        Assert.assertEquals(actualItem, expectedItem);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Search")
    @Description("Verify that all relevant products are displayed when searching by a keyword")
    public void testSearchListOfItems() {
        final String wordForSearch = "phone";
        final List<String> expectedItemsList = new ArrayList<>(
                List.of("Genuine Leather Handbag with Cell Phone Holder & Many Pockets",
                "Phone Cover", "Smartphone", "Used phone")
        );

        List<String> actualItemsList = new MainPage(getDriver())
                .getHeader()
                .searchItem(wordForSearch)
                .getListOfSearchedItems();

        Collections.sort(expectedItemsList);
        Collections.sort(actualItemsList);

        Assert.assertEquals(actualItemsList, expectedItemsList);
    }

    @DataProvider(name="searchData")
    public Object[][] getSearchData() {
        return new Object[][] {
                {"smartphone"},
                {"Smartphone"},
                {"SMARTPHONE"}
        };
    }
    @Test(dataProvider = "searchData")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Search")
    @Description("Verify that the search functionality is case-insensitive and returns the correct product regardless of text casing")
    public void testSearchIsCaseInsensitive(String itemName) {
        final String expectedItem = "Smartphone";

        boolean searchResult = new MainPage(getDriver())
                .getHeader()
                .searchItem(itemName)
                .isItemPresentInSearchResults(expectedItem);

        Assert.assertTrue(searchResult, "Search results do not contain the expected item: " + itemName);
    }
}
