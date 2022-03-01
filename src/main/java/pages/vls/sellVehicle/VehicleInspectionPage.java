package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import com.shaft.tools.support.JavaScriptHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class VehicleInspectionPage {
    private WebDriver driver;

    public VehicleInspectionPage(WebDriver driver) {
        this.driver = driver;
    }

    By apptDate = By.xpath("(//div[@class='listOfAppointment']/div[not(@class='selected')])[1]/span[@class='seats']/preceding-sibling::span");
    By confAppointment = By.id("confirmAppointmentBTN");
    private By chasisInspectionItem(String chasis) {

        return By.xpath("//div[@class='messageBanner T_success']//div[@class='Insuranve_GQ']/following-sibling::label[contains(text(),'"+chasis+"')]");
    }

    public boolean verifyChasisInspection(String chasis) {
        if (ElementActions.isElementDisplayed(driver,chasisInspectionItem(chasis))) {
            return true;
        }else{
            return false;
        }
    }



    public void selectAvailbleAppointment() throws InterruptedException {
        Thread.sleep(4000);
        ElementActions.click(driver,apptDate);
        ElementActions.click(driver,confAppointment);
    }
}
