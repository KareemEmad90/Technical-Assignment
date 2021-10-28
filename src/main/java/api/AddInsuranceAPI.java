package api;

import com.shaft.api.RequestBuilder;
import com.shaft.api.RestActions;
import io.cucumber.java.hu.Ha;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import payLoads.AddInsourancePayLoad;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class AddInsuranceAPI {

    private String declareEndpoint = "/submit";

    public Response AddInsuranceResponse(String rtaUnifiedNo, String chassisNo , String eId) throws ParseException {
      //  String Authorization=null;
        HashMap<String , String> header= new HashMap<>();

        Response insuranceResponse;
        Response insuranceResponse2;


        System.out.println("rtaUnifiedNo "+ rtaUnifiedNo);
        System.out.println("chassisNo "+ chassisNo);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(AddInsourancePayLoad.insourancePayLoad(rtaUnifiedNo, chassisNo));
        System.out.println("BBBBBBB "+jsonObject.toJSONString());
        System.out.println("XXXXXXX "+jsonObject);

        GenerateTokenApi generateTokenApi = new GenerateTokenApi();
        String token = generateTokenApi.generateTokenResponse(eId);
        System.out.println("Bearer "+ token);
        /*
        insuranceResponse = RestActions.buildNewRequest("https://vlsgw.portal.apps.qa.licensing.rta.ae/services/insurance/api/vehicle/insurances" , declareEndpoint, RestActions.RequestType.POST)
                .setRequestBody(jsonObject)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization","Bearer "+token)
                .performRequest();
*/

        header.put("Authorization","Bearer "+token);
        header.put("Content-Type","application/json");
        insuranceResponse2=given().config(RestAssured.config()
                .encoderConfig(EncoderConfig.encoderConfig()
                        .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .relaxedHTTPSValidation().headers(header)
                .body(jsonObject)
                .when()
                .post("https://vlsgw.portal.apps.qa.licensing.rta.ae/services/insurance/api/vehicle/insurances/submit");



        return insuranceResponse2;
    }
}
