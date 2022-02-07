package api;

import com.shaft.api.RequestBuilder;
import com.shaft.api.RestActions;
import data.LoadProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class CheckEligibilityAPI extends BaseApi {
    private String eligibilityEndpoint = "/check-eligibility";

    static Logger log = Logger.getLogger(CheckEligibilityAPI.class.getName());

    public Response checkEligibility(String rtaUnifiedNumber, String chassisNo) throws ParseException {
        Response response;
        JSONObject checkEligibilityJourneyRequestBody = new JSONObject();

        checkEligibilityJourneyRequestBody.put("journeyCode", "RENEW_VEHICLE");
        checkEligibilityJourneyRequestBody.put("chassisNumber", chassisNo);
        response = RestActions.buildNewRequest("https://vlsgw.external.qa.rta.ae/external/vehiclerenewaljourney/api/vehicle-renewal" , eligibilityEndpoint, RestActions.RequestType.POST)
                .setRequestBody(checkEligibilityJourneyRequestBody)
                .setContentType(ContentType.JSON)
                .addHeader("rta-unified-number",rtaUnifiedNumber)
                .setAuthentication(LoadProperties.userData.getProperty("CIS_UserName"),LoadProperties.userData.getProperty("CIS_Password"), RequestBuilder.AuthenticationType.BASIC)
                .performRequest();

        log.info("CheckEligibilityAPI Response  >>>>"+response.getBody().prettyPrint());

        return response;
    }
}
