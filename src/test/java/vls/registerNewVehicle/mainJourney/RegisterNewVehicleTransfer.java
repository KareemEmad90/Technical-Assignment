package vls.registerNewVehicle.mainJourney;

import api.AddElectronicInsurance;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.DbQueries;
import data.LoadProperties;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.common.ChromeCertificatePage;
import pages.vls.LoginPage;
import pages.vls.sellVehicle.IdentityVerificationPage;
import pages.vls.sellVehicle.VehicleInfoPage;
import pages.vls.sellVehicle.VehicleInspectionPage;
import pages.vls.sellVehicle.backOffice;
import utils.ChassisGeneration;

import static com.shaft.driver.DriverFactory.DriverType.DESKTOP_CHROME;

public class  RegisterNewVehicleTransfer {
    private WebDriver driver;
    static Logger log = Logger.getLogger(RegisterNewVehicleImportDubai.class.getName());
    ChromeOptions options;
    LoginPage vlsLoginPage;
    VehicleInfoPage vehicleInfoPage;
    IdentityVerificationPage identityVerificationPage;
    VehicleInspectionPage vehicleInspectionPage;
    backOffice backOffice;
    String AssocRefNum = "2712021";
    String chassisNum = "6T153SK10V9171004";
    String rtaUnifiedNo="50024163";
    String tradeLicense = "504429";
    String licenseExp = "2025/08/19";
    String licenseSource = "DED-83";
    String Certs = "27/1/2021";
    String getAppRefNo;

    @BeforeMethod
    public void setup() {
        options = new ChromeOptions();
        options.addArguments("incognito");
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSURL"));
        vlsLoginPage = new LoginPage(driver);
        vehicleInfoPage = new VehicleInfoPage(driver);
        identityVerificationPage = new IdentityVerificationPage(driver);
        vehicleInfoPage = new VehicleInfoPage(driver);
        vehicleInspectionPage = new VehicleInspectionPage(driver);

    }

    @Step(" Vehicle Test case")
    @Test()
    public void RegisterFromTransferCertificate() throws InterruptedException {

        vlsLoginPage.corpLogin(tradeLicense, licenseExp, licenseSource);
        identityVerificationPage.proceedOrgOwner();
        identityVerificationPage.clickOnContinueButton();
        chassisNum= ChassisGeneration.ChassisNo();

       // -----------------------------------Add Electronic Insurance-----------------------------------------
        AddElectronicInsurance insurance= new AddElectronicInsurance();
        insurance.elecInsuranceAPI(rtaUnifiedNo,chassisNum);
        // -----------------------------------END Add Electronic Insurance------------------------------------

        // -----------------------------------Add Inspection--------------------------------------------------
        DbQueries dbQueries= new DbQueries();
        dbQueries.addInspectionSync(chassisNum);
        Thread.sleep(3000);
        // -----------------------------------End Add Inspection----------------------------------------------

        vehicleInfoPage.transferExportCert(chassisNum);
        vehicleInfoPage.showAndProceedListedVehicle();
        getAppRefNo= vehicleInfoPage.getAppRefNo();
        vehicleInfoPage.requiredNOCDocuments();
        // -----------------------------------Select Plate-----------------------------------------------------

        vehicleInfoPage.selectPlateDetails();
        // -----------------------------------Select Plate Center----------------------------------------------
        vehicleInfoPage.selectPlateCenter();

        // -----------------------------------Application Document Under Review in VLS BO ----------------------------------------------
        vehicleInfoPage.applicationUnderReview();

        vlsApplicationBO();

        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSURL"));
        vlsLoginPage = new LoginPage(driver);
        vlsLoginPage.corpLogin(tradeLicense, licenseExp, licenseSource);
    }

    public void vlsApplicationBO() throws InterruptedException {
        driver.close();
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSBO"));
        ChromeCertificatePage ChromeCertificatePage = new ChromeCertificatePage(driver);
        ChromeCertificatePage.skipUnsafePage();
        backOffice = new backOffice(driver);
        backOffice.selectLicensingHelpDeskAndLogin();
        backOffice.selectApplication(getAppRefNo);
        backOffice.acceptAttachments();
        driver.close();
    }

}
