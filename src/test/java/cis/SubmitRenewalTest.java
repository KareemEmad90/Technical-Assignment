package cis;

import com.shaft.driver.DriverFactory;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.DatabaseActionsCustom;
import data.DbQueries;
import data.LoadProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.cis.*;
import pages.common.ChromeCertificatePage;
import enums.*;

import java.io.File;

public class SubmitRenewalTest {

    pages.cis.inspectionResultsPage inspectionResultsPage;
    DefectAnalysisPage defectAnalysisPage;
    VehicleDiscrepanciesPage vehicleDiscrepanciesPage;
    CisLoginPage cisLoginPage;
    CisHomePage cisHomePage;
    NewVehicleInspectionPage newVehicleInspectionPage;
    SearchVehiclePage searchVehiclePage;
    EditVehiclePage editVehiclePage;
    VehicleDetails vehicleDetails;
    LaneSelectionPage laneSelectionPage;
    OdometerPage odometerPage;
    VisualInspectionPage visualInspectionPage;
    DatabaseActionsCustom db = new DatabaseActionsCustom();
    String chassisNumber, plateNo;
    private WebDriver driver;

    @BeforeMethod()
    public void beforeMethod() {
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File(System.getProperty("user.dir")+"/extensions/connector.crx"));
        //DesiredCapabilities dc = new DesiredCapabilities();
        //dc.setCapability(ChromeOptions.CAPABILITY,options);
        driver = BrowserFactory.getBrowser(DriverFactory.DriverType.DESKTOP_CHROME,options);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("cisURL"));
        ChromeCertificatePage chromeCertificatePage = new ChromeCertificatePage(driver);
        chromeCertificatePage.skipUnsafePage();
        vehicleDiscrepanciesPage = new VehicleDiscrepanciesPage(driver);
        cisLoginPage = new CisLoginPage(driver);
        cisHomePage = new CisHomePage(driver);
        defectAnalysisPage = new DefectAnalysisPage(driver);
        inspectionResultsPage = new inspectionResultsPage(driver);
        newVehicleInspectionPage = new NewVehicleInspectionPage(driver);
        searchVehiclePage = new SearchVehiclePage(driver);
        editVehiclePage = new EditVehiclePage(driver);
        vehicleDetails = new VehicleDetails(driver);
        laneSelectionPage = new LaneSelectionPage(driver);
        odometerPage = new OdometerPage(driver);
        visualInspectionPage = new VisualInspectionPage(driver);
        DbQueries dbQueries = new DbQueries();
        String[] vehicle = dbQueries.getVehicle();
        chassisNumber = vehicle[0];
        plateNo = vehicle[4];
    }

    @Test
    public void SubmitRenewalTest(){
        cisLoginPage.login("shi_koshis", "Qc_123456");
        cisHomePage.clickOnNavigationBtn();
        cisHomePage.clickOnNewVehicleInspectionMenuItem();
        newVehicleInspectionPage.SelectTestType(TestType.RenewalTest);
        searchVehiclePage.selectChasisNumberOption();
        searchVehiclePage.typeChasisNumber(chassisNumber);
        searchVehiclePage.clickSearchButton();
        editVehiclePage.clickContinueBtn();
        vehicleDetails.clickDispatchBtn();
        vehicleDetails.proceedWithMobileBtn("0515584988");
        vehicleDetails.clickContinueBtn();
        cisHomePage.clickOnNavigationBtn();
        cisHomePage.clickOnSupervisorQueu();
        laneSelectionPage.proceedWithSelectedCar(plateNo);
        laneSelectionPage.StartInspectionRenwalTest("2","3","7");
        odometerPage.fillOdometer();

    }

    @AfterMethod()
    public void tearDown() {
        //BrowserActions.closeCurrentWindow(driver);
    }
}