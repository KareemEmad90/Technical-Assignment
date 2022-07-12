package pages.vls.platesBox;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class PlateMainPage {


    private WebDriver driver ;

    public PlateMainPage(WebDriver driver) {
        this.driver = driver;
    }

    By switchOwned=By.xpath("//div[@class='switch']//label[contains(text(),'Owned')]/following-sibling::div[@class='p-inputswitch p-component p-inputswitch-checked']");
    By switchReserved=By.xpath("//div[@class='switch']//label[contains(text(),'Reserved')]/following-sibling::div[@class='p-inputswitch p-component p-inputswitch-checked']");
    By switchOnVehicle=By.xpath("//div[@class='switch']//label[contains(text(),'On vehicle')]/following-sibling::div[@class='p-inputswitch p-component p-inputswitch-checked']");
    By searchBox=By.xpath("//div[@class='search']//input[@type='text']");
    By viewPlateAction=By.xpath("//div[@class='actionsList']");
    By transferPlateLink=By.xpath("//button[contains(text(),'Transfer Number')]");
    By renewPlateLink=By.xpath("//button[contains(text(),'Renew')]");



    public void verifySwitchDisplayed(){
        ElementActions.waitForElementToBePresent(driver,switchOwned,5,true);
        ElementActions.waitForElementToBePresent(driver,switchReserved,5,true);
        ElementActions.waitForElementToBePresent(driver,switchOnVehicle,5,true);
    }

    public void switchOwnedOff(){
        ElementActions.click(driver,switchOwned);
    }
    public void switchReservedOff(){
        ElementActions.click(driver,switchReserved);
    }
    public void switchOnVehicleOff(){
        ElementActions.click(driver,switchOnVehicle);
    }

    public void searchForPlate(String plateNo){
        ElementActions.type(driver,searchBox,plateNo);
    }

    public void transferPlate(){
        ElementActions.click(driver,viewPlateAction);
        ElementActions.click(driver,transferPlateLink);
    }

    public void selectReservedPlate(){

        List<WebElement> platesCards=driver.findElements(viewPlateAction);

        if (platesCards.isEmpty()){

            Assert.fail("No Reserved Plates Found , Please ");
        }

        for (WebElement element :  platesCards) {
            element.click();
            break;
        }


    }

    public void renewPlateAction(){
        ElementActions.click(driver,viewPlateAction);
        ElementActions.click(driver,renewPlateLink);
    }

    public void renewPlateLinkAction(){

        List<WebElement> renewPlatesAction=driver.findElements(renewPlateLink);
        for (WebElement element :  renewPlatesAction) {
            element.click();
            break;
        }
    }

}
