package org.demoTest.model;

import org.demoTest.model.base.BaseMainHeaderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BaseMainHeaderPage<ProductPage> {

    @FindBy(xpath = "//strong[@class='warning']")
    private WebElement warningSearchMessage;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public String getWarningSearchMessage() {
        return getWait5().until(ExpectedConditions.visibilityOf(warningSearchMessage)).getText().trim();
    }
}
