package vls.registerNewVehicle.mainJourney;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
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

import static com.shaft.driver.DriverFactory.DriverType.DESKTOP_CHROME;

public class RegisterNewVehicleImportDubai {

    private WebDriver driver;
    static Logger log = Logger.getLogger(RegisterNewVehicleImportDubai.class.getName());
    ChromeOptions options;
    LoginPage vlsLoginPage;
    VehicleInfoPage vehicleInfoPage;
    IdentityVerificationPage identityVerificationPage;
    VehicleInspectionPage vehicleInspectionPage;
    String AssocRefNum = "2712021";
    String chassisNum = "6T153SK10V9171004";
    String tradeLicense = "505282";
    String licenseExp = "2022/04/28";
    String licenseSource = "DED-83";
    String Certs = "27/1/2021";

    @BeforeMethod
    public void setup() {
        options = new ChromeOptions();
        options.addArguments("incognito");
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSURL"));
        ChromeCertificatePage ChromeCertificatePage = new ChromeCertificatePage(driver);
        ChromeCertificatePage.skipUnsafePage();
        vlsLoginPage = new LoginPage(driver);
        vehicleInfoPage = new VehicleInfoPage(driver);
        identityVerificationPage = new IdentityVerificationPage(driver);
        vehicleInfoPage = new VehicleInfoPage(driver);
        vehicleInspectionPage = new VehicleInspectionPage(driver);
    }

    @Step(" Vehicle Test case")
    @Test()
    public void ImportDubaiCustoms() throws InterruptedException {
        vlsLoginPage.corpLogin(tradeLicense, licenseExp, licenseSource);
        identityVerificationPage.OrgOwnerFlow(AssocRefNum);
        vehicleInfoPage.transferExportCert(chassisNum);
        vehicleInfoPage.importedFromDubaiCustoms(Certs);
        vehicleInspectionPage.selectAvailbleAppointment();

//        vehicleInfoPage.uploadDocuments();
//        Thread.sleep(10000);
    }


}
