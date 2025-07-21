package org.demoTest.model;

import org.demoTest.model.base.BaseMainHeaderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReviewPage extends BaseMainHeaderPage<ReviewPage> {

    @FindBy(xpath = "//input[@class='review-title']")
    private WebElement reviewTitleInput;

    @FindBy(xpath = "//textarea[@class='review-text']")
    private WebElement reviewTextArea;

    @FindBy(xpath = "//input[@id='addproductrating_4']")
    private WebElement fourStarsRating;

    @FindBy(xpath = "//input[@name='add-review']")
    private WebElement submitReviewButton;

    @FindBy(xpath = "//div[@class='result']")
    private WebElement reviewAddResultMessage;

    public ReviewPage(WebDriver driver) {
        super(driver);
    }

    public ReviewPage addReview(String title, String text) {
        reviewTitleInput.sendKeys(title);
        reviewTextArea.sendKeys(text);
        fourStarsRating.click();
        submitReviewButton.click();
        return this;
    }

    public String getReviewAddResultMessageText() {
        return getWait5().until(ExpectedConditions.visibilityOf(reviewAddResultMessage)).getText();
    }
}
