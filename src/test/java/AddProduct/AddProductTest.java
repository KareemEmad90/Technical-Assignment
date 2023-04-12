package AddProduct;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.LoadProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.sauceDemoPage.AddProductItems;
import pages.sauceDemoPage.CheckoutProduct;
import pages.sauceDemoPage.LoginPage;

import java.sql.SQLException;

public class AddProductTest {

    private WebDriver driver;



    @BeforeMethod
    public void beforeTest() throws SQLException, ClassNotFoundException {
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("navigateURL"));

    }


    @Step("Search For Product and add items to cart")
    @Test
    public void buyNewItem() throws InterruptedException {

        LoginPage login = new LoginPage(driver);
        login.enterLoginInfo();


        AddProductItems addProduct = new AddProductItems(driver);
        addProduct.addProductToCart();


        CheckoutProduct checkoutproduct = new CheckoutProduct(driver);
        checkoutproduct.enterCustomerDetails();
        checkoutproduct.assertThanksMessage("Thank you for your order!");



    }

    @BeforeTest()
    public void beforeMethod() throws InterruptedException {

    }


}
