package cis;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.DatabaseActionsCustom;
import data.LoadProperties;
import io.cucumber.java.tr.Ve;
import org.openqa.selenium.WebDriver;
import org.python.modules.thread.thread;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.cis.*;
import pages.common.ChromeCertificatePage;

import java.awt.*;

public class SubmitExportInspection {
    private WebDriver driver;
    CisLoginPage cisLoginPage;
    CisHomePage cisHomePage;
    NewVehicleInspectionPage newVehicleInspectionPage;
    SearchVehiclePage searchVehiclePage;
    EditVehiclePage editVehiclePage;
    VehicleDetails vehicleDetails;
    LaneSelectionPage laneSelectionPage;

    DatabaseActionsCustom db = new DatabaseActionsCustom();

    String chasisNumber;



    @Test
    public void SubmitNewInspection() throws InterruptedException {
        cisLoginPage.login("shi_koshis", "Qc_123456");
        cisHomePage.clickOnNavigationBtn();
        cisHomePage.clickOnNewVehicleInspectionMenuItem();
        newVehicleInspectionPage.unselectRenewalTestService();
        newVehicleInspectionPage.selectExportTest();
        newVehicleInspectionPage.clickContinue();
        searchVehiclePage.selectChasisNumberOption();
        searchVehiclePage.typeChasisNumber("MR1YU59G260004612");
        Thread.sleep(5000);
        searchVehiclePage.clickSearchButton();
        editVehiclePage.clickContinueBtn();
        vehicleDetails.clickDispatchBtn();
        vehicleDetails.proceedWithMobileBtn("0515584988");
        vehicleDetails.clickContinueBtn();
        cisHomePage.clickOnNavigationBtn();
        cisHomePage.clickOnSupervisorQueu();
        laneSelectionPage.proceedWithSelectedCar(vehicleDetails.plateNumber());
    }

    @BeforeMethod()
    public void beforeMethod() {
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("cisURL"));
        ChromeCertificatePage chromeCertificatePage = new ChromeCertificatePage(driver);
        chromeCertificatePage.skipUnsafePage();
        cisLoginPage = new CisLoginPage(driver);
        cisHomePage = new CisHomePage(driver);
        newVehicleInspectionPage = new NewVehicleInspectionPage(driver);
        searchVehiclePage = new SearchVehiclePage(driver);
        editVehiclePage = new EditVehiclePage(driver);
        vehicleDetails = new VehicleDetails(driver);
        laneSelectionPage = new LaneSelectionPage(driver);
        //chasisNumber = DatabaseActionsCustom.getChasisDetails()[0];
    }


    @AfterMethod()
    public void tearDown() {
       //BrowserActions.closeCurrentWindow(driver);
    }
}

