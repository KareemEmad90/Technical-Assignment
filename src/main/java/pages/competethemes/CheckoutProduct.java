package pages.competethemes;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utils.Utils;

public class CheckoutProduct {
    private WebDriver driver ;

    public CheckoutProduct(WebDriver driver) {
        this.driver = driver;
    }
    Utils utils = new Utils(driver);


    //By viewCartBTN=By.xpath("//label[@for='digitalSelection']");
    By viewCartBTN=By.xpath("//*[@id='shopping-cart-container']/div/a");
    By checkSubtotal=By.xpath("//*[@id=\"loop-container\"]/div/article/div[2]/div/form/table/tbody/tr[1]/td[6]/span");
    By verfiyCountItems = By.xpath("//*[@id=\"loop-container\"]/div/article/div[2]/div/form/table/tbody/tr[1]/td[5]/div/label");
    By changeAddressLink = By.linkText("Change address");
    By countryTxt = By.id("calc_shipping_state");
    By cityTxt = By.id("calc_shipping_city");
    By updateAddressBtn=By.name("calc_shipping");
    By checkOutBtn=By.linkText("Proceed to checkout");


    @Step("View Cart Details")
    public void viewCartDetails()  {
        ElementActions.click(driver, viewCartBTN);


    }
    @Step("Verify Subtotal")
    public void verifySubtotal(String subTotal)  {

        String getSubtotal = ElementActions.getText(driver, checkSubtotal);
        utils.verifyValues(getSubtotal, subTotal);
        System.out.println("Prices for Two items: " + subTotal);

    }

    @Step("Verify Items Count")
    public void verifyItemsCount(String count)  {

        String getCountItems = ElementActions.getText(driver, verfiyCountItems);
        utils.verifyValues(getCountItems, count);
        System.out.println("Count of Items: " + count);

    }

    @Step("Change Address")
    public void changeAddress()  {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        ElementActions.click(driver,changeAddressLink);
        ElementActions.type(driver, countryTxt , "TestCountry");
        ElementActions.type(driver, cityTxt , "TestCity");
        ElementActions.click(driver,updateAddressBtn);

    }

    @Step("Checkout Products")
    public void checkoutProducts()  {

    }


}
