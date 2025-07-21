package org.demoTest.runner;

import com.github.javafaker.Faker;
import org.demoTest.model.base.BaseModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestUtils {

    public static void scrollWithPauseByActions(BaseModel baseModel, By locator, int mls) {
        WebElement element = new WebDriverWait(baseModel.getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(locator));

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
