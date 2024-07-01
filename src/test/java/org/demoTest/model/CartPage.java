package org.demoTest.model;

import org.demoTest.model.base.BaseMainHeaderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BaseMainHeaderPage<CartPage> {

    @FindBy(xpath = "(//a[@class='product-name'])[1]")
    private WebElement firstProductName;

    @FindBy(xpath = "//tr[@class='cart-item-row']//div[@class='attributes']")
    private WebElement productsAttributes;

    @FindBy(xpath = "//input[@class='qty-input']")
    private WebElement quantityInput;

    @FindBy(xpath = "//input[@name='updatecart']")
    private WebElement updateCartButton;

    @FindBy(xpath = "//span[@class='product-unit-price']")
    private WebElement unitItemPrice;

    @FindBy(xpath = "//span[@class='product-subtotal']")
    private WebElement totalItemPrice;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement checkoutButton;

    @FindBy(xpath = "//div[@id='terms-of-service-warning-box']")
    private WebElement warningBox;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return firstProductName.getText();
    }

    public String getProductSize() {
        String prodAtt = productsAttributes.getText();
        return prodAtt.substring(prodAtt.indexOf(" "), prodAtt.indexOf("C")).trim();
    }

    public String getProductColor() {
        String prodAtt = productsAttributes.getText();
        return prodAtt.substring(prodAtt.lastIndexOf(":") + 1).trim();
    }

    public CartPage setItemQuantity(int quantity) {
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
        return this;
    }

    public CartPage clickUpdateCartButton() {
        updateCartButton.click();
        return this;
    }

    public double getUnitItemPrice() {
        return Double.parseDouble(unitItemPrice.getText());
    }

    public double getTotalItemPrice() {
        return Double.parseDouble(totalItemPrice.getText());
    }

    public int getItemQuantity() {
        return Integer.parseInt(quantityInput.getAttribute("value"));
    }

    public CartPage clickCheckoutButton() {
        checkoutButton.click();
        return this;
    }

    public String getWarningBoxText() {
        return warningBox.getText();
    }
}
