package io.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PurchaseTest {

    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    LoginPage loginPage;
    MyAccountPage myAccountPage;
    SearchResultsPage searchResultsPage;
    ItemPage itemPage;
    ShoppingCart shoppingCart;
    Order order;
    OrderConfirmation orderConfirmation;
    OrderHistory orderHistory;

    @BeforeEach
    public void openTheBrowser() {
        System.setProperty("webdriver.chrome.driver", "D:\\Ani\\CodersLab\\WebDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 3);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        myAccountPage = new MyAccountPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        itemPage = new ItemPage(driver, wait);
        shoppingCart = new ShoppingCart(driver);
        order = new Order(driver);
        orderConfirmation = new OrderConfirmation(driver);
        orderHistory = new OrderHistory(driver);

    }


    @Test
    public void correctCredentialTest() throws InterruptedException {

        String email = "bgcvzqjvlxczspgoru@ytnhy.com";
        String itemName = "Hummingbird Printed Sweater";
        String password = "5Sdvg44h3Mdm8d2";
        String size = "M";
        String quantity = "5";

        homePage.goToHomePage();
        homePage.goToLoginPage();

        loginPage.insertEmail(email);
        loginPage.insertPassword(password);
        loginPage.clickLoginButton();

        myAccountPage.searchItemBySearchBox(itemName);

        searchResultsPage.goToItemPage(itemName);

        itemPage.checkDiscount();
        itemPage.changeSize(size);
        itemPage.changeQuantity(quantity);
        itemPage.goToCart();
        itemPage.proceedToCheckout();

        shoppingCart.proceedToCheckout();

        order.confirmAddress();
        order.selectSelfPickUp();
        order.pressContinueInShipping();
        order.selectPayByCheck();
        order.pressApproveConditions();
        order.pressPlaceOrder();

        orderConfirmation.checkTheOrder(itemName, size, quantity);
        orderConfirmation.takeScreenshot();
        orderConfirmation.goToAccount();

        myAccountPage.goToOrderHistory();

        orderHistory.checkTheOrder(orderConfirmation);
        orderHistory.quitBrowser();

    }
}
