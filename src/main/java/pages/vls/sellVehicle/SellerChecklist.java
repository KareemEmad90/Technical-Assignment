package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SellerChecklist {
    private WebDriver webDriver;
    private By checklist1 = By.id("makeSureYouHaveId");
    private By checklist2 = By.id("buyersEmiratesNumberId");
    private By checklist3 = By.id("buyersMobileNumberId");
    private By checklist4 = By.id("agreedVehiclePriceId");
    private By continuebtn = By.xpath("//button[text() = 'Continue']");


    public void sellerSelectCheckList() {
        ElementActions.click(webDriver, checklist1);
        ElementActions.click(webDriver, checklist2);
        ElementActions.click(webDriver, checklist3);
        ElementActions.click(webDriver, checklist4);
        ElementActions.click(webDriver, continuebtn);
    }

    public SellerChecklist(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
