package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehicleInsurancePage {
    private WebDriver driver;

    public VehicleInsurancePage(WebDriver driver) {
        this.driver = driver;
    }


    private By chasisInsuranceItem(String chasis) {

    return By.xpath("//div[@class='messageBanner T_success']//div[@class='Insuranve_GQ']/following-sibling::label[contains(text(),'"+chasis+"')]");
    }

    public boolean verifyChasisInsurance(String chasis) {
        if (ElementActions.isElementDisplayed(driver,chasisInsuranceItem(chasis))) {
            return true;
        }else{
            return false;
        }
    }
}
