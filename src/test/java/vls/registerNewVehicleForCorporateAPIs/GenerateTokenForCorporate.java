package vls.registerNewVehicleForCorporateAPIs;


//import api.registerNewVehicleForCorporateAPIs.DocumentMatrixAPI;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;



public class GenerateTokenForCorporate {


    @Step("Generate Token For Corporate")
    @Test
    public void getToken() throws ParseException {

        //CorporateLoginAPI GetResponse = new CorporateLoginAPI();
        //Response response= GetResponse.CorporateLogin();
        //String Token = RestActions.getResponseJSONValue(response , "access_token");
        //System.out.println("apiToken  >>> "+Token);

        //DocumentMatrixAPI GetResponse = new DocumentMatrixAPI();
        //Response response=GetResponse.DocumentMatrixResponse();

    }

}
