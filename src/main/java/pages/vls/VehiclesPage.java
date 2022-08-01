package pages.vls;

import com.shaft.gui.element.ElementActions;
import io.cucumber.java.tr.Ve;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehiclesPage {
    WebDriver pdriver ;
    By searchBox = By.xpath("//*[@class='search vehicleSearch']/input");
    By searchBtn = By.xpath("//*[@class='search-img']");
    By vehicleCard = By.xpath("//*[@class='vehicleBox']");
    @Step("Search for vehicle by chassis number or plate number")
    public void searchForVehicle(String ChassisOrPlate){
        ElementActions.type(pdriver,searchBox,ChassisOrPlate);
        ElementActions.click(pdriver,searchBtn);
    }

    @Step("Click on vehicle card")
    public void selectVehicle(){
        ElementActions.click(pdriver,vehicleCard);
    }


    public VehiclesPage(WebDriver driver){
        this.pdriver = driver ;
    }

}
