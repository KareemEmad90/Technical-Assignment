package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SellerFines {
    private WebDriver webDriver;
    private By continueBtn = By.xpath("//button[text() = 'Continue']");


    public SellerFines(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void continueWithNoFines() {
        ElementActions.click(webDriver, continueBtn);
    }
}
