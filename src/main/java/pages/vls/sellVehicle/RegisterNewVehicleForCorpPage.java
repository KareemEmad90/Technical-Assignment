package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import data.LoadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class RegisterNewVehicleForCorpPage {

    private WebDriver driver ;
    By importCertificate = By.xpath("//label[@for='importCertificate']");
    By transferExportCertificate = By.xpath("//label[@for='TECertificate']");
    By ownershipCertificate = By.xpath("//label[@for='ownershipCertificate']");
    By sellPurchase = By.xpath("//label[@for='sellPurchase']");
    By dubaiCustoms = By.xpath("//label[@for='dubaiCustoms']");
    By otherEmirateCustoms = By.xpath("//label[@for='otherEmirateCustoms']");
    By certificateVCCNumber=By.xpath("//input[@id='certificateNumberVCCId']");
    By continueBtn=By.xpath("//button[@class='BtnStyle']");
    By listOfUploadedDocuments=  By.xpath("//div[@class='row fixAlignmentInRow']//div[@id]");
    List <WebElement> elements= driver.findElements(By.xpath("//div[@class='GC_dropZoneComponent']//div//input"));
    By registerAdditionalVehicle=By.xpath("//button[contains(text(),'Register Additional Vehicle')]");
    By backBtn=By.xpath("//div[@class='back']");
    By vehicleBox=By.xpath("//div[@class='foundVehicleBox']");
    By foundVehicelMessage=By.xpath("//div[@class='messageBanner T_success']");

    public RegisterNewVehicleForCorpPage(WebDriver driver) {
        this.driver = driver;
    }

    public void importCertificateDubaiCustoms(){
        ElementActions.click(driver,importCertificate);
        ElementActions.click(driver,dubaiCustoms);
        ElementActions.type(driver,certificateVCCNumber,"123456");
        ElementActions.click(driver,continueBtn);
        ElementActions.isElementDisplayed(driver,vehicleBox);
        ElementActions.isElementDisplayed(driver,foundVehicelMessage);
    }

    public void uploadDocuments(){

        if (elements.isEmpty()){
            Assert.fail("No Documents Found To Upload");
        }

        for (WebElement element:elements){

            element.sendKeys(System.getProperty("user.dir") + LoadProperties.userData.getProperty("attachmentFilePngPath"));
        }

    }


}
