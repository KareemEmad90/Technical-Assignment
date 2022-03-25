package vls.registerNewVehicle.mainJourney;

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
import pages.vls.sellVehicle.IdentityVerificationPage;
import pages.vls.sellVehicle.VehicleInfoPage;
import pages.vls.sellVehicle.VehicleInspectionPage;
import pages.vls.sellVehicle.VehicleInsurancePage;

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
    String AssocRefNum = "2712021";
    String chassisNum = "VLSAUTOMATION1111";
    String tradeLicense = "185217";
    String licenseExp = "2025/08/20";
    String licenseSource = "TRF-5";

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
        vehicleInsurancePage = new VehicleInsurancePage(driver);


    }

    @BeforeTest
    public void beforeTest() {
        DbQueries getQueries = new DbQueries();
        getQueries.addInspectionVls(chassisNum);

    }


    @Step(" Vehicle Test case")
    @Test()
    public void ImportOtherEmirate() throws InterruptedException {
        vlsLoginPage.corpLogin(tradeLicense, licenseExp, licenseSource);
        //identityVerificationPage.cancelActiveJourney(true);
        identityVerificationPage.authOwnerFlow();
        vehicleInfoPage.transferExportCert(chassisNum);
        vehicleInfoPage.certificatesInformationsDetails();
        vehicleInfoPage.isAdvertised(false);
        vehicleInfoPage.registerAdditionalVehicles();
        vehicleInfoPage.openVehiclesList();
        vehicleInfoPage.proceedWithListedVehicle();
        vehicleInfoPage.requiredNOCDocuments();
        //vehicleInfoPage.importedFromOtherEmirate(chassisNum);
        //vehicleInspectionPage.selectAvailbleAppointment();
        //Assertions.assertTrue(vehicleInsurancePage.verifyChasisInsurance(chassisNum));
//        vehicleInfoPage.uploadDocuments();
//        Thread.sleep(10000);
    }
}
