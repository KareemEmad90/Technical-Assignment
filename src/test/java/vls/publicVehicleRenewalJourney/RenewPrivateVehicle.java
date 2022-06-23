package vls.publicVehicleRenewalJourney;

import api.AddElectronicInsurance;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
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
import pages.vls.publicVehicleRenewal.IdentityVerification;
import pages.vls.publicVehicleRenewal.ReviewAndPayment;
import pages.vls.publicVehicleRenewal.VehicleInformation;
import pages.vls.publicVehicleRenewal.VehicleNumberPlate;
import pages.vls.sellVehicle.IdentityVerificationPage;
import pages.vls.sellVehicle.VehicleInfoPage;
import pages.vls.sellVehicle.VehicleInspectionPage;

import static com.shaft.driver.DriverFactory.DriverType.DESKTOP_CHROME;

public class RenewPrivateVehicle {

    private WebDriver driver;
    static Logger log = Logger.getLogger(RenewPrivateVehicle.class.getName());
    ChromeOptions options;



    @BeforeMethod
    public void setup() {
        options = new ChromeOptions();
        options.addArguments("incognito");
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSRenewalURL"));
        ChromeCertificatePage ChromeCertificatePage = new ChromeCertificatePage(driver);

    }

    @Step(" Vehicle Renewal Test")
    @Test()
    public void renewPrivateVehicle() throws InterruptedException {

        IdentityVerification searchVehicleDetails = new IdentityVerification(driver);
        searchVehicleDetails.addVehicleInspection();
        searchVehicleDetails.addVehicleInsurance();

        searchVehicleDetails.searchForVehicle();
        searchVehicleDetails.checkMobileNumber();
        searchVehicleDetails.verifyOTP();

        VehicleInformation vehicleDetails = new VehicleInformation(driver);
        vehicleDetails.vehicleInformationPage();


        VehicleNumberPlate vehicleNumberPlate = new VehicleNumberPlate(driver);
        vehicleNumberPlate.vehicleNumberPlatepage();

        ReviewAndPayment reviewPayment = new ReviewAndPayment(driver);
        reviewPayment.reviewAndPaymentPage();


    }


}
