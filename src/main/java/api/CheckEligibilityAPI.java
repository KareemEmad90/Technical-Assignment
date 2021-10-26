package api;

import com.shaft.api.RequestBuilder;
import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class CheckEligibilityAPI extends BaseApi {
    private String eligibilityEndpoint = "/check-eligibility";
    public Response checkEligibility(String rtaUnifiedNumber, String chassisNo) {
        Response response;
        JSONObject checkEligibilityJourneyRequestBody = new JSONObject();

        checkEligibilityJourneyRequestBody.put("journeyCode", "RENEW_VEHICLE");
        checkEligibilityJourneyRequestBody.put("chassisNumber", chassisNo);
        response = RestActions.buildNewRequest("http://vlsgw.external.apps.qa.licensing.rta.ae/external/vehiclerenewaljourney/api/vehicle-renewal" , eligibilityEndpoint, RestActions.RequestType.POST)
                .setRequestBody(checkEligibilityJourneyRequestBody)
                .setContentType(ContentType.JSON)
                .addHeader("rta-unified-number",rtaUnifiedNumber)
                .setAuthentication("CIS_USER","lVzBSZ2S/oGkN6WYbe+QhA==", RequestBuilder.AuthenticationType.BASIC)
                .performRequest();
        return response;
    }
}
