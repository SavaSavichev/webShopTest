package org.demoTest.model.component;

import org.demoTest.SearchTest;
import org.demoTest.model.*;
import org.demoTest.model.base.BaseHeaderComponent;
import org.demoTest.model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainHeaderComponent<Page extends BasePage<?>> extends BaseHeaderComponent<Page> {

    @FindBy(xpath = "//img[@alt='Tricentis Demo Web Shop']")
    private WebElement logo;

    @FindBy(xpath = "//a[@class='ico-register']")
    private WebElement registerButton;

    @FindBy(xpath = "//a[@class='ico-login']")
    private WebElement logInButton;

    @FindBy(xpath = "//a[@class='ico-logout']")
    private WebElement logOutButton;

    @FindBy(xpath = "//li[@id='topcartlink']")
    private WebElement shoppingCartButton;

    @FindBy(xpath = "//ul[@class='top-menu']//a[@href='/jewelry']")
    private WebElement jewelryButtonTopMenu;

    @FindBy(xpath = "//ul[@class='top-menu']//a[@href='/electronics']")
    private WebElement electronicsButtonTopMenu;

    @FindBy(xpath = "//ul[@class='top-menu']//a[@href='/cell-phones']")
    private WebElement cellPhonesButton;

    @FindBy(xpath = "//div[@class='header']//a[@class='ico-wishlist']")
    private WebElement wishlistButton;

    @FindBy(xpath = "//div[@class='header-links']//a[@class='account']")
    private WebElement accountEmail;

    @FindBy(xpath = "//input[@id='small-searchterms']")
    private WebElement searchInput;

    @FindBy(xpath = "//input[@value='Search']")
    private WebElement searchSubmitButton;

    public MainHeaderComponent(Page page) {
        super(page);
    }

    public MainPage clickLogo() {
        logo.click();
        return new MainPage(getDriver());
    }

    public RegistrationPage clickRegister() {
        getWait2().until(ExpectedConditions.elementToBeClickable(registerButton)).click();
        return new RegistrationPage(getDriver());
    }

    public CartPage clickShoppingCart() {
        shoppingCartButton.click();
        return new CartPage(getDriver());
    }

    public WishlistPage clickWishlist() {
        wishlistButton.click();
        return new WishlistPage(getDriver());
    }

    public CatalogPage clickCameraPhotoButtonTopMenu() {
        getWait10().until(ExpectedConditions.elementToBeClickable(electronicsButtonTopMenu)).click();
        return new CatalogPage(getDriver());
    }

    public CatalogPage clickJewelryButtonTopMenu() {
        getWait10().until(ExpectedConditions.elementToBeClickable(jewelryButtonTopMenu)).click();
        return new CatalogPage(getDriver());
    }

    public LoginPage clickLoginButton() {
        getWait10().until(ExpectedConditions.elementToBeClickable(logInButton)).click();
        return new LoginPage(getDriver());
    }

    public MainPage clickLogOutButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(logOutButton)).click();
        return new MainPage(getDriver());
    }

    public String getAccountEmail() {
        return getWait10().until(ExpectedConditions.visibilityOf(accountEmail)).getText();
    }

    public String getRegisterButtonText() {
        return registerButton.getText();
    }

    public SearchPage searchItem(String itemName) {
        getWait5().until(ExpectedConditions.visibilityOf(searchInput)).sendKeys(itemName);
        searchSubmitButton.click();

        return new SearchPage(getDriver());
    }
}
