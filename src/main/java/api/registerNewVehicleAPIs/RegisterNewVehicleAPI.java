package api.registerNewVehicleAPIs;

import com.shaft.api.RequestBuilder;
import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import payLoads.AddInsourancePayLoad;
import payLoads.RegisterNewVehiclePayLoad;

import static io.restassured.RestAssured.given;

public class RegisterNewVehicleAPI {

    private String declareEndpoint = "/register";

    public Response registerVehicleResponse(String chassisNo,String EID, String plateCategory , String frontPlateSize,String backPlateSize, String logoType) throws ParseException {

        Response insuranceResponse;
        JSONParser parser = new JSONParser();


        String stringObject = RegisterNewVehiclePayLoad.registerPayLoad(chassisNo, EID,plateCategory,frontPlateSize,backPlateSize,logoType);
        /*
                JSONObject jsonObject = (JSONObject) parser.parse(RegisterNewVehiclePayLoad.registerPayLoad(chassisNo, EID,plateCategory,frontPlateSize,backPlateSize,logoType));
        insuranceResponse = RestActions.buildNewRequest("https://vlsgw.external.apps.qa.licensing.rta.ae/external/buynewvehicle/api/application/vehicle" , declareEndpoint, RestActions.RequestType.POST)
                .setRequestBody(jsonObject)
                .setContentType(ContentType.JSON)
                .setAuthentication("FUTAIM_NEW_VEHILCE_DEALER","mrxT1Xj3RFDo9LOfJkRP9A==", RequestBuilder.AuthenticationType.BASIC)
                .performRequest();
        return insuranceResponse;


 */
        Response  responses  =given().header("Content-type", "application/json")
                .and().auth().preemptive().basic("FUTAIM_NEW_VEHILCE_DEALER", "mrxT1Xj3RFDo9LOfJkRP9A==")
                .body(stringObject)
                .when()
                .post("https://vlsgw.external.apps.qa.licensing.rta.ae/external/buynewvehicle/api/application/vehicle/register")
                .then()
                .extract().response();

        return responses;

    }
}
