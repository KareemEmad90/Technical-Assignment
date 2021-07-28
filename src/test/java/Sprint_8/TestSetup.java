package Sprint_8;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.DatabaseActionsCustom;
import data.DbQueries;
import data.LoadProperties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;
import pages.cis.*;
import pages.common.ChromeCertificatePage;

public class TestSetup {
    SoftAssert softAssert;
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
    DbQueries dbQueries;
    DatabaseActionsCustom db = new DatabaseActionsCustom();
    String chassisNumber;
    String userName = "shi_koshis";
    String passWrd = "Qc_123456";
    String newInspection = "New Vehicle Inspection";
    String invalidChasisNumber = "xxxxxxxxxxxxxxxxxxxx";
    String [] vehicle;
    String [] certificate;
    String plateNo;


    String docNum;
    String NotExisting_docNum;
    String Date;
    WebDriver driver;

    @BeforeSuite
    public void setup(){
        ChromeCertificatePage chromeCertificatePage = new ChromeCertificatePage(driver);
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
        softAssert = new SoftAssert();
        dbQueries = new DbQueries();
    }

    @BeforeMethod()
    public void beforeMethod() {
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("cisURL"));
        ChromeCertificatePage chromeCertificatePage = new ChromeCertificatePage(driver);
        chromeCertificatePage.skipUnsafePage();
    }

    private String[] getVehicle() {
        vehicle = dbQueries.getVehicle();
        return vehicle;
    }

    private String[] getCertificate(){
        certificate = dbQueries.getCertificate();
        return certificate;
    }

    public String getChassisNumber(){
        chassisNumber = getVehicle()[0];
        return chassisNumber;
    }

    public String getPlateNo() {
        plateNo = getVehicle()[4];
        return plateNo;
    }

    public String getDocNum() {
        return docNum;
    }


}
