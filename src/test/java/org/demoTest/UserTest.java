package org.demoTest;

import org.demoTest.model.MainPage;
import org.demoTest.runner.BaseTest;
import org.demoTest.runner.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest extends BaseTest {

    private final static String FIRST_NAME = "First name";
    private final static String LAST_NAME = "Last name";
    private final static String EMAIL = "testovoemylo@mulo.com";
    private final static String PASSWORD = "123456";

    @Test
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

    @Test
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
