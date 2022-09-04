package AddProduct;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.LoadProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.competethemes.AddProductItems;
import pages.competethemes.CheckoutProduct;
import pages.competethemes.Product;

import java.sql.SQLException;

public class AddProductTest {

    private WebDriver driver;



    @BeforeMethod
    public void beforeTest() throws SQLException, ClassNotFoundException {
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("openWebSite"));


    }


    @Step("Search For Product on Women Category and add items to cart")
    @Test
    public void buyNewWomenItems() throws InterruptedException {
        Product searchProduct = new Product(driver);
        searchProduct.selectWomenCategory();
        searchProduct.filterProductByRating();

        AddProductItems addProduct = new AddProductItems(driver);
        addProduct.getPriceInFilteredPage("67");
        addProduct.openFirstItemes();
        addProduct.getPriceInProductDetailsPage("67");
        addProduct.addProductToCart();

        CheckoutProduct checkoutproduct = new CheckoutProduct(driver);
        checkoutproduct.viewCartDetails();
        checkoutproduct.verifySubtotal("134");
        checkoutproduct.verifyItemsCount("2");
        checkoutproduct.changeAddress();
        checkoutproduct.checkoutProducts();


    }

    @BeforeTest()
    public void beforeMethod() throws InterruptedException {

    }


}
