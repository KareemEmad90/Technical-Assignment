package pages.vls;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehicleDetailsPage {
    WebDriver pdriver ;
    By serviceIcon = By.xpath("//*[@class='renew']");
    By yesBtn = By.xpath("//label[@for='yes']");
    By noBtn =  By.xpath("//label[@for='no']");
    By continueBtn = By.xpath("//button[@class='BtnStyle']");


    @Step("Select service {0}")
    public void selectService(String serviceName){
        ElementActions.click(pdriver,By.xpath("//*[@class='"+serviceName+"']"));
    }

    @Step("Select resume journey Yes")
    public void resumeJourney(){
        ElementActions.click(pdriver,yesBtn);
        ElementActions.click(pdriver,continueBtn);
    }
    @Step("Select cancel journey ")
    public void cancelJourney(){
        ElementActions.click(pdriver,noBtn);
        ElementActions.click(pdriver,continueBtn);
    }

    public VehicleDetailsPage(WebDriver driver){
        this.pdriver= driver;
    }
}
