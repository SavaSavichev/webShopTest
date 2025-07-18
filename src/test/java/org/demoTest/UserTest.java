package org.demoTest;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.demoTest.model.MainPage;
import org.demoTest.runner.BaseTest;
import org.demoTest.runner.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class UserTest extends BaseTest {

    private final static String FIRST_NAME = "First name";
    private final static String LAST_NAME = "Last name";
    private final static String EMAIL = "testovoemylo@mulo.com";
    private final static String PASSWORD = "123456";

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Checking the user's ability to register from the main page")
    public void testRegisterNewUser() {
        final String expectedResultText = "Your registration completed";

        String actualResultText = new MainPage(getDriver())
                .getHeader()
                .clickRegister()
                .clickGenderMale()
                .enterFirstName(FIRST_NAME)
                .enterLastName(LAST_NAME)
                .enterEmail(TestUtils.randomEmail())
                .enterPassword(PASSWORD)
                .enterConfirmPassword(PASSWORD)
                .clickRegisterButton()
                .getRegistrationConfirmationMessage();

        Assert.assertEquals(actualResultText, expectedResultText);
    }

    @Ignore
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Feature("User Registration")
    @Description("Verify that the system shows an error when trying to register with an already registered email")
    public void testRegisterNewUserWithExistingEmail() {
        final String expectedErrorText = "The specified email already exists";

        String actualErrorText = new MainPage(getDriver())
                .getHeader()
                .clickRegister()
                .clickGenderMale()
                .enterFirstName(FIRST_NAME)
                .enterLastName(LAST_NAME)
                .enterEmail(EMAIL)
                .enterPassword(PASSWORD)
                .enterConfirmPassword(PASSWORD)
                .clickRegisterButton()
                .getRegistrationErrorMessage();

        Assert.assertEquals(actualErrorText, expectedErrorText);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Checking the user's ability to log in from the main page")
    public void testLogInUser() {
        String actualAccountEmail = new MainPage(getDriver())
                .getHeader()
                .clickLoginButton()
                .loginUser(EMAIL, PASSWORD)
                .getHeader()
                .getAccountEmail();

        Assert.assertEquals(actualAccountEmail, EMAIL);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Function")
    @Description("Checking the user's ability to log out from the main page")
    public void testLogOutUser() {
        final String expectedResultText = "Register";

        String actualLinkText = new MainPage(getDriver())
                .getHeader()
                .clickLoginButton()
                .loginUser(EMAIL, PASSWORD)
                .getHeader()
                .clickLogOutButton()
                .getHeader()
                .getRegisterButtonText();

        Assert.assertEquals(actualLinkText, expectedResultText);
    }
}
