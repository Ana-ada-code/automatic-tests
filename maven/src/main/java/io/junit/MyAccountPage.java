package io.junit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountPage {

    private WebDriver driver;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchItemBySearchBox(String item) {

        WebElement searchBox = driver.findElement(By.cssSelector("input[name=s]"));
        searchBox.sendKeys(item);
        searchBox.sendKeys(Keys.ENTER);

    }

    public void goToOrderHistory() {
        WebElement orderHistory = driver.findElement(By.id("history-link"));
        orderHistory.click();
    }


}
