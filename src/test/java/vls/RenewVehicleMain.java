package vls;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.DbQueries;
import data.LoadProperties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.cis.*;
import pages.common.ChromeCertificatePage;

public class RenewVehicleMain {
    WebDriver driver ;
    String chassisNumber , eid , plateNo ;
    @BeforeMethod
    public void setup(){
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("cisURL"));
        ChromeCertificatePage chromeCertificatePage = new ChromeCertificatePage(driver);
        chromeCertificatePage.skipUnsafePage();
        VehicleDiscrepanciesPage vehicleDiscrepanciesPage = new VehicleDiscrepanciesPage(driver);
        CisLoginPage cisLoginPage = new CisLoginPage(driver);
        CisHomePage cisHomePage = new CisHomePage(driver);
        DefectAnalysisPage defectAnalysisPage = new DefectAnalysisPage(driver);
        inspectionResultsPage inspectionResultsPage = new inspectionResultsPage(driver);
        NewVehicleInspectionPage newVehicleInspectionPage = new NewVehicleInspectionPage(driver);
        SearchVehiclePage searchVehiclePage = new SearchVehiclePage(driver);
        EditVehiclePage editVehiclePage = new EditVehiclePage(driver);
        VehicleDetails vehicleDetails = new VehicleDetails(driver);
        LaneSelectionPage laneSelectionPage = new LaneSelectionPage(driver);
        OdometerPage odometerPage = new OdometerPage(driver);
        VisualInspectionPage visualInspectionPage = new VisualInspectionPage(driver);
        DbQueries dbQueries = new DbQueries();
        String[] vehicle = dbQueries.getVehicle();
        chassisNumber = vehicle[0];
        plateNo = vehicle[4];
    }
    @Test
    public void renewVehicleFulljourney(){

    }
    @AfterMethod
    public void tearDown(){}
}
