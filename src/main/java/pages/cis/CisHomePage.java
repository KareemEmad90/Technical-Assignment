package pages.cis;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CisHomePage {
    private WebDriver driver ;
    By navigatorBtn = By.xpath("//*[@title='Navigation']");
    By newVehicleInspectionBtn = By.linkText("New Vehicle Inspection");

    By supervisorQueueBtn = By.xpath("//a[contains(text(),'Supervisor Queue')]");

    @Step("user click on insepctor queueu button")
    public void clickOnSupervisorQueu(){
        ElementActions.click(driver,supervisorQueueBtn);
    }

    @Step("Click on navigation button")
    public void clickOnNavigationBtn() {
        ElementActions.click(driver, navigatorBtn);
    }

    @Step("Click on new vehicle inspection menu item")
    public void clickOnNewVehicleInspectionMenuItem() {
        ElementActions.waitForElementToBePresent(driver, newVehicleInspectionBtn, 3, true);
        ElementActions.click(driver, newVehicleInspectionBtn);
    }


    public CisHomePage(WebDriver driver){
        this.driver = driver ;
    }
}


//*[@id="inspectorForm"]/div[6]/h4/a/span/span[1]