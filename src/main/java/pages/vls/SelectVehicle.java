package pages.vls;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class SelectVehicle {
    private WebDriver driver ;
    public SelectVehicle(WebDriver driver){this.driver=driver;}
    By ContinueBtn = By.xpath("//*[@id=\"processing\"]/div/div/button");

    public void selectvehicle(String ChassisNo)   {

        ElementActions.click(driver,By.xpath("//label[@for='"+ChassisNo+"']"));
        ElementActions.click(driver,ContinueBtn);
    }
}
