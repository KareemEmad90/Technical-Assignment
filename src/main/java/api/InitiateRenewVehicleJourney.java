package api;

import com.shaft.api.RequestBuilder;
import com.shaft.api.RestActions;
import data.LoadProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

public class InitiateRenewVehicleJourney extends BaseApi {
    private String initiateEndpoint = "/initiate";
    static Logger log = Logger.getLogger(InitiateRenewVehicleJourney.class.getName());
    public Response initiateRenewVehicle(String rtaUnifiedNumber, String ChassisNumber) {
        Response response;
        JSONObject initiateJourneyReq = new JSONObject();

        initiateJourneyReq.put("journeyType", "RENEW_VEHICLE");
        initiateJourneyReq.put("chassisNumber", ChassisNumber);
        initiateJourneyReq.put("mobileNumber", "971568370331");
        initiateJourneyReq.put("cisTransactionRefNo", 14744570);

        response = RestActions.buildNewRequest(LoadProperties.userData.getProperty("VLS_Vehicle_Renewal"), initiateEndpoint, RestActions.RequestType.POST)
                .setRequestBody(initiateJourneyReq)
                .setContentType(ContentType.JSON)
                .addHeader("rta-unified-number",rtaUnifiedNumber)
                .setAuthentication(LoadProperties.userData.getProperty("CIS_UserName"),LoadProperties.userData.getProperty("CIS_Password"), RequestBuilder.AuthenticationType.BASIC)
                .performRequest();

        log.info("InitiateRenewVehicleJourney Response  >> "+response.getBody().prettyPrint());
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
}
