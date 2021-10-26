package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SellerPlatesDetails {
    private WebDriver webDriver;
    private By continueBtn = By.xpath("//button[text() = 'Continue']");
    private By yesBtn = By.xpath("//label[@for='yes']");
    private By noBtn = By.xpath("//label[@for='no']");

    public SellerPlatesDetails(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void selectYesKeepPlate(){
        ElementActions.click(webDriver, yesBtn);
        ElementActions.click(webDriver, continueBtn);
    }

    public void selectNoKeepPlate(){
        ElementActions.click(webDriver, noBtn);
        ElementActions.click(webDriver, continueBtn);
    }
}
