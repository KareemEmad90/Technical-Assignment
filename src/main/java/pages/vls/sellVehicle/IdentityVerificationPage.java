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
    By authOwnerBtn = By.xpath("//label[@for='AuthorizedOwner']");
    By applicationRefNum = By.name("registeredMobile");
    By authIssuedBtn = By.id("yes");
    By authNotIssuedBtn = By.id("no");
    By associationRefNum = By.xpath("//div[@class='GC_infoIcon']/parent::label/following-sibling::input");   //id missing
    By orgTradeLicenseUpload = By.xpath("//div[@id='uploaderAuthorised']//input"); //id missing
    By proceedToInfo = By.xpath("//div[@class='btnActions']/button"); //id missing
    By continueBTN = By.xpath("//*[@id=\"processing\"]/div/div/div[2]/button");
    By advertisingNo = By.xpath("//label[@for='noAdvertising']");
    By advertisingYes = By.xpath("//label[@for='yesAdvertising']");
    By cancelActiveJourney = By.xpath("//label[@for='no']");
    By complatelActiveJourney = By.xpath("//label[@for='yes']");
    By continueCancelJourneyBtn = By.xpath("//*[@id='app-view-container']/main/div/div/div/div[2]/button");




    private void proceedOrgOwner() throws InterruptedException {
        ElementActions.click(driver,orgOwnerBtn);
    }
    private void clickOnContinueButton() throws InterruptedException {
        ElementActions.click(driver,continueBTN);
    }

    private void authOwner() {
        ElementActions.click(driver, authOwnerBtn);
    }

    private void writeAppRefNum(String refNumber) {
        ElementActions.type(driver, applicationRefNum, refNumber);
    }

    private void authIsIssued() {
        ElementActions.click(driver, authOwnerBtn);
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

    public void OrgOwnerFlow() throws InterruptedException {
        ElementActions.waitForElementToBePresent(driver, orgOwnerBtn, 2, true);
        proceedOrgOwner();
        clickOnContinueButton();
    }

/*    public void authOwnerFlow(Boolean isIssuedByDubaiCourt, String RefNumber) {
        if (isIssuedByDubaiCourt) {
            authIsIssued();
            writeAssociateRefNUm(RefNumber);
        } else {
            authIsNotIssued();
            writeAssociateRefNUm(RefNumber);
            uploadOrgTrade();
        }
    }*/

    public void authOwnerFlow() throws InterruptedException {
        authIsIssued();
        clickOnContinueButton();

    }

/*    public void cancelActiveJourney(Boolean journeyStatus) throws InterruptedException {
        if (journeyStatus) {
            ElementActions.click(driver, cancelActiveJourney);
            ElementActions.click(driver, continiueCancelJourney);


        }
        else
            ElementActions.click(driver,complatelActiveJourney);
            ElementActions.click(driver, continiueCancelJourney);
    }*/



    public void cancelActiveJourney(WebDriver driver) throws InterruptedException {

        Thread.sleep(3000);
        if (driver.getPageSource().contains("Would you like to proceed with it?")) {
            ElementActions.click(driver, cancelActiveJourney);
            ElementActions.click(driver, continueCancelJourneyBtn);
        }
        else
            System.out.println("No Active Journey");

    }



}
