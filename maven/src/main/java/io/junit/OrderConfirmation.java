package io.junit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;

public class OrderConfirmation {
    private WebDriver driver;
    private String totalCost;
    private String orderReference;


    public OrderConfirmation(WebDriver driver) {
        this.driver = driver;
    }

    public void checkTheOrder(String itemName, String size, String quantity) {
        WebElement confirmation = driver.findElement(By.className("h1"));
        String confirmationText = confirmation.getText();

        Assert.assertTrue(confirmationText.contains("YOUR ORDER IS CONFIRMED"));

        WebElement item = driver.findElement(By.cssSelector("#order-items > div.order-confirmation-table > div > div.col-sm-4.col-xs-9.details"));
        String itemText = item.getText().toUpperCase();

        Assert.assertTrue(itemText.contains(itemName.toUpperCase()));
        Assert.assertTrue(itemText.contains("SIZE: " + size.toUpperCase()));

        WebElement quantityWeb = driver.findElement(By.cssSelector("#order-items > div.order-confirmation-table > div > div.col-sm-6.col-xs-12.qty > div > div:nth-child(2)"));
        String quantityWebText = quantityWeb.getText();

        Assert.assertEquals(quantity, quantityWebText);

        setTotalCost();
        setOrderReference();
    }

    public void takeScreenshot() {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File("D:/screenshot.png");
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToAccount() {
        WebElement account = driver.findElement(By.cssSelector("a[class=account]"));
        account.click();
    }


    private void setTotalCost() {
        WebElement totalCost = driver.findElement(By.cssSelector("#order-items > div.order-confirmation-table > table > tbody > tr.total-value.font-weight-bold > td:nth-child(2)"));
        this.totalCost = totalCost.getText();
    }

    private void setOrderReference() {
        WebElement orderReference = driver.findElement(By.id("order-reference-value"));
        this.orderReference = orderReference.getText().substring(17);
    }


    public String getTotalCost() {
        return totalCost;
    }

    public String getOrderReference() {
        return orderReference;
    }
}
