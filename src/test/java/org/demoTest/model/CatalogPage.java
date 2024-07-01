package org.demoTest.model;

import org.demoTest.model.base.BaseMainHeaderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CatalogPage extends BaseMainHeaderPage<CatalogPage> {

    @FindBy(xpath = "//img[@alt='Picture for category Notebooks']")
    private WebElement notebooksButton;

    @FindBy(xpath = "//img[@alt='Picture of Blue and green Sneaker']")
    private WebElement sneakersButton;

    @FindBy(xpath = "//div[@class='details']//a[text()='14.1-inch Laptop']")
    private WebElement laptopButton;

    @FindBy(xpath = "//img[@alt='Picture of Smartphone']")
    private WebElement smartphoneButton;

    @FindBy(xpath = "//h2//a[text()='Black & White Diamond Heart']")
    private WebElement diamondHeartButton;

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public CatalogPage clickNotebooksButton() {
        notebooksButton.click();
        return this;
    }

    public ProductPage clickSneakersButton() {
        sneakersButton.click();
        return new ProductPage(getDriver());
    }

    public ProductPage clickLaptopButton() {
        laptopButton.click();
        return new ProductPage(getDriver());
    }

    public ProductPage clickSmartphoneButton() {
        smartphoneButton.click();
        return new ProductPage(getDriver());
    }

    public ProductPage clickDiamondHeartButton() {
        diamondHeartButton.click();
        return new ProductPage(getDriver());
    }
}
