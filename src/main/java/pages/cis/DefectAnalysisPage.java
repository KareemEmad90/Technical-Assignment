package pages.cis;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DefectAnalysisPage {

    WebDriver driver;

    By confirmDefectsBtn = By.xpath("//span[contains(text(),'I confirm that all inspection findings are authentic')]//ancestor::label");

    By proceedBtn = By.id("confirmForm:confrimBtn");

    public DefectAnalysisPage(WebDriver driver) {
        this.driver = driver;
    }

    public void proceedWithDefects(){
        ElementActions.waitForElementToBePresent(driver,confirmDefectsBtn,5,true);
        ElementActions.click(driver,confirmDefectsBtn);
        ElementActions.click(driver,proceedBtn);
    }
}
