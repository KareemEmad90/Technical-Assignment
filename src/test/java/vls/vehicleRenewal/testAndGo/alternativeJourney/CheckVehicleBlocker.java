package vls.vehicleRenewal.testAndGo.alternativeJourney;

import api.*;
import com.shaft.api.RestActions;
import data.DbQueries;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Epic("Master test cases Execution")
@Feature("VLS Renew External Scenario for Persona 1")
public class CheckVehicleBlocker {
    String Expected = "Success";
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
        //LoginPage vlsLoginPage = new LoginPage(driver);
        //vlsLoginPage.login(eidNUMBER);
        //RenewVehiclePage assertLicense = new RenewVehiclePage(driver);
        //assertLicense.verifyLicensingInsiurancePhase();
       // PaymentPage paymentPage = new PaymentPage(driver);
       // paymentPage.checkTotalAmount();
       // Assertions.assertEquals(Expected,paymentPage.getResult(),"380");
       // paymentPage.payment();
    }

    @BeforeTest()
    public void beforeMethod() throws InterruptedException, ParseException {
        checkEligibilityAPI = new CheckEligibilityAPI();
        String[] vehicle = dbQueries.getVehicle("VCL_ID_3");
        eidNUMBER = vehicle[2];
        chassisNo = vehicle[0];
        rtaUnifiedNumber = vehicle[1];
        dbQueries.addunpayablefines(rtaUnifiedNumber, chassisNo);
        dbQueries.addBlackPoint(rtaUnifiedNumber);
        dbQueries.addCircularNoteDRL(rtaUnifiedNumber);
        dbQueries.addCircularNoteTRF(rtaUnifiedNumber);
        dbQueries.addCircularNoteVLD(rtaUnifiedNumber, chassisNo);
        dbQueries.addLicenseConfiscation(rtaUnifiedNumber);
        dbQueries.addVehicleCONFISCATION(rtaUnifiedNumber, chassisNo);
        dbQueries.addVehicleNote(rtaUnifiedNumber, chassisNo);
        dbQueries.addBlackPoint(rtaUnifiedNumber);
        Response checkEligibilityResponse = checkEligibilityAPI.checkEligibility(rtaUnifiedNumber, chassisNo);
        String elgibilityResult = RestActions.getResponseJSONValue(checkEligibilityResponse, "eligible");
        System.out.println(elgibilityResult);

        if (elgibilityResult == "false")
        {
            System.out.println(checkEligibilityResponse.prettyPrint());
        }

    }


    @AfterMethod()
    public void tearDown() {
      //  BrowserActions.closeCurrentWindow(driver);
        //dbQueries.cancelReferenceApplication(applicationRefNumber);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

