package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SellerSelectVehicle {
    private WebDriver driver ;
    private By continueBtn = By.xpath("//button[text() = 'Continue']");
    private By selectVehicleBtn = By.xpath("//label[@for='veh_0']");

    public SellerSelectVehicle(WebDriver driver) {
        this.driver = driver;
    }

    public void selectVehicle() {
        ElementActions.click(driver, selectVehicleBtn);
        ElementActions.click(driver, continueBtn);
    }
}
