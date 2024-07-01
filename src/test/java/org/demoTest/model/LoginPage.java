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
    private WebElement logInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MainPage logInUser(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        logInButton.click();
        return new MainPage(getDriver());
    }
}
