package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SellerSelectBuyer {
    private WebDriver webDriver;
    private By buyerEmiratesID = By.id("emiratesId");
    private By buyerMobileNo = By.id("buyerMobileId");
    private By buyerContinueBtn = By.xpath("//button[text() = 'Continue']");

    public SellerSelectBuyer(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void selectBuyer(String buyerEid, String buyerMobileNumber) {
        ElementActions.type(webDriver, buyerEmiratesID, buyerEid);
        ElementActions.type(webDriver, buyerMobileNo, buyerMobileNumber);
        ElementActions.click(webDriver, buyerContinueBtn);
    }
}
