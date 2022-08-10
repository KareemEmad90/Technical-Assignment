package vls.vehicleRenewal.online;

import api.AddElectronicInsurance;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.DbQueries;
import data.ExcelReader;
import data.LoadProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Dashboard.Corporate.CorporateDashboardPage;
import pages.Individual.IndividualDashboardPage;
import pages.common.ChromeCertificatePage;
import pages.serviceDelivery.CourierDeliveryTypesPage;
import pages.vls.VehicleDetailsPage;
import pages.vls.VehiclesPage;
import pages.vls.publicVehicleRenewal.IdentityVerification;
import pages.vls.publicVehicleRenewal.VehicleInformation;

import java.io.IOException;
import java.sql.SQLException;

import static com.shaft.driver.DriverFactory.DriverType.DESKTOP_CHROME;

public class VehicleRenewalPublicTest {
    WebDriver driver;
    CorporateDashboardPage corporateDashboardPage;
    ChromeCertificatePage chromeCertificatePage;
    IndividualDashboardPage individualDashboardPage;
    VehiclesPage vehiclesPage ;
    VehicleDetailsPage vehicleDetailsPage ;
    CourierDeliveryTypesPage courierDeliveryTypesPage ;
    String chassisNo,rtaUnifiedNo;
    @BeforeMethod
    public void beforeTest() throws SQLException, ClassNotFoundException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSRenewalURL"));
        ChromeCertificatePage ChromeCertificatePage = new ChromeCertificatePage(driver);

    }


    @DataProvider(name = "vehicleInfo")
    public Object[][] vehicleData(ITestContext context) throws IOException {
        int TotalNumberOfCols = 6;
        ExcelReader ER = new ExcelReader();
        String sheetName = "vehicleRenewal";
        String excelFileName = LoadProperties.userData.getProperty("vehicleRenewal");
        return ER.getExcelData(excelFileName, sheetName, TotalNumberOfCols);
    }



    @Test(dataProvider="vehicleInfo")
    public void vehicleRenewalDigitalTest(String vehicle_Class,String plateCategory , String vehicleMinWeight,String vehicleMaxWeight,String mortgageStatus, String profileClassification) throws InterruptedException, SQLException, ClassNotFoundException {
        DbQueries dbQueries = new DbQueries();
        String[] vehicle = dbQueries.getExpiredVehicle( vehicle_Class, plateCategory ,  vehicleMinWeight, vehicleMaxWeight, mortgageStatus,  profileClassification);
        chassisNo =vehicle[0];
        rtaUnifiedNo=vehicle[1];
        corporateDashboardPage = new CorporateDashboardPage(driver);
        individualDashboardPage = new IndividualDashboardPage(driver);
        chromeCertificatePage.skipUnsafePage();
        AddElectronicInsurance insurance= new AddElectronicInsurance();
        insurance.elecInsuranceAPI(rtaUnifiedNo,chassisNo);
        dbQueries.addTest(chassisNo);
        dbQueries.resetviloation(rtaUnifiedNo, chassisNo);
        dbQueries.removeBlocker(rtaUnifiedNo);
        VehicleInformation vehicleInformation = new VehicleInformation(driver);
        IdentityVerification identityVerification = new IdentityVerification(driver);
        identityVerification.searchForVehicle();
        identityVerification.checkMobileNumber();
        identityVerification.verifyOTP();
       /* corporateDashboardPage.sddiLogin(rtaUnifiedNo);
        individualDashboardPage.clickVehiclesBox();
        vehiclesPage = new VehiclesPage(driver);
        vehiclesPage.searchForVehicle(chassisNo);
        vehiclesPage.selectVehicle();
        vehicleDetailsPage = new VehicleDetailsPage(driver);
        vehicleDetailsPage.selectService("renew");
        vehicleDetailsPage.cancelJourney();

        vehicleInformation.vehicleInformationPage();
        VehicleNumberPlate vehicleNumberPlate = new VehicleNumberPlate(driver);
        vehicleNumberPlate.vehicleNumberPlatepage();
        ReviewAndPayment reviewPayment = new ReviewAndPayment(driver);
        reviewPayment.selectDigitalDelivery();
        reviewPayment.acceptTermsAndConditions();
        reviewPayment.reviewAndPaymentPage();
        RenewalConfirmationPage renewalConfirmationPage = new RenewalConfirmationPage(driver);
        renewalConfirmationPage.downloadProducts();

        */

    }


    @AfterMethod
    public void afterMethod() {
        BrowserActions.closeCurrentWindow(driver);
    }

}
