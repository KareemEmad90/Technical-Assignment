package vls.vehicleRenewal.mainJourney;

import api.CheckEligibilityAPI;
import api.InitiateRenewVehicleJourney;
import api.SubmitInspectionResult;
import api.SubmitProcessInfo;
import com.shaft.api.RestActions;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.DbQueries;
import data.ExcelReader;
import data.LoadProperties;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.vls.RenewVehiclePage;
import vls.declareVehicle.mainScenarios.DeclareNewVehicleJourney;

import java.io.IOException;

import static com.shaft.driver.DriverFactory.DriverType.DESKTOP_CHROME;

public class VRJScenarios {
    String chassisNo;
    String eidNUMBER;
    String rtaUnifiedNumber;
    DbQueries dbQueries = new DbQueries();
    Response declareRes;
    String ExcelfileName, sheetname = "vehicleRenewalTestData";
    int TotalNumberOfCols = 16;
    ExcelReader ER = new ExcelReader();
    Boolean toRunValue = true;
    String applicationRefNumber;
    CheckEligibilityAPI checkEligibilityAPI;
    InitiateRenewVehicleJourney initiateRenewVehicleJourney;
    SubmitInspectionResult submitInspectionResult;
    SubmitProcessInfo submitProcessInfo;
    RenewVehiclePage renewVehiclePage;
    Response initiateJourney;
    private WebDriver driver;
    static Logger log = Logger.getLogger(DeclareNewVehicleJourney.class.getName());

    @DataProvider(name = "RenewVehicleDetailsExcel")
    public Object[][] vehicleData(ITestContext context) throws IOException {

        ExcelfileName = LoadProperties.userData.getProperty("NewVehicleDetails");
        return ER.getExcelData(ExcelfileName, sheetname, TotalNumberOfCols);
    }

    @Step("Declare Vehicle")
    @Test(dataProvider = "RenewVehicleDetailsExcel")
    public void declearNewVechile(String Persona_No, String ProfileType, String ProfileClassification, String VehicleWeightFrom,
                                  String VehicleWeightTo,String vehicleClass, String vehicleClassCode, String PlateCategory, String VehicleLicenseStatus, String mortgageStatus
            , String InspectedStatus, String ExpiredDaysCount, String InsurancePeriod, String HasUAEAndGCCFines,
                                  String HasUAEFines, String HasUAEandSalikFines, String toRun) {

        toRunValue = Boolean.parseBoolean(toRun);


        System.out.println(Persona_No + "  " + ProfileType + "  " + ProfileClassification + "  " + VehicleWeightFrom+ "  " + VehicleWeightTo
                + "  " + vehicleClass + "  " + vehicleClassCode + "  " + PlateCategory + "  " + VehicleLicenseStatus + "  " + mortgageStatus
                + "  " + InspectedStatus + "  " + ExpiredDaysCount + "  " + InsurancePeriod + "  " + HasUAEandSalikFines
                + "  " + HasUAEFines + "  " + HasUAEandSalikFines + "  " + HasUAEAndGCCFines + "  " + toRun);

        if (ExpiredDaysCount.equals("Not Expired")) {

            ExpiredDaysCount = String.valueOf(0);
            System.out.println("XXXXXX " + ExpiredDaysCount);
        }










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
        Thread.sleep(10000);
        checkEligibilityAPI.checkEligibility(rtaUnifiedNumber, chassisNo);
        initiateJourney = initiateRenewVehicleJourney.initiateRenewVehicle(rtaUnifiedNumber, chassisNo);
        applicationRefNumber = RestActions.getResponseJSONValue(initiateJourney, "applicationRefNo");
        System.out.println("applicationRefNumber " + applicationRefNumber);
        submitProcessInfo.submitProcessInfoResponse(applicationRefNumber, rtaUnifiedNumber, "IN_QUEUE");
        submitProcessInfo.submitProcessInfoResponse(applicationRefNumber, rtaUnifiedNumber, "UNDER_INSPECTION");
        submitInspectionResult.submitInspectionResult(applicationRefNumber, rtaUnifiedNumber, chassisNo, "PASSED");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSURL"));


    }
}
