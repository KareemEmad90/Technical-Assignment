package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SellerConsentVehicle {
    private WebDriver webDriver;
    private By yesBtn = By.xpath("//button[text() = 'Yes']");
    private By noBtn = By.xpath("//button[text() = 'No']");

    public SellerConsentVehicle(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void cancelRegisteration() {
        ElementActions.click(webDriver, yesBtn);
    }
}
