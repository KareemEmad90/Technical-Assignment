package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import data.LoadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehicleNumberPlatePage {
    private WebDriver driver;

    public VehicleNumberPlatePage(WebDriver driver) {
        this.driver = driver;
    }


    By plateSourceList = By.id("plateSource");
    By selectFirstValuePlate = By.xpath("//ul[@class='p-dropdown-items']/li[1]");
    By plateTypeList = By.id("plateType");
    By plateLogoList = By.id("logo");
    By frontPlateList = By.id("frontPlate");
    By backPlateList = By.id("backPlate");
    By trueSign = By.xpath("//*[@id='processing']/div/div[2]/div[1]/div/div[2]/button[1]");
    By continueSelectPlateBtn = By.xpath("//*[@id='processing']/div/div[2]/div[2]/button"); //id missing







    public void selectVehiclePlatesFromRTA() throws InterruptedException {
        ElementActions.click(driver , plateSourceList);
        ElementActions.click(driver , selectFirstValuePlate);
        ElementActions.click(driver , plateTypeList );
        ElementActions.click(driver , selectFirstValuePlate);
        ElementActions.click(driver , plateLogoList );
        ElementActions.click(driver , selectFirstValuePlate);
        ElementActions.click(driver , frontPlateList );
        ElementActions.click(driver , selectFirstValuePlate);
        ElementActions.click(driver , backPlateList );
        ElementActions.click(driver , selectFirstValuePlate);
        ElementActions.click(driver , trueSign);
        ElementActions.click(driver , continueSelectPlateBtn);

    }



}
