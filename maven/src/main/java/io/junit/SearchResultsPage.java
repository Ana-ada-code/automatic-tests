package io.junit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage {
    private WebDriver driver;


    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToItemPage(String itemNames) {
        List<WebElement> items = driver.findElements(By.cssSelector("article"));

        WebElement itemName = null;
        for (int i = 0; i < items.size(); i++) {
            itemName = driver.findElement(By.cssSelector("#js-product-list > div.products.row > div:nth-child("
                    + (i + 1) + ") > article > div > div.product-description > h2 > a"));
            String itemNameText = itemName.getText();
            if (itemNames.equals(itemNameText)) {
                break;
            }
        }

        itemName.click();
    }
}
