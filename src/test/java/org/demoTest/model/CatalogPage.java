package org.demoTest.model;

import org.demoTest.model.base.BaseMainHeaderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CatalogPage extends BaseMainHeaderPage<CatalogPage> {

    @FindBy(xpath = "//img[@alt='Picture for category Notebooks']")
    private WebElement notebooksButton;

    @FindBy(xpath = "//h2/a[text()='Blue and green Sneaker']")
    private WebElement sneakersButton;

    @FindBy(xpath = "//div[@class='details']//a[text()='14.1-inch Laptop']")
    private WebElement laptopButton;

    @FindBy(xpath = "//img[@alt='Picture of Smartphone']")
    private WebElement smartphoneButton;

    @FindBy(xpath = "//h2/a[@href='/cell-phones']")
    private WebElement cellphonesButton;

    @FindBy(xpath = "//h2//a[text()='Black & White Diamond Heart']")
    private WebElement diamondHeartButton;

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public CatalogPage clickNotebooksButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(notebooksButton)).click();
        return this;
    }

    public ProductPage clickSneakersButton() {
        getWait10().until(ExpectedConditions.visibilityOf(sneakersButton)).click();
        return new ProductPage(getDriver());
    }

    public ProductPage clickLaptopButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(laptopButton)).click();
        return new ProductPage(getDriver());
    }

    public CatalogPage clickCellPhoneButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(cellphonesButton)).click();

        return this;
    }

    public ProductPage clickSmartphoneButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(smartphoneButton)).click();
        return new ProductPage(getDriver());
    }

    public ProductPage clickDiamondHeartButton() {
        getWait10().until(ExpectedConditions.elementToBeClickable(diamondHeartButton)).click();
        return new ProductPage(getDriver());
    }
}
