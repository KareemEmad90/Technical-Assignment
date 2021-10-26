package vls.vehicleRenewal.mainJourney;

import api.*;
import com.shaft.api.RestActions;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.DbQueries;
import data.LoadProperties;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.vls.LoginPage;
import pages.vls.PaymentPage;
import pages.vls.RenewVehiclePage;
import utils.Utils;

import static com.shaft.driver.DriverFactory.DriverType.DESKTOP_CHROME;

public class checkRenewVehicleLicenseForLightVehicleWithFines {
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
    Utils utils;

    @Step("Renewal Vehicle Test case")
    @Test
    public void Persona31TestCase()  {
        LoginPage vlsLoginPage = new LoginPage(driver);
        renewVehiclePage = new RenewVehiclePage(driver);
        PaymentPage paymentPage = new PaymentPage(driver);
        utils = new Utils(driver);
        vlsLoginPage.login(eidNUMBER);
        paymentPage.assertLicenseRenewalFineAmount("200");
        paymentPage.assertStickerFeeAmount("10");
        paymentPage.assertKnowledgeAndInnovationFeeAmount("20");
        paymentPage.assertVehicleRenewalFine("50");
        paymentPage.assertVehicleNonRenewalFineAmount("100");
        paymentPage.checkTotalAmount("280");
        utils.confirmSoftAssertion();
        paymentPage.clickOnPayNow();
        paymentPage.payUsingRms();
        BrowserActions.refreshCurrentPage(driver);
        renewVehiclePage.downloadRegisterCard();
    }

    @BeforeTest()
    public void beforeMethod() throws InterruptedException {
        checkEligibilityAPI = new CheckEligibilityAPI();
        initiateRenewVehicleJourney = new InitiateRenewVehicleJourney();
        submitProcessInfo = new SubmitProcessInfo();
        submitInspectionResult = new SubmitInspectionResult();
        String[] vehicle = dbQueries.getVehicle("VCL_ID_3");
        eidNUMBER = vehicle[2];
        chassisNo = vehicle[0];
        rtaUnifiedNumber = vehicle[1];
        dbQueries.resetviloation(rtaUnifiedNumber, chassisNo);
        dbQueries.addelectronicinsurance(rtaUnifiedNumber, chassisNo);
        dbQueries.addpayablefine(rtaUnifiedNumber,chassisNo);
        Thread.sleep(10000);
        checkEligibilityAPI.checkEligibility(rtaUnifiedNumber, chassisNo);
        initiateJourney = initiateRenewVehicleJourney.initiateRenewVehicle(rtaUnifiedNumber, chassisNo);
        applicationRefNumber = RestActions.getResponseJSONValue(initiateJourney, "applicationRefNo");
        submitProcessInfo.submitProcessInfoResponse(applicationRefNumber, rtaUnifiedNumber, "IN_QUEUE");
        submitProcessInfo.submitProcessInfoResponse(applicationRefNumber, rtaUnifiedNumber, "UNDER_INSPECTION");
        submitInspectionResult.submitInspectionResult(applicationRefNumber,rtaUnifiedNumber, chassisNo, "PASSED");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSURL"));
    }


    @AfterMethod()
    public void tearDown() {
        BrowserActions.closeCurrentWindow(driver);
    }
}
