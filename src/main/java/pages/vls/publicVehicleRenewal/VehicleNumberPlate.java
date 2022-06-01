package pages.vls.publicVehicleRenewal;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehicleNumberPlate {
    private WebDriver driver ;



    public VehicleNumberPlate(WebDriver driver){this.driver=driver;}

    By ContinueBTN=By.xpath("//button[text() = 'Continue']");




    public void vehicleNumberPlatepage(){
        ElementActions.click(driver , ContinueBTN);
    }


}


