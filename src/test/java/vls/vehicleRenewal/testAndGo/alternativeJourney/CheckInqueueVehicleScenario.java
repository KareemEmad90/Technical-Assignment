package vls.vehicleRenewal.testAndGo.alternativeJourney;

import api.*;
import com.shaft.api.RestActions;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.DbQueries;
import data.LoadProperties;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.vls.LoginPage;
import pages.vls.RenewVehiclePage;

import static com.shaft.driver.DriverFactory.DriverType.DESKTOP_CHROME;

@Epic("Master test cases Execution")
@Feature("VLS Renew External Scenario for Persona 1")
public class CheckInqueueVehicleScenario {
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
        renewVehiclePage.verifyTokenNumber("Token 123");
        renewVehiclePage.verifyLaneNumber("Lane 4");
        renewVehiclePage.verifyVehicleLicenseStatus("2 MINUTES LEFT");
       // PaymentPage paymentPage = new PaymentPage(driver);
       // paymentPage.checkTotalAmount();
       // Assertions.assertEquals(Expected,paymentPage.getResult(),"380");
       // paymentPage.payment();
    }

    @BeforeTest()
    public void beforeMethod() throws InterruptedException, ParseException {
        checkEligibilityAPI = new CheckEligibilityAPI();
        initiateRenewVehicleJourney = new InitiateRenewVehicleJourney();
        submitProcessInfo = new SubmitProcessInfo();
        submitInspectionResult = new SubmitInspectionResult();
        //String[] vehicle = dbQueries.getVehicle("VCL_ID_3");
        eidNUMBER = "196679154862";
        chassisNo = "JMYMRV63W5J710532";
        rtaUnifiedNumber = "11845143";
        //dbQueries.updatelicenseexpirydate(chassisNo, "90");
        dbQueries.resetviloation(rtaUnifiedNumber, chassisNo);
        Response checkEligibilityResponse = checkEligibilityAPI.checkEligibility(rtaUnifiedNumber, chassisNo);
        String elgibilityResult = RestActions.getResponseJSONValue(checkEligibilityResponse, "eligible");
        System.out.println(elgibilityResult);
        initiateJourney = initiateRenewVehicleJourney.initiateRenewVehicle(rtaUnifiedNumber, chassisNo);
        applicationRefNumber = RestActions.getResponseJSONValue(initiateJourney, "applicationRefNo");
        submitProcessInfo.submitProcessInfoResponse(applicationRefNumber, rtaUnifiedNumber, "IN_QUEUE");
        //submitInspectionResult.initiateRenewVehicle(applicationRefNumber,rtaUnifiedNumber, chassisNo);
        //Thread.sleep(5000);
        //dbQueries.updateInspectionServiceRequest(chassisNo);
        //dbQueries.updateAppPhase(applicationRefNumber);
        //dbQueries.addpayablefine(rtaUnifiedNumber, chassisNo);
        //dbQueries.addelectronicinsurance(rtaUnifiedNumber, chassisNo);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSURL"));
    }


    @AfterMethod()
    public void tearDown() {
        dbQueries.cancelReferenceApplication(applicationRefNumber);
        BrowserActions.closeCurrentWindow(driver);

    }
}

