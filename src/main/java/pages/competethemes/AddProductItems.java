package pages.competethemes;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Utils;

public class AddProductItems {

    private WebDriver driver ;
    public AddProductItems(WebDriver driver) {
        this.driver = driver;
    }
    Utils utils = new Utils(driver);

    By openFirstItemBtn=By.xpath("//*[@id='loop-container']/ul/li[1]/a/img");
    By priceInSearchPageBtn=By.xpath("//*[@id='loop-container']/ul/li[1]/a/span/span/bdi");
    By priceProductPageBtn=By.xpath("//*[@id='product-328']/div[2]/p/span/bdi");
    By addToCartBtn=By.xpath("//*[@id='product-328']/div[2]/form/button");
    By enterCountNoTxt = By.id("quantity_6312700edcbd6");





    @Step("Get Prices In Filtered Page")
    public void getPriceInFilteredPage(String expectedPrice) {

        String actualPrice = ElementActions.getText(driver, priceInSearchPageBtn);
        //Assertions.assertEquals(expectedFees, actualNonRenewalFee);
        utils.verifyValues(actualPrice, expectedPrice);
        System.out.println("Prices in search page: " + actualPrice);


    }

    @Step("Get Prices In Product Page")
    public void getPriceInProductDetailsPage(String priceDetails)  {
        String priceInProductDetails = ElementActions.getText(driver, priceProductPageBtn);
        utils.verifyValues(priceInProductDetails, priceDetails);
        System.out.println("price In Product Details: " + priceInProductDetails);

    }
    @Step("Open First Item")
    public void openFirstItemes()  {

        ElementActions.click(driver, openFirstItemBtn);
    }

    @Step("Add Product To Cart")
    public void addProductToCart()  {

        ElementActions.type(driver, enterCountNoTxt , "2");
        ElementActions.click(driver, addToCartBtn);
    }
}
