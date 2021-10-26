package pages.vls;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReturnPlates {
    private WebDriver driver ;
    public ReturnPlates(WebDriver driver){this.driver=driver;}
    //By ReserveRadioBTN = By.id("RESERVE");
    By ReserveRadioBTN = By.xpath("//label[@for='RESERVE']");
    //By ReturnToRTABTN = By.id("RETURN_TO_RTA");
    By ReturnToRTABTN = By.xpath("//label[@for='RETURN_TO_RTA']");
    By ContinueBTN = By.xpath("//*[@id=\"processing\"]/div/div/button");

    public void plateActionReserve(){
        ElementActions.click(driver,ReserveRadioBTN);
        ElementActions.click(driver,ContinueBTN);
    }

    public void plateActionReturnToRTA(){
        ElementActions.click(driver,ReturnToRTABTN);
        ElementActions.click(driver,ContinueBTN);
    }
}
