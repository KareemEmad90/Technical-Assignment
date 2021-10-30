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

public class RegisterNewVehicleAPI {

    private String declareEndpoint = "/register";

    public Response registerVehicleResponse(String chassisNo,String EID, String plateCategory , String frontPlateSize, String backPlateSize) throws ParseException {

        Response insuranceResponse;
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(RegisterNewVehiclePayLoad.registerPayLoad(chassisNo, EID,plateCategory,frontPlateSize,backPlateSize));
        insuranceResponse = RestActions.buildNewRequest("https://vlsgw.external.apps.qa.licensing.rta.ae/external/buynewvehicle/api/application/vehicle" , declareEndpoint, RestActions.RequestType.POST)
                .setRequestBody(jsonObject)
                .setContentType(ContentType.JSON)
                .setAuthentication("FUTAIM_NEW_VEHILCE_DEALER","mrxT1Xj3RFDo9LOfJkRP9A==", RequestBuilder.AuthenticationType.BASIC)
                .performRequest();
        return insuranceResponse;
    }
}
