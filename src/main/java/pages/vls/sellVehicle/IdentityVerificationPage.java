package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import data.LoadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IdentityVerificationPage {
    private WebDriver driver;

    By orgOwnerBtn = By.id("OrganizationOwner");
    By authOwnerBtn = By.id("AuthorizedOwner");
    By applicationRefNum = By.name("registeredMobile");
    By authIssuedBtn = By.id("yes");
    By authNotIssuedBtn = By.id("no");
    By associationRefNum = By.xpath("//div[@class='GC_infoIcon']/parent::label/following-sibling::input");   //id missing
    By orgTradeLicenseUpload = By.xpath("//div[@id='uploaderAuthorised']//input"); //id missing
    By proceedToInfo = By.xpath("//div[@class='btnActions']/button"); //id missing

    public void proceedOrgOwner(){
        ElementActions.click(driver,orgOwnerBtn);
    }

    public void authOwner(){
        ElementActions.click(driver,authOwnerBtn);
    }

    public void writeAppRefNum(String refNumber){
        ElementActions.type(driver,applicationRefNum,refNumber);
    }

    public void authIsIssued(){
        ElementActions.click(driver,authIssuedBtn);
    }

    public void authIsNotIssued(){
        ElementActions.click(driver,authNotIssuedBtn);
    }

    public void writeAssociateRefNUm(String RefNum){
        ElementActions.type(driver,associationRefNum,RefNum);
    }

    public void uploadOrgTrade(){
        ElementActions.typeFileLocationForUpload(driver,orgTradeLicenseUpload,System.getProperty("user.dir") + LoadProperties.userData.getProperty("attachmentFilePngPath"));
    }

    public void proceedToInfo(){
        ElementActions.click(driver,proceedToInfo);
    }
}
