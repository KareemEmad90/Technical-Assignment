package pages.sauceDemoPage;

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

    //By searchForItem=By.id("item_0_title_link");
    By searchForItem=By.linkText("Sauce Labs Bike Light");
    By addToChartBtn=By.id("add-to-cart-sauce-labs-bike-light");
    By openBasket=By.id("shopping_cart_container");



    @Step("Add Product To Cart")
    public void addProductToCart()  {

        ElementActions.click(driver, searchForItem);
        ElementActions.click(driver, addToChartBtn);
        ElementActions.click(driver, openBasket);
    }


}
