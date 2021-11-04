package api.registerNewVehicleAPIs;

import com.shaft.api.RequestBuilder;
import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import payLoads.PayApplicationPayLoad;

public class PayApplicationAPI {

    private String declareEndpoint = "/pay";

    public Response payApplicationResponse(String applicationRefNo) throws ParseException {

        Response response;
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(PayApplicationPayLoad.payApplicationPayLoad(applicationRefNo));
        response = RestActions.buildNewRequest("https://vlsgw.external.apps.qa.licensing.rta.ae/external/buynewvehicle/api/application" , declareEndpoint, RestActions.RequestType.POST)
                .setRequestBody(jsonObject)
                .setContentType(ContentType.JSON)
                .setAuthentication("FUTAIM_NEW_VEHILCE_DEALER","mrxT1Xj3RFDo9LOfJkRP9A==", RequestBuilder.AuthenticationType.BASIC)
                .performRequest();
        return response;
    }
}
