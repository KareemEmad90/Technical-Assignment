package vls.vehicleRenewal.alternativeJourney;

import api.*;
import com.shaft.api.RestActions;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.DbQueries;
import data.LoadProperties;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.common.ChromeCertificatePage;
import pages.vls.LoginPage;
import pages.vls.RenewVehiclePage;

import static com.shaft.driver.DriverFactory.DriverType.DESKTOP_CHROME;

public class CheckUnderInspectionVehicleScenario {
    DbQueries dbQueries = new DbQueries();
    String eidNUMBER ;
    String chassisNo ;
    String rtaUnifiedNumber;
    private WebDriver driver;
    String applicationRefNumber;
    CheckEligibilityAPI checkEligibilityAPI;
    InitiateRenewVehicleJourney initiateRenewVehicleJourney;
    SubmitInspectionResult submitInspectionResult;
    SubmitProcessInfo submitProcessInfo;
    RenewVehiclePage renewVehiclePage;
    Response initiateJourney;

    @Step("Renewal Vehicle Test case")
    @Test
    public void Persona1TestCase()  {
        LoginPage vlsLoginPage = new LoginPage(driver);
        renewVehiclePage = new RenewVehiclePage(driver);
        vlsLoginPage.login(eidNUMBER);
        renewVehiclePage.verifyInspectingStatusIsDisplayed();
        // PaymentPage paymentPage = new PaymentPage(driver);
        // paymentPage.checkTotalAmount();
        // Assertions.assertEquals(Expected,paymentPage.getResult(),"380");
        // paymentPage.payment();
    }

    @BeforeTest()
    public void beforeMethod() throws ParseException {
        checkEligibilityAPI = new CheckEligibilityAPI();
        initiateRenewVehicleJourney = new InitiateRenewVehicleJourney();
        submitProcessInfo = new SubmitProcessInfo();
        submitInspectionResult = new SubmitInspectionResult();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        //String[] vehicle = dbQueries.getVehicle("VCL_ID_3");
        eidNUMBER = "196679154862";
        chassisNo = "JMYMRV63W5J710532";
        rtaUnifiedNumber = "11845143";
        dbQueries.updatelicenseexpirydate(chassisNo, "91");
        dbQueries.resetviloation(rtaUnifiedNumber, chassisNo);
        Response checkEligibilityResponse = checkEligibilityAPI.checkEligibility(rtaUnifiedNumber, chassisNo);
        String elgibilityResult = RestActions.getResponseJSONValue(checkEligibilityResponse, "eligible");
        System.out.println(elgibilityResult);
        initiateJourney = initiateRenewVehicleJourney.initiateRenewVehicle(rtaUnifiedNumber, chassisNo);
        applicationRefNumber = RestActions.getResponseJSONValue(initiateJourney, "applicationRefNo");
        submitProcessInfo.submitProcessInfoResponse(applicationRefNumber, rtaUnifiedNumber, "IN_QUEUE");
        submitProcessInfo.submitProcessInfoResponse(applicationRefNumber, rtaUnifiedNumber, "UNDER_INSPECTION");
        //submitInspectionResult.initiateRenewVehicle(applicationRefNumber,rtaUnifiedNumber, chassisNo);
        //dbQueries.addpayablefine(rtaUnifiedNumber, chassisNo);
        //dbQueries.addelectronicinsurance(rtaUnifiedNumber, chassisNo);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSURL"));
        ChromeCertificatePage chromeCertificatePage = new ChromeCertificatePage(driver);
        chromeCertificatePage.skipUnsafePage();
    }


    @AfterMethod()
    public void tearDown() {
        dbQueries.cancelReferenceApplication(applicationRefNumber);
        BrowserActions.closeCurrentWindow(driver);
    }
}
