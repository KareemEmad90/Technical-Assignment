package api;

import com.shaft.api.RequestBuilder;
import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import payLoads.AddInsourancePayLoad;

public class AddInsuranceAPI {

    private String declareEndpoint = "/submit";

    public Response AddInsuranceResponse(String rtaUnifiedNo, String chassisNo ) throws ParseException {

        Response insuranceResponse;
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(AddInsourancePayLoad.insourancePayLoad(rtaUnifiedNo, chassisNo));
        insuranceResponse = RestActions.buildNewRequest("https://vlsgw.portal.apps.qa.licensing.rta.ae/services/insurance/api/vehicle/insurances" , declareEndpoint, RestActions.RequestType.POST)
                .setRequestBody(jsonObject)
                .setContentType(ContentType.JSON)
                .setAuthentication("FUTAIM_NEW_VEHILCE_DEALER","mrxT1Xj3RFDo9LOfJkRP9A==", RequestBuilder.AuthenticationType.BASIC)
                .performRequest();
        return insuranceResponse;
    }
}
