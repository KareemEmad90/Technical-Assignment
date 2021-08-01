package pages.cis;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class VehicleInspectionPage {

    WebDriver driver;

    By missingInfoBlocks = By.xpath("//ul[@id='missing-elements-ul-details']//input[not(contains(@type,'hidden')) and not(contains(@readonly,'readonly'))]");

    By missingInfoDialogue = By.id("missing-elements-ul-details");

    By addDefectBtn(String section){return By.xpath("(//ul[@id='tests']/li[@testid and (contains(@data-filtertext,'"+section+"'))]/following-sibling::li/a[@title='Defects'])[1]");}

    By saveBtn = By.xpath("//*[contains(text(),'Save')]");

    By addDiscrepencyBtn(String Section){return By.xpath("(//ul[@id='tests']/li[@testid and (contains(@data-filtertext,'"+Section+"'))]/following-sibling::li//div[@class='action-buttons']/a[@title='Info'])[1]");}

    By continueBtn = By.xpath("(//a[@data-theme='b']//span[contains(text(),'Continue')])[1]");

    public VehicleInspectionPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("user attempts to click on contine")
    public void clickOnContinue(){
        ElementActions.click(driver,continueBtn);
        if (ElementActions.isElementDisplayed(driver,missingInfoDialogue)){
            List<WebElement> emtpyFlds = driver.findElements(missingInfoBlocks);
            for (int i = 0; i < emtpyFlds.size(); i++) {
                if (emtpyFlds.iterator().hasNext()){
                    emtpyFlds.iterator().next().sendKeys("40000");
                }
            }
        }
        ElementActions.click(driver,saveBtn);
    }

}
