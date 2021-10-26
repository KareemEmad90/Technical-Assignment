package vls.registerNewVehicle.mainJourney;

import api.AddInsuranceAPI;
import api.DeclareVehicleAPI;
import api.registerNewVehicleAPIs.RegisterNewVehicleAPI;
import com.shaft.api.RestActions;
import data.DbQueries;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegisterNewVehicleJourney {
    Response declareRes;
    Response addInsuranceRes;
    Response registerNewVehicle;
    String applicationReferenceNo;
    String declarationCertificateRefNo;
    DbQueries dbQueries = new DbQueries();
    String eidNUMBER ;
    String rtaUnifiedNumber;
    String chassisNo ;
    static Logger log = Logger.getLogger(RegisterNewVehicleJourney.class.getName());
    @Step("Declare Vehicle Test case")
    @Test
    public void declareVehicleAPITestCase() throws ParseException {


        DeclareVehicleAPI declare= new DeclareVehicleAPI();
        AddInsuranceAPI addInsourance = new AddInsuranceAPI();
        RegisterNewVehicleAPI registerNewVehicleAPI= new RegisterNewVehicleAPI();
        declareRes = declare.declareSerivceResponse();
        Assert.assertEquals(declareRes.getStatusCode() , 200);
        applicationReferenceNo= RestActions.getResponseJSONValue(declareRes,"applicationReferenceNo");
        declarationCertificateRefNo= RestActions.getResponseJSONValue(declareRes,"declarationCertificateRefNo");
        chassisNo =declare.getChassisNo();
        System.out.println("applicationReferenceNo "+applicationReferenceNo);
        System.out.println("declarationCertificateRefNo "+declarationCertificateRefNo);
        addInsuranceRes = addInsourance.AddInsuranceResponse(rtaUnifiedNumber,chassisNo);
        registerNewVehicle=registerNewVehicleAPI.registerVehicleResponse(chassisNo,eidNUMBER);
    }

    @BeforeTest()
    public void beforeMethod() throws InterruptedException {

        String[] vehicle = dbQueries.getVehicleNotMortgaged("false");
        eidNUMBER = vehicle[2];
        rtaUnifiedNumber = vehicle[1];
        dbQueries.resetviloation(rtaUnifiedNumber, chassisNo);
    }

}
