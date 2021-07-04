package pages.cis;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehicleDiscrepanciesPage {
    WebDriver driver;

    By agreeVehicleDiscrepancies = By.xpath("//span[contains(text(),'Verify Vehicle Elements Discrepancies')]/ancestor::label");

    By saveBtn = By.id("finishForm1:finishBtn");

    public VehicleDiscrepanciesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void proceedWithViehcel(){
        ElementActions.waitForElementToBePresent(driver,agreeVehicleDiscrepancies,5,true);
        ElementActions.click(driver,agreeVehicleDiscrepancies);
        ElementActions.click(driver,saveBtn);
    }



}
