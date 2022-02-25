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

public class RegisterNewVehicleJourney {

    private WebDriver driver;
    static Logger log = Logger.getLogger(RegisterNewVehicleJourney.class.getName());
    ChromeOptions options;
    LoginPage vlsLoginPage;
    VehicleInfoPage vehicleInfoPage;
    IdentityVerificationPage identityVerificationPage;
    VehicleInspectionPage vehicleInspectionPage;
    String AssocRefNum = "27/1/2021";
    String chassisNum = "6T153SK10V9171004";

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
    }

    @Step(" Vehicle Test case")
    @Test()
    public void declareVehicleAPITestCase() throws InterruptedException {
        vlsLoginPage.corpLogin("123301", "2025/08/20", "DED-83");
        identityVerificationPage.OrgOwnerFlow(AssocRefNum);
        vehicleInfoPage.transferExportCert(chassisNum);
        vehicleInspectionPage.selectAvailbleAppointment();
//        vehicleInfoPage.uploadDocuments();
//        Thread.sleep(10000);
    }




}
