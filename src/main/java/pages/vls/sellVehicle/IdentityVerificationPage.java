package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import com.shaft.tools.support.JavaScriptHelper;
import data.LoadProperties;
import org.apache.xpath.operations.Bool;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class IdentityVerificationPage {
    private WebDriver driver;

    public IdentityVerificationPage(WebDriver driver) {
        this.driver = driver;
    }

    By orgOwnerBtn = By.xpath("//input[@id='OrganizationOwner']/following-sibling::label");
    By authOwnerBtn = By.id("AuthorizedOwner");
    By applicationRefNum = By.name("registeredMobile");
    By authIssuedBtn = By.id("yes");
    By authNotIssuedBtn = By.id("no");
    By associationRefNum = By.xpath("//div[@class='GC_infoIcon']/parent::label/following-sibling::input");   //id missing
    By orgTradeLicenseUpload = By.xpath("//div[@id='uploaderAuthorised']//input"); //id missing
    By proceedToInfo = By.xpath("//div[@class='btnActions']/button"); //id missing

    private void proceedOrgOwner() throws InterruptedException {
        ElementActions.click(driver,orgOwnerBtn);
    }

    private void authOwner() {
        ElementActions.click(driver, authOwnerBtn);
    }

    private void writeAppRefNum(String refNumber) {
        ElementActions.type(driver, applicationRefNum, refNumber);
    }

    private void authIsIssued() {
        ElementActions.click(driver, authIssuedBtn);
    }

    private void authIsNotIssued() {
        ElementActions.click(driver, authNotIssuedBtn);
    }

    private void writeAssociateRefNUm(String RefNum) {
        ElementActions.type(driver, associationRefNum, RefNum);
    }

    private void uploadOrgTrade() {
        ElementActions.typeFileLocationForUpload(driver, orgTradeLicenseUpload, System.getProperty("user.dir") + LoadProperties.userData.getProperty("attachmentFilePngPath"));
    }

    private void proceedToInfo() {
        ElementActions.click(driver, proceedToInfo);
    }

    public void OrgOwnerFlow(String RefNumber) throws InterruptedException {
        ElementActions.waitForElementToBePresent(driver, orgOwnerBtn, 2, true);
        proceedOrgOwner();
        writeAssociateRefNUm(RefNumber);
        proceedToInfo();
        proceedToInfo();
    }

    public void authOwnerFlow(Boolean isIssuedByDubaiCourt, String RefNumber) {
        if (isIssuedByDubaiCourt) {
            authIsIssued();
            writeAssociateRefNUm(RefNumber);
        } else {
            authIsNotIssued();
            writeAssociateRefNUm(RefNumber);
            uploadOrgTrade();
        }
    }


}
