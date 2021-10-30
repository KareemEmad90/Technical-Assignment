package api;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
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

        HashMap<String , String> header= new HashMap<>();

        Response insuranceResponse;
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(AddInsourancePayLoad.insourancePayLoad(rtaUnifiedNo, chassisNo));

        GenerateTokenApi generateTokenApi = new GenerateTokenApi();
        String token = generateTokenApi.generateTokenResponse(eId);

        header.put("Authorization","Bearer "+token);
        header.put("Content-Type","application/json");
        insuranceResponse=given().config(RestAssured.config()
                .encoderConfig(EncoderConfig.encoderConfig()
                        .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .relaxedHTTPSValidation().headers(header)
                .body(jsonObject)
                .when()
                .post("https://vlsgw.portal.apps.qa.licensing.rta.ae/services/insurance/api/vehicle/insurances/submit");

        return insuranceResponse;
    }
}
