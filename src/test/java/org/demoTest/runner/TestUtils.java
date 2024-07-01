package org.demoTest.runner;

import com.github.javafaker.Faker;
import org.demoTest.model.base.BaseModel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TestUtils {

    public static void scrollWithPauseByActions(BaseModel baseModel, WebElement element, int mls) {
        new Actions(baseModel.getDriver())
                .scrollToElement(element)
                .pause(mls)
                .perform();
    }

    public static String randomEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }
}
