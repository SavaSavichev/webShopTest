package org.demoTest.runner;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;


public abstract class BaseTest {

    private WebDriver driver;

    @BeforeMethod
    protected void beforeMethod() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                "--headless=new",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--window-size=1920,1080",
                "--disable-gpu",
                "--remote-allow-origins=*",
                "--user-data-dir=/tmp/chrome-data"
        );

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
}
