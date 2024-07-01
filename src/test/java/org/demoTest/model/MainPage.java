package org.demoTest.model;

import org.demoTest.model.base.BaseMainHeaderPage;
import org.demoTest.runner.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BaseMainHeaderPage<MainPage> {

    @FindBy(xpath = "//h2[@class = 'topic-html-content-header']")
    private WebElement contentHeader;

    @FindBy(xpath = "//div[@class='block block-category-navigation']//a[@href='/computers']")
    private WebElement computersButtonSideMenu;

    @FindBy(xpath = "//div[@class='block block-category-navigation']//a[@href='/apparel-shoes']")
    private WebElement apparelShoesButtonSideMenu;

    @FindBy(xpath = "//div[@class='item-box']//img[@title='Show details for 14.1-inch Laptop']")
    private WebElement laptopIcon;

    @FindBy(xpath = "//div[@class='product-item']//img[@alt='Picture of Build your own cheap computer']")
    private WebElement ownCheapComputer;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getContentHeader() {
        return contentHeader.getText();
    }

    public CatalogPage clickComputersButtonSideMenu() {
        computersButtonSideMenu.click();
        return new CatalogPage(getDriver());
    }

    public CatalogPage clickApparelShoesButtonSideMenu() {
        apparelShoesButtonSideMenu.click();
        return new CatalogPage(getDriver());
    }

    public ProductPage clickLaptopFromFeatured() {
        TestUtils.scrollWithPauseByActions(new ProductPage(getDriver()), laptopIcon, 1500);
        getWait2().until(ExpectedConditions.visibilityOf(laptopIcon)).click();
        return new ProductPage(getDriver());
    }

    public ProductPage clickOwnCheapComputer() {
        TestUtils.scrollWithPauseByActions(new ProductPage(getDriver()), ownCheapComputer, 1500);
        getWait2().until(ExpectedConditions.visibilityOf(ownCheapComputer)).click();
        return new ProductPage(getDriver());
    }
}
