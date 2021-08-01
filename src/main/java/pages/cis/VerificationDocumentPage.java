package pages.cis;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VerificationDocumentPage {

    WebDriver driver;

    By savaBtn = By.id("payCollectFeesForm:continue-button");

    By okBtn = By.xpath("//a[@data-handler='1']");

    public VerificationDocumentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void proceedWithVerificationDocs(){
        ElementActions.waitForElementToBePresent(driver,savaBtn,3,true);
        ElementActions.click(driver,savaBtn);
        ElementActions.waitForElementToBePresent(driver,okBtn,3,true);
        ElementActions.click(driver,okBtn);
    }
}
