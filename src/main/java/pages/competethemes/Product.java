package pages.competethemes;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Product {

    private WebDriver driver ;
    public Product(WebDriver driver) {
        this.driver = driver;
    }


    By selectWomenStoreBtn=By.id("menu-item-342");
    By rateFilterBtn=By.xpath("//*[@id='woocommerce_rating_filter-3']/ul/li/a");




    @Step("Go to Women Store")
    public void selectWomenCategory() throws InterruptedException {

        ElementActions.click(driver , selectWomenStoreBtn);

    }


    @Step("Filter By Rate")
    public void filterProductByRating()  {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        ElementActions.click(driver, rateFilterBtn);
    }


}
