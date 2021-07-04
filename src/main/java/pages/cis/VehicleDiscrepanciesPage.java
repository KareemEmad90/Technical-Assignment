package pages.cis;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehicleDiscrepanciesPage {
    WebDriver driver;

    By agreeVehicleDiscrepancies = By.xpath("//span[contains(text(),'Verify Vehicle Elements Discrepancies')]/ancestor::label");

    By saveBtn = By.id("finishForm1:authenticate");

    public VehicleDiscrepanciesPage(WebDriver driver) {
        this.driver = driver;
    }



}
