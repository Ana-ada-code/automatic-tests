package io.junit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ItemPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ItemPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void changeSize(String size) {
        WebElement sizeElement = driver.findElement(By.id("group_1"));
        sizeElement.sendKeys(size);
    }

    public void changeQuantity(String quantity) {

        WebElement quantityElement = driver.findElement(By.cssSelector("input[id=quantity_wanted]"));

        quantityElement.clear();
        quantityElement.clear();
        Actions actions = new Actions(driver);
        actions.click(quantityElement)
                .sendKeys(Keys.chord(Keys.CONTROL, "a"))
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(quantity)
                .perform();
    }


    public void goToCart() {
        WebElement cartElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-button-action=add-to-cart]")));
        cartElement.click();
    }

    public void proceedToCheckout() {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#blockcart-modal > div > div")));

        WebElement proceedToCheckoutButton = modal.findElement(By.cssSelector("a.btn.btn-primary"));
        proceedToCheckoutButton.click();
    }

    public void checkDiscount() {
        WebElement discount = driver.findElement(By.className("discount-percentage"));
        String discountText = discount.getText();
        Assert.assertTrue(discountText.contains("20%"));

        WebElement currentPrice = driver.findElement(By.className("current-price-value"));
        String currentPriceContent = currentPrice.getAttribute("content");
        double currentP = Double.parseDouble(currentPriceContent);


        WebElement regularPrice = driver.findElement(By.className("regular-price"));
        String regularPriceText = regularPrice.getText();
        double regularP = Double.parseDouble(regularPriceText.substring(1));

        Assert.assertTrue(Math.abs((currentP / regularP) - 0.8) < 0.01);
    }


}
