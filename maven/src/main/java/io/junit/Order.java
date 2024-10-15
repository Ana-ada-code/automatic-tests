package io.junit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Order {
    private WebDriver driver;


    public Order(WebDriver driver) {
        this.driver = driver;
    }

    public void confirmAddress() {
        WebElement confirmAddress = driver.findElement(By.cssSelector("button[name=confirm-addresses]"));
        confirmAddress.click();
    }

    public void selectSelfPickUp() {
        WebElement radioButton = driver.findElement(By.id("delivery_option_8"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", radioButton);
    }

    public void pressContinueInShipping() {
        WebElement continueElement = driver.findElement(By.cssSelector("button[name=confirmDeliveryOption]"));
        continueElement.click();
    }

    public void selectPayByCheck() {
        WebElement payByCheck = driver.findElement(By.cssSelector("input[id=payment-option-1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", payByCheck);
    }

    public void pressApproveConditions() {
        WebElement accept = driver.findElement(By.cssSelector("input[class=ps-shown-by-js]"));
        accept.click();
    }

    public void pressPlaceOrder() {
        WebElement placeOrder = driver.findElement(By.cssSelector("#payment-confirmation > div.ps-shown-by-js > button"));
        placeOrder.click();
    }
}
