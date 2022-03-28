package vls.registerNewVehicle.mainJourney;

import api.AddElectronicInsurance;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import com.shaft.validation.Assertions;
import data.DbQueries;
import data.LoadProperties;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.common.ChromeCertificatePage;
import pages.vls.LoginPage;
import pages.vls.sellVehicle.*;

import static com.shaft.driver.DriverFactory.DriverType.DESKTOP_CHROME;

public class RegisterNewVehicleImportOthrEmirate {
    private WebDriver driver;
    static Logger log = Logger.getLogger(RegisterNewVehicleImportDubai.class.getName());
    ChromeOptions options;
    LoginPage vlsLoginPage;
    VehicleInfoPage vehicleInfoPage;
    IdentityVerificationPage identityVerificationPage;
    VehicleInspectionPage vehicleInspectionPage;
    VehicleInsurancePage vehicleInsurancePage;
    VehicleNumberPlatePage VehicleNumberPlatePage;
    DbQueries getQueries = new DbQueries();
    String AssocRefNum = "2712021";
    String chassisNum = "VLSAUTOMATION1111";
    String tradeLicense = "185217";
    String licenseExp = "2025/08/20";
    String licenseSource = "TRF-5";
    String rtaUnifiedNumber = "50152098";



    public void setup() {
        options = new ChromeOptions();
        options.addArguments("incognito");
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSURL"));
        vlsLoginPage = new LoginPage(driver);


    }



    @BeforeTest
    public void beforeTest() {


        getQueries.deleteActiveInspectionFromVls(chassisNum);
        getQueries.addInspectionVls(chassisNum);

        AddElectronicInsurance addElectronicInsurance = new AddElectronicInsurance();
        addElectronicInsurance.elecInsuranceAPI(rtaUnifiedNumber, chassisNum);

    }


    @Step(" Vehicle Test case")
    @Test()
    public void ImportOtherEmirate() throws InterruptedException {
        setup();
        vehicleInfoPage = new VehicleInfoPage(driver);
        identityVerificationPage = new IdentityVerificationPage(driver);
        vehicleInfoPage = new VehicleInfoPage(driver);
        vehicleInspectionPage = new VehicleInspectionPage(driver);
        vehicleInsurancePage = new VehicleInsurancePage(driver);
        vlsLoginPage.corpLogin(tradeLicense, licenseExp, licenseSource);
        identityVerificationPage.cancelActiveJourney(driver);
        identityVerificationPage.authOwnerFlow();
        vehicleInfoPage.transferExportCert(chassisNum);
        vehicleInfoPage.certificatesInformationsDetails();
        vehicleInfoPage.isAdvertised(false);
        vehicleInfoPage.registerAdditionalVehicles();
        vehicleInfoPage.openVehiclesList();
        vehicleInfoPage.proceedWithListedVehicle();
        vehicleInfoPage.requiredNOCDocuments();
        Thread.sleep(3000);

        VehicleNumberPlatePage selectVehiclePlatesFromRTA= new VehicleNumberPlatePage(driver);
        selectVehiclePlatesFromRTA.selectVehiclePlatesFromRTA();

        SelectCenterPage selectCenter = new SelectCenterPage(driver);
        selectCenter.selectCollectPlateCenter();
        String getAppNumber = selectCenter.getAppRefNo();
        getQueries.updateJourneyStatus(getAppNumber);

        driver.close();
        setup();
        vlsLoginPage.corpLogin(tradeLicense, licenseExp, licenseSource);

        identityVerificationPage.continueActiveJourney(driver);







    }
}
