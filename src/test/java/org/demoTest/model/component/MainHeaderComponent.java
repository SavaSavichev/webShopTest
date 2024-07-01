package org.demoTest.model.component;

import org.demoTest.model.*;
import org.demoTest.model.base.BaseHeaderComponent;
import org.demoTest.model.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

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

    public MainHeaderComponent(Page page) {
        super(page);
    }

    public MainPage clickLogo() {
        logo.click();
        return new MainPage(getDriver());

    }

    public RegistrationPage clickRegister() {
        registerButton.click();
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
        new Actions(getDriver())
                .moveToElement(electronicsButtonTopMenu)
                .pause(1000)
                .moveToElement(cellPhonesButton)
                .click()
                .perform();
        return new CatalogPage(getDriver());
    }

    public CatalogPage clickJewelryButtonTopMenu() {
        jewelryButtonTopMenu.click();
        return new CatalogPage(getDriver());
    }

    public LoginPage clickLoginButton() {
        logInButton.click();
        return new LoginPage(getDriver());
    }

    public MainPage clickLogOutButton() {
        logOutButton.click();
        return new MainPage(getDriver());
    }

    public String getAccountEmail() {
        return accountEmail.getText();
    }

    public String getRegisterButtonText() {
        return registerButton.getText();
    }
}
