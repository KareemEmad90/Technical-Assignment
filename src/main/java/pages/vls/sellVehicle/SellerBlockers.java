package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SellerBlockers {
    private WebDriver webDriver;
    private By continueBtn = By.xpath("//button[text() = 'Continue']");

    public SellerBlockers(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void continueNoBlockers() {
        ElementActions.click(webDriver, continueBtn);
    }
}
