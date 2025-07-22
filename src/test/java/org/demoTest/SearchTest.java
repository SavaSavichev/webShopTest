package org.demoTest;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.demoTest.model.MainPage;
import org.demoTest.runner.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

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
}
