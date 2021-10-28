package api.registerNewVehicleAPIs;

import com.shaft.api.RequestBuilder;
import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import payLoads.RegisterNewVehiclePayLoad;

public class ApplicationReceiptAPI {

    //private String declareEndpoint = "/register";

    public Response getReceiptResponse(String declareEndpoint ) throws ParseException {

        Response getReceiptResponse;

        getReceiptResponse = RestActions.buildNewRequest("https://vlsgw.external.apps.qa.licensing.rta.ae/external/buynewvehicle/api/application/vehicle" , declareEndpoint, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .setAuthentication("FUTAIM_NEW_VEHILCE_DEALER","mrxT1Xj3RFDo9LOfJkRP9A==", RequestBuilder.AuthenticationType.BASIC)
                .performRequest();
        return getReceiptResponse;
    }
}
