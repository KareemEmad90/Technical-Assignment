package vls.vehicleRenewal.alternativeJourney;

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
public class CheckMissingInsuranceScenario {
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
    Response initiateJourney;

    @Step("Renewal Vehicle Test case")
    @Test
    public void Persona1TestCase()  {
        LoginPage vlsLoginPage = new LoginPage(driver);
        vlsLoginPage.login(eidNUMBER);
        RenewVehiclePage assertLicense = new RenewVehiclePage(driver);
        assertLicense.verifyLicensingInsiurancePhase();
    }

    @BeforeTest()
    public void beforeMethod() {
        checkEligibilityAPI = new CheckEligibilityAPI();
        initiateRenewVehicleJourney = new InitiateRenewVehicleJourney();
        submitProcessInfo = new SubmitProcessInfo();
        submitInspectionResult = new SubmitInspectionResult();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        eidNUMBER = "196679154862";
        chassisNo = "JMYMRV63W5J710532";
        rtaUnifiedNumber = "11845143";
        dbQueries.resetviloation(rtaUnifiedNumber, chassisNo);
        Response checkEligibilityResponse = checkEligibilityAPI.checkEligibility(rtaUnifiedNumber, chassisNo);
        String elgibilityResult = RestActions.getResponseJSONValue(checkEligibilityResponse, "eligible");
        System.out.println(elgibilityResult);
        dbQueries.updatelicenseexpirydate(chassisNo, "-91");
        initiateJourney = initiateRenewVehicleJourney.initiateRenewVehicle(rtaUnifiedNumber, chassisNo);
        applicationRefNumber = RestActions.getResponseJSONValue(initiateJourney, "applicationRefNo");
        submitProcessInfo.submitProcessInfoResponse(applicationRefNumber, rtaUnifiedNumber, "IN_QUEUE");
        dbQueries.updateInspectionServiceRequest(chassisNo);
        dbQueries.updateAppPhase(applicationRefNumber);
        dbQueries.addpayablefine(rtaUnifiedNumber, chassisNo);
        dbQueries.addelectronicinsurance(rtaUnifiedNumber, chassisNo);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSURL"));
    }


    @AfterMethod()
    public void tearDown() {
        dbQueries.cancelReferenceApplication(applicationRefNumber);
        BrowserActions.closeCurrentWindow(driver);
    }
}

