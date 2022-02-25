package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehicleInspectionPage {
    private WebDriver driver;

    public VehicleInspectionPage(WebDriver driver) {
        this.driver = driver;
    }

    By apptDate = By.xpath("(//div[@class='listOfAppointment']/div[not(@class='selected')])[1]");
    By confAppointment = By.id("confirmAppointmentBTN");


    public void selectAvailbleAppointment(){
        ElementActions.click(driver,apptDate);
        ElementActions.click(driver,confAppointment);
    }
}
