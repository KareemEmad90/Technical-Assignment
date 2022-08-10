package vls.servicedelivery;

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
import pages.serviceDelivery.CourierDeliveryAddressPage;
import pages.serviceDelivery.CourierDeliveryTypesPage;
import pages.vls.VehicleDetailsPage;
import pages.vls.VehiclesPage;

import java.sql.SQLException;

public class ServiceDeliveryTest {
    WebDriver driver;
    CorporateDashboardPage corporateDashboardPage;
    ChromeCertificatePage chromeCertificatePage;
    IndividualDashboardPage individualDashboardPage;
    VehiclesPage vehiclesPage ;
    VehicleDetailsPage vehicleDetailsPage ;
    CourierDeliveryTypesPage courierDeliveryTypesPage ;
    String chassisNo,rtaUnifiedNo,eidNo,eidExpiryDate,vehicleExpiryDate,plateNo,plateCode,mobileNo,vehicleEmptyWeight,profileCategory;
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
        String ChassisNo = "1C4BJWAG3GL281207";//vehicle[0];
        String TrafficCodeNumber = "10343518";//vehicle[1];
        /*String  PlateNumber= vehicle[2];
        String  PlateCode = vehicle[3];
        String  MobileNumber = vehicle[4];
        AddElectronicInsurance insurance= new AddElectronicInsurance();
        insurance.elecInsuranceAPI(TrafficCodeNumber,ChassisNo);
        dbQueries.addTest(ChassisNo);
        dbQueries.resetviloation(TrafficCodeNumber, ChassisNo);
        dbQueries.removeBlocker(TrafficCodeNumber);

         */
        corporateDashboardPage.sddiLogin(TrafficCodeNumber);
        individualDashboardPage.clickVehiclesBox();
        vehiclesPage = new VehiclesPage(driver);
        vehiclesPage.searchForVehicle(ChassisNo);
        vehiclesPage.selectVehicle();
        vehicleDetailsPage = new VehicleDetailsPage(driver);
        vehicleDetailsPage.selectService("renew");
        vehicleDetailsPage.resumeJourney();
       /* VehicleInformation vehicleInformation = new VehicleInformation(driver);
        vehicleInformation.vehicleInformationPage();
        VehicleNumberPlate vehicleNumberPlate = new VehicleNumberPlate(driver);
        vehicleNumberPlate.vehicleNumberPlatepage();
*/
        courierDeliveryTypesPage = new CourierDeliveryTypesPage(driver);
    }
    @Test
    public void premiumDeliveryTest() throws InterruptedException {

        courierDeliveryTypesPage.selectDeliveryType("PREMIUM");


    }
    @Test
    public void sameDayDeliveryTest(){
        courierDeliveryTypesPage.selectDeliveryType("SAME_DAY");

    }


    @Test
    public void standardDeliveryTest(){
        courierDeliveryTypesPage.selectDeliveryType("STANDARD");
        CourierDeliveryAddressPage courierDeliveryAddressPage = new CourierDeliveryAddressPage(driver);
        //courierDeliveryAddressPage.fillApartmentAddress();
        courierDeliveryAddressPage.fillVillaAddress();
        courierDeliveryAddressPage.selectCity("Dubai");
        courierDeliveryAddressPage.selectArea("Abu Hail ( Deira )");
        courierDeliveryAddressPage.markPreferredAddress();
        courierDeliveryAddressPage.fillContactInfo("505155849","2505155849");
        courierDeliveryAddressPage.selectDate("15");
        courierDeliveryAddressPage.checkTermsAndConditions();
        courierDeliveryAddressPage.clickOnSaveResume();
    }

    @Test
    public void internationalDeliveryTest(){
        courierDeliveryTypesPage.selectDeliveryType("INTERNATIONAL");

    }

    private void getVehicle(String vehicle_Class,String plateCategory , String vehicleMinWeight,String vehicleMaxWeight,String mortgageStatus, String profileClassification){
        DbQueries dbQueries = new DbQueries();
        String[] vehicle = dbQueries.getExpiredVehicle( vehicle_Class, plateCategory ,  vehicleMinWeight, vehicleMaxWeight, mortgageStatus,  profileClassification);

    }


    @AfterMethod
    public void afterMethod() {
      //  BrowserActions.closeCurrentWindow(driver);
    }

}
