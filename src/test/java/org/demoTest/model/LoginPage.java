package org.demoTest.model;

import org.demoTest.model.base.BaseMainHeaderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseMainHeaderPage<CatalogPage> {

    @FindBy(xpath = "//input[@class='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@class='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@class='button-1 login-button']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MainPage loginUser(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new MainPage(getDriver());
    }
}
