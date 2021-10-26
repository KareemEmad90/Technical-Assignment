package api;

import com.shaft.api.RequestBuilder;
import com.shaft.api.RestActions;
import data.LoadProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class InitiateRenewVehicleJourney extends BaseApi {
    private String initiateEndpoint = "/initiate";

    public Response initiateRenewVehicle(String rtaUnifiedNumber, String ChassisNumber) {
        Response response;
        JSONObject initiateJourneyReq = new JSONObject();

        initiateJourneyReq.put("journeyType", "RENEW_VEHICLE");
        initiateJourneyReq.put("chassisNumber", ChassisNumber);
        initiateJourneyReq.put("mobileNumber", "971568370331");
        initiateJourneyReq.put("cisTransactionRefNo", 1234597);

        response = RestActions.buildNewRequest("http://vlsgw.external.apps.qa.licensing.rta.ae/external/vehiclerenewaljourney/api/vehicle-renewal" , initiateEndpoint, RestActions.RequestType.POST)
                .setRequestBody(initiateJourneyReq)
                .setContentType(ContentType.JSON)
                .addHeader("rta-unified-number",rtaUnifiedNumber)
                .setAuthentication("CIS_USER","lVzBSZ2S/oGkN6WYbe+QhA==", RequestBuilder.AuthenticationType.BASIC)
                .performRequest();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
}
