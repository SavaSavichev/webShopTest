package org.demoTest.runner;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;

import org.testng.ITestResult;

public abstract class BaseTest {

    private WebDriver driver;

    @BeforeMethod
    protected void beforeMethod() {
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments(
//                "--headless=new",
//                "--no-sandbox",
//                "--disable-dev-shm-usage",
//                "--window-size=1920,1080",
//                "--disable-gpu",
//                "--remote-allow-origins=*",
//                "--user-data-dir=/tmp/chrome-data"
//        );

        driver = new ChromeDriver(chromeOptions);

        driver.get("https://demowebshop.tricentis.com/");
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) {
        if (!testResult.isSuccess()) {
            File file = takeScreenShot(driver, method.getName(), this.getClass().getName());
            try {
                Allure.addAttachment("Page state: ", FileUtils.openInputStream(file));
            } catch (IOException e) {
                System.out.println("Couldn't make a screenshot because of exception: " + e.getMessage());
            }
        }
        if (isUserLoggedIn()) {
            logoutSafely();
        }

        clearCartIfNeeded();
        clearWishlistIfNeeded();

        driver.quit();
    }

    protected WebDriver getDriver() {
        return driver;
    }

    static File takeScreenShot(WebDriver driver, String methodName, String className) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(String.format("screenshots/%s.%s.png", className, methodName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    protected boolean isUserLoggedIn() {
        try {
            return driver.findElement(By.className("ico-logout")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void logoutSafely() {
        try {
            driver.findElement(By.className("ico-logout")).click();
        } catch (Exception e) {
            System.out.println("Logout failed or not necessary: " + e.getMessage());
        }
    }

    public void clearCartIfNeeded() {
        driver.get("https://demowebshop.tricentis.com/cart");
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Shopping cart']")));

            List<WebElement> removeCheckboxes = driver.findElements(By.name("removefromcart"));
            if (!removeCheckboxes.isEmpty()) {
                for (WebElement checkbox : removeCheckboxes) {
                    if (!checkbox.isSelected()) {
                        checkbox.click();
                    }
                }
                driver.findElement(By.name("updatecart")).click();
            }
        } catch (Exception e) {
            System.out.println("Cart cleanup failed: " + e.getMessage());
        }
    }

    public void clearWishlistIfNeeded() {
        driver.get("https://demowebshop.tricentis.com/wishlist");
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.className("wishlist-content")));

            List<WebElement> removeCheckboxes = driver.findElements(By.name("removefromcart"));
            if (!removeCheckboxes.isEmpty()) {
                for (WebElement checkbox : removeCheckboxes) {
                    if (!checkbox.isSelected()) {
                        checkbox.click();
                    }
                }
                driver.findElement(By.name("updatecart")).click();
            }
        } catch (Exception e) {
            System.out.println("Wishlist cleanup failed: " + e.getMessage());
        }
    }
}
