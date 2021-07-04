package pages.cis;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VisualInspectionPage {
    By continueBtn = By.xpath("//*[@onclick='validateNonVerified()'][1]");

    By entryForm = By.id("elementsForm");

    By grossWghtVeihcle = By.id("elementsForm:j_idt15:0:j_idt17:tvl");

    By emptyWght = By.id("//input[@id='elementsForm:j_idt15:1:j_idt17:tvl']");

    By saveBtm = By.id("elementsForm:doSaveBtn");

    private WebDriver driver;

    public VisualInspectionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void visualInsContinue() {
        ElementActions.click(driver, continueBtn);
    }

    public void proceedWithViehcleWhgt(String groosWeight,String emptyWaght){
        ElementActions.waitForElementToBePresent(driver,entryForm,5,true);
        String grossWghtOrignal = ElementActions.getAttribute(driver,grossWghtVeihcle,"value");
        if (grossWghtOrignal.isEmpty()){
            ElementActions.type(driver,grossWghtVeihcle,groosWeight);
            ElementActions.type(driver,emptyWght,emptyWaght);
            ElementActions.click(driver,saveBtm);
        }else
        {
            ElementActions.type(driver,emptyWght,emptyWaght);
            ElementActions.click(driver,saveBtm);
        }
    }


}
