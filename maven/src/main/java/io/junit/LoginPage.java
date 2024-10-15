package io.junit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void insertEmail(String email) {
        WebElement userName = driver.findElement(By.cssSelector("input[id=field-email]"));
        userName.sendKeys(email);
    }

    public void insertPassword(String password) {
        WebElement passwordElement = driver.findElement(By.cssSelector("input[id=field-password]"));
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement submitButton = driver.findElement(By.id("submit-login"));
        submitButton.click();
    }


}
