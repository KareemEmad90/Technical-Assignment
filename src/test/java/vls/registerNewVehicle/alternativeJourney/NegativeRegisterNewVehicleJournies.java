package vls.registerNewVehicle.alternativeJourney;

import api.DeclareVehicleAPI;
import api.registerNewVehicleAPIs.RegisterNewVehicleAPI;
import com.shaft.api.RestActions;
import data.DbQueries;
import data.ExcelReader;
import data.LoadProperties;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ChassisGeneration;
import java.io.IOException;

public class NegativeRegisterNewVehicleJournies {

    String ExcelfileName, sheetname = "AlternativeDataRegisterVehicle";
    int TotalNumberOfCols = 12;
    ExcelReader ER = new ExcelReader();

    Response registerNewVehicleRes;
    Response declareRes;
    DbQueries dbQueries = new DbQueries();
    String eidNUMBER;
    String chassisNo;
    Boolean toRunValue = true;
    RegisterNewVehicleAPI registerNewVehicleAPI = new RegisterNewVehicleAPI();
    static Logger log = Logger.getLogger(NegativeRegisterNewVehicleJournies.class.getName());

    @DataProvider(name = "NewVehicleDetailsExcel")
    public Object[][] vehicleData(ITestContext context) throws IOException {

        ExcelfileName = LoadProperties.userData.getProperty("NewVehicleDetails");
        return ER.getExcelData(ExcelfileName, sheetname, TotalNumberOfCols);
    }


    @Step("Declare Vehicle Test case")
    @Test(dataProvider = "NewVehicleDetailsExcel")
    public void declareVehicleAPITestCase(String persona_No, String plateCategory, String frontPlateSize,
                                          String backPlateSize, String logoType,String vehicleWeight,String vehicleClassCode, String arabicName, String englishName, String year,String scenarioType ,String toRun) throws ParseException {
        System.out.println(persona_No + "  "
                + plateCategory + "  " + frontPlateSize + "  " + backPlateSize +"  " + toRun);

        toRunValue = Boolean.parseBoolean(toRun);

        //  --------------------------------- Negative Test Cases --------------------------------
        if (toRunValue) {

            if (scenarioType.equals("expiredEid")){
                    chassisNo = ChassisGeneration.ChassisNo();
                    String[] customerDetails = dbQueries.getRTAUnitfiedIdAndEid("<");
                    eidNUMBER = customerDetails[1];
                    registerNewVehicleRes = registerNewVehicleAPI.registerVehicleResponse(chassisNo, eidNUMBER, plateCategory, frontPlateSize, backPlateSize,logoType);

                    Assert.assertEquals(registerNewVehicleRes.getStatusCode(), 500);
                    String BR_PRD_ELG_011_ErrorCode = RestActions.getResponseJSONValue(registerNewVehicleRes, "errorDetails[0].code");
                    String BR_PRD_ELG_011_ErrorCodeDesc = RestActions.getResponseJSONValue(registerNewVehicleRes, "errorDetails[0].description.en");
                    Assert.assertEquals(BR_PRD_ELG_011_ErrorCode, "BR_PRD_ELG_011");
                    Assert.assertEquals(BR_PRD_ELG_011_ErrorCodeDesc, "Apology, you are not eligible to proceed with the selected service because your emirates Id is expired");
                    log.info("This Emirates ID : " + eidNUMBER + " is expired");
            }

            if (scenarioType.equals("noProfileExist")){
                chassisNo = ChassisGeneration.ChassisNo();
                eidNUMBER = "7841974E9176177";
                registerNewVehicleRes = registerNewVehicleAPI.registerVehicleResponse(chassisNo, eidNUMBER, plateCategory, frontPlateSize, backPlateSize,logoType);
                Assert.assertEquals(registerNewVehicleRes.getStatusCode(), 500);
                String BR_JRN_BNV_006_ErrorCode = RestActions.getResponseJSONValue(registerNewVehicleRes, "errorDetails[0].code");
                String BR_JRN_BNV_006_ErrorCodeDesc = RestActions.getResponseJSONValue(registerNewVehicleRes, "errorDetails[0].description.en");
                Assert.assertEquals(BR_JRN_BNV_006_ErrorCode, "BR_JRN_BNV_006");
                Assert.assertEquals(BR_JRN_BNV_006_ErrorCodeDesc, "Apology, there is no profile for provided emirates ID");
                log.info("This Emirates ID : " + eidNUMBER + " Has No Profile");
            }

            if (scenarioType.equals("noInsurancePolicy")){
                String[] customerDetails = dbQueries.getRTAUnitfiedIdAndEid(">");
                chassisNo = ChassisGeneration.ChassisNo();
                eidNUMBER = customerDetails[1];
                DeclareVehicleAPI declare = new DeclareVehicleAPI();
                declareRes = declare.declareSerivceResponse(chassisNo, vehicleWeight, vehicleClassCode, arabicName, englishName, year);
                registerNewVehicleRes = registerNewVehicleAPI.registerVehicleResponse(chassisNo, eidNUMBER, plateCategory, frontPlateSize, backPlateSize,logoType);

                String BR_JRN_BNV_004_ErrorCode = RestActions.getResponseJSONValue(registerNewVehicleRes, "errorDetails[0].code");
                String BR_JRN_BNV_004_ErrorCodeDesc = RestActions.getResponseJSONValue(registerNewVehicleRes, "errorDetails[0].description.en");
                Assert.assertEquals(BR_JRN_BNV_004_ErrorCode, "BR_JRN_BNV_004");
                Assert.assertEquals(BR_JRN_BNV_004_ErrorCodeDesc, "Apology, you are not eligible to proceed with licensing the vehicle because you need to have active insurance policy for you vehicle");
                log.info("This Chassis No : " + chassisNo + " Has No have active insurance policy");

            }

            if (scenarioType.equals("vehicleNoDeclaredNoOwned")){
                String[] customerDetails = dbQueries.getRTAUnitfiedIdAndEid(">");
                chassisNo = ChassisGeneration.ChassisNo();
                eidNUMBER = customerDetails[1];
                registerNewVehicleRes = registerNewVehicleAPI.registerVehicleResponse(chassisNo, eidNUMBER, plateCategory, frontPlateSize, backPlateSize,logoType);
                String BR_JRN_BNV_001_ErrorCode = RestActions.getResponseJSONValue(registerNewVehicleRes, "errorDetails[0].code");
                String BR_JRN_BNV_001_ErrorCodeDesc = RestActions.getResponseJSONValue(registerNewVehicleRes, "errorDetails[0].description.en");
                Assert.assertEquals(BR_JRN_BNV_001_ErrorCode, "BR_JRN_BNV_001");
                Assert.assertEquals(BR_JRN_BNV_001_ErrorCodeDesc, "Apology, you are not eligible to proceed with licensing the vehicle because Vehicle should be declared or owned before being registered");
                log.info("This Chassis No : " + chassisNo + " should be declared or owned before being registered");

            }

        }else {
            log.info(persona_No + " Has Ignored");
        }

    }
}
