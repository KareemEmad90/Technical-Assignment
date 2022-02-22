package vls.vehicleRenewal.mainJourney;

import api.*;
import com.shaft.api.RestActions;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.DbQueries;
import data.ExcelReader;
import data.LoadProperties;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.common.ChromeCertificatePage;
import pages.vls.LoginPage;
import pages.vls.PaymentPage;
import pages.vls.RenewVehiclePage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.shaft.driver.DriverFactory.DriverType.DESKTOP_CHROME;


public class RenewVehicleLicenseFailed {
    String chassisNo;
    String eidNUMBER;
    String rtaUnifiedNumber;
    DbQueries dbQueries = new DbQueries();
    Response declareRes;
    String ExcelfileName, sheetname = "vehicleRenewalTestData";
    int TotalNumberOfCols = 7;
    ExcelReader ER = new ExcelReader();
    Boolean toRunValue = true;
    String applicationRefNumber;
    String eligibilityStatus;
    CheckEligibilityAPI checkEligibilityAPI;
    InitiateRenewVehicleJourney initiateRenewVehicleJourneyAPI;
    SubmitInspectionResult submitInspectionResultAPI;
    SubmitProcessInfo submitProcessInfoAPI;
    RenewVehiclePage renewVehiclePage;
    Response initiateJourney;
    Response checkEligibilityResponse;
    Response submitProcessInfoResponse;
    Response submitInspectionResultResponse;
    String plate_No;
    String plate_Code;
    private WebDriver driver;
    static Logger log = Logger.getLogger(RenewVehicleLicenseFailed.class.getName());

    @DataProvider(name = "RenewVehicleDetailsExcel")
    public Object[][] vehicleData(ITestContext context) throws IOException {
        ExcelfileName = LoadProperties.userData.getProperty("NewVehicleDetails");
        return ER.getExcelData(ExcelfileName, sheetname, TotalNumberOfCols);
    }

    @Test(dataProvider = "RenewVehicleDetailsExcel")
    public void renewVehicle(String TextCaseNo, String VehicleWeightFrom,
                             String VehicleWeightTo, String vehicleClassCode, String mortgageStatus
            ,  String ExpiredDaysCount, String toRun) throws SQLException, ParseException, ClassNotFoundException, InterruptedException {
        toRunValue = Boolean.parseBoolean(toRun);
        if (toRunValue) {
            checkEligibilityAPI = new CheckEligibilityAPI();
            initiateRenewVehicleJourneyAPI = new InitiateRenewVehicleJourney();
            submitInspectionResultAPI = new SubmitInspectionResult();
            submitProcessInfoAPI = new SubmitProcessInfo();

            ArrayList<String> vehicle = dbQueries.getVehicleBasedOnExcel(VehicleWeightFrom, VehicleWeightTo, vehicleClassCode, mortgageStatus);
            eidNUMBER = vehicle.get(2);
            chassisNo = vehicle.get(0);
            rtaUnifiedNumber = vehicle.get(1);
            plate_No = vehicle.get(4);
            plate_Code = vehicle.get(5);

            dbQueries.updatelicenseexpirydate(chassisNo, ExpiredDaysCount);
            dbQueries.resetviloation(rtaUnifiedNumber, chassisNo);


// ------------------------------------------------Add Electronic Insurance-------------------------------------------------------

            AddElectronicInsurance addElectronicInsurance = new AddElectronicInsurance();
            addElectronicInsurance.elecInsuranceAPI(rtaUnifiedNumber, chassisNo);

// ------------------------------------------------Check Eligibility-------------------------------------------------------

            checkEligibilityResponse = checkEligibilityAPI.checkEligibility(rtaUnifiedNumber, chassisNo);
            eligibilityStatus = RestActions.getResponseJSONValue(checkEligibilityResponse, "eligible");
            Assert.assertEquals(checkEligibilityResponse.getStatusCode(), 200);
            Assert.assertEquals(eligibilityStatus, "true");

// ------------------------------------------------Initiate Renew Vehicle Journey-------------------------------------------------------

            initiateJourney = initiateRenewVehicleJourneyAPI.initiateRenewVehicle(rtaUnifiedNumber, chassisNo);
            applicationRefNumber = RestActions.getResponseJSONValue(initiateJourney, "applicationRefNo");
            Assert.assertEquals(initiateJourney.getStatusCode(), 200);
            System.out.println("applicationRefNumber " + applicationRefNumber);
            log.info("application Ref Number For Vehicle Renewal Journey " + applicationRefNumber);

// ------------------------------------------------Submit Process Info-------------------------------------------------------

            submitProcessInfoAPI.submitProcessInfoResponse(applicationRefNumber, rtaUnifiedNumber, "IN_QUEUE");
            submitProcessInfoResponse = submitProcessInfoAPI.submitProcessInfoResponse(applicationRefNumber, rtaUnifiedNumber, "UNDER_INSPECTION");
            Assert.assertEquals(submitProcessInfoResponse.getStatusCode(), 200);

// ------------------------------------------------Submit Inspection Result-------------------------------------------------------

            submitInspectionResultResponse = submitInspectionResultAPI.submitInspectionResult(applicationRefNumber, rtaUnifiedNumber, chassisNo, "FAILED");
            Assert.assertEquals(submitInspectionResultResponse.getStatusCode(), 200);

        } else {

            throw new SkipException("Test Case No " + TextCaseNo + " For Vehicle Renewal Service Has Ignored");
        }

// ------------------------------------------------ VLS Login And Do Payment -------------------------------------------------------

        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSURL"));

        LoginPage vlsLoginPage = new LoginPage(driver);

        vlsLoginPage.login(eidNUMBER);

        PaymentPage paymentPage= new PaymentPage(driver);
        paymentPage.checkTotalAmount("170");
        paymentPage.clickOnPayNowForRenewal();

        ChromeCertificatePage ChromeCertificatePage = new ChromeCertificatePage(driver);
        ChromeCertificatePage.skipUnsafePage();
        paymentPage.payUsingRms();

        renewVehiclePage= new RenewVehiclePage(driver);

        renewVehiclePage.verifyConfirmationFailedRenewalPage();

        String getRenewalTransactionNo=dbQueries.getVehicleRenewalTransactionNo(applicationRefNumber);

        log.info("------------------------------------------------ Verify Transaction In Traffic -------------------------------------------------------");
        dbQueries.verifyTransaction(getRenewalTransactionNo,getClass().getSimpleName());


        log.info("------------------------------------------------ Verify Transaction In VLS -------------------------------------------------------");
        dbQueries.checkVehicleRenewalStatus(applicationRefNumber);

    }

}
