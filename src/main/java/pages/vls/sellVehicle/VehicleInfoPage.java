package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import data.LoadProperties;
import io.cucumber.java.an.E;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.python.modules._locale._locale;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VehicleInfoPage {

    private WebDriver driver ;
    By importCertificate = By.xpath("//label[@for='importCertificate']");
    By transferExportCertificate = By.xpath("//label[@for='TECertificate']");
    By ownershipCertificate = By.xpath("//label[@for='ownershipCertificate']");
    By sellPurchase = By.xpath("//label[@for='sellPurchase']");
    By dubaiCustoms = By.xpath("//label[@for='dubaiCustoms']");
    By otherEmirateCustoms = By.xpath("//label[@for='otherEmirateCustoms']");
    By certificateVCCNumber=By.id("certificateNumberVCCId");
    By chasisVCCNumber = By.id("chassisNumberVCCId");
    By continueBtn=By.xpath("//button[@class='BtnStyle']");
    By listOfUploadedDocuments=  By.xpath("//div[@class='row fixAlignmentInRow']//div[@id]");

    By registerAdditionalVehicle=By.xpath("//button[contains(text(),'Register Additional Vehicle')]");
    By backBtn=By.xpath("//div[@class='back']");
    By vehicleBox=By.xpath("//div[@class='foundVehicleBox']");
    By foundVehicelMessage=By.xpath("//div[@class='messageBanner T_success']");
    By advertised = By.xpath("//label[@for='yesAdvertising']");
    By notAdvertised =  By.xpath("//label[@for='noAdvertising']");
    By registeredAddVhcl = By.xpath("//button[contains(@class,'additionalVehicleBTN')]");
    By contineBtn = By.xpath("//div[@class='btnActions']/button");
    By transferCert = By.xpath("//input[@id='transferCertificate']/following-sibling::label");
    By otherEmiratCert = By.xpath("//div[@class='GC_dropZoneComponent']//input");
    By regAddVhcl = By.xpath("//div[@class='btnActions']/button");
    By vhcList = By.id("showVehicleListId");
    By proceedToIns = By.xpath("");
    By viewDtlsBtns = By.xpath("");
    By imprtCrtBtn = By.xpath("");
    By closeDlg = By.xpath("");
    By sourceRefNumberTxt = By.id("sourceRefNumber");
    By sourceIssueDateTxt = By.id("sourceIssueDate");
    By emirateList =By.id("emirate");
    By selectEmirates =By.xpath("/html/body/div[2]/div[2]/ul/li[4]/div/div");
    By proceedWithListedVehicleBtn =By.xpath("//*[@id=\"processing\"]/div/div/div[2]/div/div[2]/button");

    By requiredNOCLink =By.xpath("//*[@id=\"processing\"]/div/div/div[2]/div/div/table/tbody/tr/td[4]/button");
    By submitBtn =By.xpath("//*[@id=\"processing\"]/div/div[2]/div/div[2]/button[1]");
    By plateSourceList=By.id("plateSource");
    By selectPlateDetails=By.xpath("//ul//li[@role='option'][1]");
    By plateTypeList=By.id("plateType");
    By logoList=By.id("logo");
    By frontPlat=By.id("frontPlate");
    By backPlate=By.id("backPlate");
    By submitPlateBtn=By.xpath("//button[@class='BtnStyle submitAddPlateBTN']");

    By selectPlateCenterTxt=By.xpath("//input[@placeholder='Search by name or location']");
    By selectCenter=By.xpath("//li//span[contains(text(),'Shamil Qusais')]");

    By applicationUnderReview=By.xpath("//div[@class='requestUnderReview']");
    By getAppRefNo= By.xpath("//div//span[contains(text(),'RVB')]");


    public void applicationUnderReview(){
        ElementActions.isElementDisplayed(driver,applicationUnderReview);
    }

    public String getAppRefNo(){
        String returnAppRefNo=ElementActions.getText(driver,getAppRefNo);
        return returnAppRefNo;
    }

    public VehicleInfoPage(WebDriver driver) {
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
        List <WebElement> elements= driver.findElements(By.xpath("//div[@class='GC_dropZoneComponent']//div//input"));
        if (elements.isEmpty()){
            Assert.fail("No Documents Found To Upload");
        }
        for (WebElement element:elements){
            element.sendKeys(System.getProperty("user.dir") + LoadProperties.userData.getProperty("attachmentFilePngPath"));
        }
    }

    public void isAdvertised(Boolean adevertisd){
        if (adevertisd)
            ElementActions.click(driver,advertised);
        else
            ElementActions.click(driver,notAdvertised);
    }

    public void ProceedToInspection(){
        ElementActions.click(driver,proceedToIns);
    }

    public void viewDetailsForInspection(){
        ElementActions.waitForElementToBePresent(driver,viewDtlsBtns,2,true);
    }


    public void importCertificate(){
        ElementActions.click(driver,imprtCrtBtn);
    }

    public void importedFromDubaiCustoms(String cert){
        ElementActions.click(driver,dubaiCustoms);
        ElementActions.type(driver,certificateVCCNumber,cert);
        ElementActions.click(driver,contineBtn);
    }

    public void importedFromOtherEmirate(String Chasis){
        ElementActions.click(driver,otherEmirateCustoms);
        ElementActions.type(driver,chasisVCCNumber,Chasis);
        ElementActions.click(driver,contineBtn);
    }

    public void transferExportCert(String Chasiss){
        ElementActions.click(driver,transferExportCertificate);
        ElementActions.click(driver,transferCert);
        ElementActions.type(driver,chasisVCCNumber,Chasiss);
        ElementActions.click(driver,contineBtn);
        certificatesInformationsDetails();
        isAdvertised(false);
        uploadDocuments();
        ElementActions.click(driver,continueBtn);
    }

    public void selectPlateDetails(){
        ElementActions.click(driver,plateSourceList);
        ElementActions.click(driver,selectPlateDetails);

        ElementActions.click(driver,plateTypeList);
        ElementActions.click(driver,selectPlateDetails);

        ElementActions.click(driver,logoList);
        ElementActions.click(driver,selectPlateDetails);

        ElementActions.click(driver,frontPlat);
        ElementActions.click(driver,selectPlateDetails);

        ElementActions.click(driver,backPlate);
        ElementActions.click(driver,selectPlateDetails);

        ElementActions.click(driver,submitPlateBtn);
        ElementActions.click(driver,continueBtn);
    }

    public void selectPlateCenter(){
        ElementActions.type(driver,selectPlateCenterTxt,"Shamil Qusais");
        ElementActions.click(driver,selectCenter);
        ElementActions.click(driver,continueBtn);
    }

    public void showAndProceedListedVehicle(){
        ElementActions.click(driver,vhcList);
        ElementActions.click(driver,continueBtn);

    }

    public String getCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        LocalDateTime now = LocalDateTime.now();
        String currentDate=dtf.format(now);
        return currentDate;
    }

    public void certificatesInformationsDetails(){
        ElementActions.type(driver,sourceRefNumberTxt,"123456");
        ElementActions.type(driver,sourceIssueDateTxt,getCurrentDate());
        //ElementActions.select(driver,emirateList,"Sharjah" );
        ElementActions.click(driver,emirateList);
        ElementActions.click(driver,selectEmirates);

    }

    public void registerAdditionalVehicles(){
        ElementActions.click(driver,regAddVhcl);

    }
    public void openVehiclesList(){
        ElementActions.click(driver,vhcList);

    }
    public void proceedWithListedVehicle(){
        ElementActions.click(driver,proceedWithListedVehicleBtn);

    }

    public void requiredNOCDocuments(){
        ElementActions.click(driver,requiredNOCLink);
        uploadDocuments();
        ElementActions.click(driver,submitBtn);

    }
}
