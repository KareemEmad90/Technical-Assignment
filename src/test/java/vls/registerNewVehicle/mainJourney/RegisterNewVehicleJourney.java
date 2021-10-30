package vls.registerNewVehicle.mainJourney;

import api.AddInsuranceAPI;
import api.DeclareVehicleAPI;
import api.registerNewVehicleAPIs.ApplicationReceiptAPI;
import api.registerNewVehicleAPIs.PayApplicationAPI;
import api.registerNewVehicleAPIs.RegisterNewVehicleAPI;
import com.shaft.api.RestActions;
import data.DbQueries;
import data.ExcelReader;
import data.LoadProperties;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegisterNewVehicleJourney {
    String ExcelfileName, sheetname = "NewVehicleDetails";
    int TotalNumberOfCols = 11;

    ExcelReader ER = new ExcelReader();
    Response declareRes;
    Response addInsuranceRes;
    Response registerNewVehicleRes;
    Response payApplicationRes;
    Response applicationReceiptRes;
    String declareApplicationReferenceNo;
    String declarationCertificateRefNo;
    String registerApplicationReferenceNo;
    DbQueries dbQueries = new DbQueries();
    String eidNUMBER ;
    String rtaUnifiedNumber;
    String chassisNo ;
    static Logger log = Logger.getLogger(RegisterNewVehicleJourney.class.getName());


    @DataProvider(name = "NewVehicleDetailsExcel")
    public Object[][] vehicleData(ITestContext context) throws IOException {

        ExcelfileName = LoadProperties.userData.getProperty("NewVehicleDetails");
        return ER.getExcelData(ExcelfileName, sheetname, TotalNumberOfCols);
    }

    @Step("Declare Vehicle Test case")
    @Test(dataProvider = "NewVehicleDetailsExcel")
    public void declareVehicleAPITestCase(String persona_No	,String vehicleWeight, String mortgageStatus,String vehicleClassCode,
                                          String arabicName, String	englishName, String	year,
                                          String plateCategory , String frontPlateSize, String backPlateSize, String logoType) throws ParseException {
        System.out.println(vehicleWeight + "  "+vehicleClassCode+" " + mortgageStatus+"  "+arabicName+"  "+englishName+"  "+year +" " +plateCategory +" "+frontPlateSize+" "+backPlateSize + " "+logoType);
        AddInsuranceAPI addInsourance = new AddInsuranceAPI();

        DeclareVehicleAPI declare= new DeclareVehicleAPI();

        RegisterNewVehicleAPI registerNewVehicleAPI= new RegisterNewVehicleAPI();
        PayApplicationAPI  payApplicationAPI= new PayApplicationAPI();
        ApplicationReceiptAPI applicationReceiptAPI= new ApplicationReceiptAPI();

        //  --------------------------------- declare Vehicle --------------------------------
        declareRes = declare.declareSerivceResponse(vehicleWeight , vehicleClassCode,arabicName,englishName,year);
        Assert.assertEquals(declareRes.getStatusCode() , 200);
        declareApplicationReferenceNo= RestActions.getResponseJSONValue(declareRes,"applicationReferenceNo");
        System.out.println(declareRes.getBody().toString());
        Assert.assertTrue(declareApplicationReferenceNo.contains("BNJ-"));
        declarationCertificateRefNo= RestActions.getResponseJSONValue(declareRes,"declarationCertificateRefNo");
        Assert.assertTrue(declarationCertificateRefNo.contains("VLS-"));
        chassisNo =declare.getChassisNo();
        String declarationStatus =dbQueries.getDeclaredVehicleStatus(declareApplicationReferenceNo,chassisNo ,vehicleWeight,vehicleClassCode,arabicName,englishName,year);
       // Thread.sleep(5000);
        Assert.assertEquals(declarationStatus,"SUCCESS");

        //  --------------------------------- Add Insurance To The Vehicle --------------------------------
        addInsuranceRes = addInsourance.AddInsuranceResponse(rtaUnifiedNumber,chassisNo,eidNUMBER);
        String insuranceStatus =dbQueries.getInsuranceVehicleStatus(chassisNo,"AVAILABLE",rtaUnifiedNumber);
        Assert.assertEquals(insuranceStatus,"SUCCESS");


        //  --------------------------------- Register The Vehicle --------------------------------

        registerNewVehicleRes=registerNewVehicleAPI.registerVehicleResponse(chassisNo,eidNUMBER,plateCategory,frontPlateSize,backPlateSize);
        registerApplicationReferenceNo=RestActions.getResponseJSONValue(registerNewVehicleRes,"applicationReferenceNo");

        String registerStatus =  dbQueries.getRegisterVehicleStatus(registerApplicationReferenceNo,chassisNo,rtaUnifiedNumber,vehicleWeight,mortgageStatus,vehicleClassCode,arabicName,englishName,year,plateCategory,logoType,frontPlateSize,backPlateSize);
        Assert.assertEquals(registerStatus,"SUCCESS");
/*
        //  --------------------------------- Pay Fees For Registered The Vehicle --------------------------------
        payApplicationRes=payApplicationAPI.payApplicationResponse(registerApplicationReferenceNo);
        Assert.assertTrue(declareApplicationReferenceNo.contains("BNJ-"));
        Assert.assertEquals(payApplicationRes.getStatusCode() , 200);
        String insuranceAfterRegisterStatus =dbQueries.getInsuranceVehicleStatus(chassisNo,"CONSUMED",eidNUMBER);
        Assert.assertEquals(insuranceAfterRegisterStatus,"SUCCESS");

        //  --------------------------------- The Receipt For The Pay Fees For Registered The Vehicle --------------------------------
        applicationReceiptRes=applicationReceiptAPI.getReceiptResponse(registerApplicationReferenceNo);
        Assert.assertEquals(applicationReceiptRes.getStatusCode() , 200);


 */
    }

    @BeforeTest()
    public void beforeMethod() throws InterruptedException {

        String[] customerDetails = dbQueries.getRTAUnitfiedIdAndEid();
        eidNUMBER = customerDetails[1];
        rtaUnifiedNumber = customerDetails[0];
        dbQueries.resetviloation(rtaUnifiedNumber, "");
    }

}
