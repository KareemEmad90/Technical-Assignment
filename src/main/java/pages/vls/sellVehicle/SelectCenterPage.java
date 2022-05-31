package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectCenterPage {
    private WebDriver driver;

    public SelectCenterPage(WebDriver driver) {
        this.driver = driver;
    }




    By searchCenterTxt = By.xpath("//input[contains(@placeholder,'Search by name or location')]");
    By selectCenter = By.xpath("//span[contains(.,'Shamil Qusais Vehicle Testing and Registration')]");
    By continueBtn = By.xpath("//button[contains(@class,'BtnStyle')]");
    By applicationRefNo = By.xpath("//div[@class ='rightSideRef']//span[2]");




    public void selectCollectPlateCenter() throws InterruptedException {
        ElementActions.type(driver , searchCenterTxt , "Shamil Qusais Vehicle Testing and Registration");
        ElementActions.click(driver , selectCenter);
        ElementActions.click(driver , continueBtn);


    }

    public String getAppRefNo ()
    {

        String getApplicationRefNo = ElementActions.getText(driver , applicationRefNo);
        return getApplicationRefNo;

    }



}
