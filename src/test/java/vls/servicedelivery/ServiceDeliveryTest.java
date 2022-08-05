package vls.servicedelivery;

import api.AddElectronicInsurance;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.DbQueries;
import data.LoadProperties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Dashboard.Corporate.CorporateDashboardPage;
import pages.Individual.IndividualDashboardPage;
import pages.common.ChromeCertificatePage;
import pages.serviceDelivery.CourierDeliveryTypesPage;
import pages.vls.VehicleDetailsPage;
import pages.vls.VehiclesPage;
import pages.vls.publicVehicleRenewal.VehicleInformation;
import pages.vls.publicVehicleRenewal.VehicleNumberPlate;

import java.sql.SQLException;

public class ServiceDeliveryTest {
    WebDriver driver;
    CorporateDashboardPage corporateDashboardPage;
    ChromeCertificatePage chromeCertificatePage;
    IndividualDashboardPage individualDashboardPage;
    VehiclesPage vehiclesPage ;
    VehicleDetailsPage vehicleDetailsPage ;
    CourierDeliveryTypesPage courierDeliveryTypesPage ;
    @BeforeMethod
    public void beforeTest() throws SQLException, ClassNotFoundException {
        driver = BrowserFactory.getBrowser();
        chromeCertificatePage = new ChromeCertificatePage(driver);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("test_Login"));
        corporateDashboardPage = new CorporateDashboardPage(driver);
        individualDashboardPage = new IndividualDashboardPage(driver);
        chromeCertificatePage.skipUnsafePage();
        DbQueries dbQueries = new DbQueries();
        String[] vehicle = dbQueries.getVehiclesReadyForRenewal();
        String ChassisNo = "19XFB2642EE900908";//vehicle[0];
        String TrafficCodeNumber = "10108442";//vehicle[1];
        String  PlateNumber= vehicle[2];
        String  PlateCode = vehicle[3];
        String  MobileNumber = vehicle[4];
        AddElectronicInsurance insurance= new AddElectronicInsurance();
        insurance.elecInsuranceAPI(TrafficCodeNumber,ChassisNo);
        dbQueries.addTest(ChassisNo);
        dbQueries.resetviloation(TrafficCodeNumber, ChassisNo);
        dbQueries.removeBlocker(TrafficCodeNumber);
        corporateDashboardPage.sddiLogin(TrafficCodeNumber);
        individualDashboardPage.clickVehiclesBox();
        vehiclesPage = new VehiclesPage(driver);
        vehiclesPage.searchForVehicle(ChassisNo);
        vehiclesPage.selectVehicle();
        vehicleDetailsPage = new VehicleDetailsPage(driver);
        vehicleDetailsPage.selectService("renew");
        //vehicleDetailsPage.cancelJourney();
        VehicleInformation vehicleInformation = new VehicleInformation(driver);
        vehicleInformation.vehicleInformationPage();
        VehicleNumberPlate vehicleNumberPlate = new VehicleNumberPlate(driver);
        vehicleNumberPlate.vehicleNumberPlatepage();

        courierDeliveryTypesPage = new CourierDeliveryTypesPage(driver);
    }
    @Test
    public void premiumDeliveryTest() throws InterruptedException {
        courierDeliveryTypesPage.selectDeliveryType("PREMIUM");
        /*ReviewAndPayment reviewPayment = new ReviewAndPayment(driver);
        reviewPayment.reviewAndPaymentPage();*/
    }
    @Test
    public void sameDayDeliveryTest(){
        courierDeliveryTypesPage.selectDeliveryType("SAME_DAY");

    }


    @Test
    public void standardDeliveryTest(){
        courierDeliveryTypesPage.selectDeliveryType("STANDARD");
    }

    @Test
    public void internationalDeliveryTest(){
        courierDeliveryTypesPage.selectDeliveryType("INTERNATIONAL");

    }



    @AfterMethod
    public void afterMethod() {
        BrowserActions.closeCurrentWindow(driver);
    }

}
