package pages.vls.publicVehicleRenewal;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehicleInformation {
    private WebDriver driver ;



    public VehicleInformation(WebDriver driver){this.driver=driver;}

    By AddVehicleToBasketBTN=By.xpath("//button[@class='BtnStyle']");
    By ProceedBasketBTN=By.xpath("//button[text() = 'Proceed with listed vehicles']");
    By VerifyOTPTXT =By.id("mobileNumber");




    public void vehicleInformationPage(){
        ElementActions.click(driver , AddVehicleToBasketBTN);
        ElementActions.click(driver , ProceedBasketBTN);

    }


}


