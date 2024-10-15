package io.junit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToHomePage() {
        driver.get("https://mystore-testlab.coderslab.pl/index.php");
    }

    public void goToLoginPage() {
        WebElement submitButton = driver.findElement(By.cssSelector("a[title='Log in to your customer account']"));
        submitButton.click();
    }
}
