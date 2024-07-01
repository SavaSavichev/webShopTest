package org.demoTest.model;

import org.demoTest.model.base.BaseMainHeaderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BaseMainHeaderPage<RegistrationPage> {

    @FindBy(xpath = "//input[@id='gender-male']")
    private WebElement genderMaleRadiobutton;

    @FindBy(xpath = "//input[@id='FirstName']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@id='LastName']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@id='Email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='Password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='ConfirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//input[@id='register-button']")
    private WebElement registerButton;

    @FindBy(xpath = "//div[@class = 'result']")
    private WebElement confirmationMessage;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage clickGenderMale() {
        genderMaleRadiobutton.click();

        return this;
    }

    public RegistrationPage enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);

        return this;
    }

    public RegistrationPage enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);

        return this;
    }

    public RegistrationPage enterEmail(String email) {
        emailField.sendKeys(email);

        return this;
    }

    public RegistrationPage enterPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public RegistrationPage enterConfirmPassword(String password) {
        confirmPasswordField.sendKeys(password);
        return this;
    }

    public RegistrationPage clickRegisterButton() {
        registerButton.click();
        return this;
    }

    public String getRegistrationConfirmationMessage() {
        return confirmationMessage.getText();

    }
}
