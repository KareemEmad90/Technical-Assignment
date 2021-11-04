package vls.declareVehicle.mainScenarios;

import api.DeclareVehicleAPI;
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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeclareNewVehicleJourney {

    String chassisNo ;
    DbQueries dbQueries = new DbQueries();
    Response declareRes;
    String declareApplicationReferenceNo;
    String declarationCertificateRefNo;
    String ExcelfileName, sheetname = "declareVehicleDetails";
    int TotalNumberOfCols = 7;
    ExcelReader ER = new ExcelReader();
    Boolean toRunValue = true;
    static Logger log = Logger.getLogger(DeclareNewVehicleJourney.class.getName());

    @DataProvider(name = "DeclareVehicleDetailsExcel")
    public Object[][] vehicleData(ITestContext context) throws IOException {

        ExcelfileName = LoadProperties.userData.getProperty("NewVehicleDetails");
        return ER.getExcelData(ExcelfileName, sheetname, TotalNumberOfCols);
    }

    @Step("Declare Vehicle")
    @Test(dataProvider = "DeclareVehicleDetailsExcel")
public void declearNewVechile(String personaNo,String vehicleWeight
        ,String vehicleClassCode ,String arabicName	,String englishName	,String year,String toRun) throws ParseException {
        DeclareVehicleAPI declare = new DeclareVehicleAPI();

        toRunValue=Boolean.parseBoolean(toRun);

        if (toRunValue) {
            if (personaNo.equals("Persona Nigative 02")){


                declareRes = declare.declareSerivceResponse(vehicleWeight, vehicleClassCode, arabicName, englishName, year);
                String BR_JRN_DNV_001_suggestion = RestActions.getResponseJSONValue(declareRes, "suggestions[0].en");

                Assert.assertEquals(BR_JRN_DNV_001_suggestion,"You need to fulfilled the service eligibility (Car Agency, Light Vehicle or motor cycle)");
            log.info("This ChassisNo "+declare.getChassisNo() +" is For Heavy Vehicle Which Is Not Allowed");

            }

            if (personaNo.equals("Persona  01")) {

            declareRes = declare.declareSerivceResponse(vehicleWeight, vehicleClassCode, arabicName, englishName, year);
            Assert.assertEquals(declareRes.getStatusCode(), 200);
            declareApplicationReferenceNo = RestActions.getResponseJSONValue(declareRes, "applicationReferenceNo");
            System.out.println(declareRes.getBody().toString());
            Assert.assertTrue(declareApplicationReferenceNo.contains("BNJ-"));
            declarationCertificateRefNo = RestActions.getResponseJSONValue(declareRes, "declarationCertificateRefNo");
            Assert.assertTrue(declarationCertificateRefNo.contains("VLS-"));
            chassisNo = declare.getChassisNo();
            String declarationStatus = dbQueries.getDeclaredVehicleStatus(declareApplicationReferenceNo, chassisNo, vehicleWeight, vehicleClassCode, arabicName, englishName, year);
            Assert.assertEquals(declarationStatus, "SUCCESS");

            }
        }
        else {
            log.info(personaNo +  " Has Ignored");
        }
}

    @BeforeTest()
    public void beforeMethod() throws InterruptedException {

    }
}
