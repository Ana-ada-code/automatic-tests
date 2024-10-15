package io.junit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCart {
    private WebDriver driver;

    public ShoppingCart(WebDriver driver) {
        this.driver = driver;
    }

    public void proceedToCheckout() {
        WebElement proceed = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a"));
        proceed.click();
    }
}
