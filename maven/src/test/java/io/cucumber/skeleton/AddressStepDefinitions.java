package io.cucumber.skeleton;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AddressStepDefinitions {
    private WebDriver driver;
    private WebDriverWait wait;
    private int addressListSize;
    private String expectedAlias;
    private String expectedAddress;
    private String expectedCity;
    private String expectedZipCode;
    private String expectedCountry;
    private String expectedPhone;

    @Before
    public void iOpenTheBrowser() {
        System.setProperty("webdriver.chrome.driver", "D:\\Ani\\CodersLab\\WebDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 3);
    }

    @After
    public void quitBrowser() {
        driver.quit();
    }

    @And("I go to Homepage")
    public void iGoToHomepage() {
        driver.get("https://mystore-testlab.coderslab.pl/index.php");
    }

    @And("I go to Login page")
    public void iGoToLoginPage() {
        WebElement submitButtonElement = driver.findElement(By.cssSelector("a[title='Log in to your customer account']"));
        submitButtonElement.click();
    }

    @And("I insert {string} as email")
    public void iInsertAsEmail(String email) {
        WebElement userNameElement = driver.findElement(By.cssSelector("input[id=field-email]"));
        userNameElement.sendKeys(email);
    }

    @And("I insert {string} as password")
    public void iInsertAsPassword(String password) {
        WebElement passwordElement = driver.findElement(By.cssSelector("input[id=field-password]"));
        passwordElement.sendKeys(password);
    }

    @And("I click login button")
    public void iClickLoginButton() {
        WebElement submitButtonElement = driver.findElement(By.id("submit-login"));
        submitButtonElement.click();
    }

    @And("I click Addresses")
    public void iClickAddresses() {
        WebElement addressButtonElement = driver.findElement(By.id("addresses-link"));
        addressButtonElement.click();
        checkAddressListSize();
    }

    @And("I click Create new address")
    public void iClickCreateNewAddress() {
        WebElement createAddressButtonElement = driver.findElement(By.cssSelector("a[data-link-action=add-address]"));
        createAddressButtonElement.click();
    }

    @And("I insert {string} as alias")
    public void iInsertAsAlias(String alias) {
        WebElement aliasElement = driver.findElement(By.id("field-alias"));
        aliasElement.sendKeys(alias);
    }

    @And("I insert {string} as address")
    public void iInsertAsAddress(String address) {
        WebElement addressElement = driver.findElement(By.id("field-address1"));
        addressElement.sendKeys(address);
    }

    @And("I insert {string} as city")
    public void iInsertAsCity(String city) {
        WebElement cityElement = driver.findElement(By.id("field-city"));
        cityElement.sendKeys(city);
    }

    @And("I insert {string} as zipCode")
    public void iInsertAsZipCode(String zipCode) {
        WebElement zipCodeElement = driver.findElement(By.id("field-postcode"));
        zipCodeElement.sendKeys(zipCode);
    }

    @And("I insert {string} as country")
    public void iInsertAsCountry(String country) {
        WebElement countryElement = driver.findElement(By.id("field-id_country"));
        countryElement.sendKeys(country);
    }

    @And("I insert {string} as phone")
    public void iInsertAsPhone(String phone) {
        WebElement phoneElement = driver.findElement(By.id("field-phone"));
        phoneElement.sendKeys(phone);
    }

    @And("I click Save")
    public void iClickSave() {
        WebElement saveButtonElement = driver.findElement(By.cssSelector("button[type=submit]"));
        saveButtonElement.click();
    }

    @When("I login with {string} and {string}")
    public void iLoginWithAnd(String email, String password) {
        iInsertAsEmail(email);
        iInsertAsPassword(password);
        iClickLoginButton();
    }

    @And("I insert {string} {string} {string} {string} {string} {string}")
    public void iInsert(String alias, String address, String city, String zipCode, String country, String phone) {
        expectedAlias = alias;
        expectedAddress = address;
        expectedCity = city;
        expectedZipCode = zipCode;
        expectedCountry = country;
        expectedPhone = phone;

        iInsertAsAlias(alias);
        iInsertAsAddress(address);
        iInsertAsCity(city);
        iInsertAsZipCode(zipCode);
        iInsertAsCountry(country);
        iInsertAsPhone(phone);
        iClickSave();
    }

    @Then("I created new address")
    public void iCreatedNewAddress() {
        verifySuccessMassage("Address successfully added!");

        List<WebElement> addressElements = driver.findElements(By.cssSelector("article[class=address]"));
        boolean numberOfAddressesIsDecremented = addressElements.size() == addressListSize + 1;
        Assertions.assertTrue(numberOfAddressesIsDecremented);
    }

    @And("I check if attributes are correct")
    public void iCheckIfItSCorrectlyAdded() {
        String addedElementId = getLastElementId();

        WebElement aliasElement = driver.findElement(By.cssSelector("#" + addedElementId + " > div.address-body > h4"));
        String currentAlias = aliasElement.getText();
        System.out.println(currentAlias);

        WebElement addressElement = driver.findElement(By.cssSelector("#" + addedElementId + " > div.address-body > address"));
        String addressValue = addressElement.getText();
        System.out.println(addressValue);
        String[] addressArray = addressValue.split("\n");
        System.out.println(addressArray[1]);
        String currentAddress = addressArray[1];
        String currentCity = addressArray[2];
        String currentZipCode = addressArray[3];
        String currentCountry = addressArray[4];
        String currentPhone = addressArray[5];

        boolean matched;

        matched = expectedAlias.equals(currentAlias);
        matched = expectedAddress.equals(currentAddress) && matched;
        matched = expectedCity.equals(currentCity) && matched;
        matched = expectedZipCode.equals(currentZipCode) && matched;
        matched = expectedCountry.equals(currentCountry) && matched;
        matched = expectedPhone.equals(currentPhone) && matched;

        Assertions.assertTrue(matched);

    }


    @Then("I delete the last address")
    public void iDeleteTheLastAddress() {

        String addedElementId = getLastElementId();

        WebElement deleteElement = driver.findElement(By.cssSelector("#" + addedElementId + " > div.address-footer > a:nth-child(2)"));
        deleteElement.click();
    }

    @And("I check that the address was deleted")
    public void iCheckThatTheAddressWasDeleted() {
        verifySuccessMassage("Address successfully deleted!");

        List<WebElement> addressElements = driver.findElements(By.cssSelector("article[class=address]"));
        boolean numberOfAddressesIsDecremented = addressElements.size() == addressListSize - 1;
        Assertions.assertTrue(numberOfAddressesIsDecremented);
    }

    private void verifySuccessMassage(String expectedMassage) {

        WebElement successElement = driver.findElement(By.className("alert-success"));
        String currentMassage = successElement.getText();

        boolean contains = currentMassage.contains(expectedMassage);
        Assertions.assertTrue(contains);
    }

    private void checkAddressListSize() {
        List<WebElement> addressElements = driver.findElements(By.cssSelector("article[class=address]"));
        addressListSize = addressElements.size();
    }

    private String getLastElementId() {
        List<WebElement> addressElements = driver.findElements(By.cssSelector("article[class=address]"));
        return addressElements.get(addressElements.size() - 1).getAttribute("id");
    }
}
