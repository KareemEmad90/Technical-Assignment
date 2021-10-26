package api;

import com.shaft.api.RequestBuilder;
import com.shaft.api.RestActions;
import data.LoadProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.python.antlr.ast.Str;

public class SubmitProcessInfo extends BaseApi{
    private String submitProcessInfoEndpoint = "/inspection-process-info";

    public Response submitProcessInfoResponse(String applicationReferenceNo, String rtaUnifiedNumber, String processInfoCode) {
        Response response;
        JSONObject submitPrcessInfoReq = new JSONObject();
        JSONObject processInfoObject = new JSONObject();

        processInfoObject.put("code", processInfoCode);
        processInfoObject.put("tokenNumber", "123");
        processInfoObject.put("laneNumber", "4");
        processInfoObject.put("estimatedTimeToComplete", "2");
        processInfoObject.put("paid", true);

        submitPrcessInfoReq.put("applicationRefNo", applicationReferenceNo);
        submitPrcessInfoReq.put("processInfo", processInfoObject);

        response = RestActions.buildNewRequest("http://vlsgw.external.apps.qa.licensing.rta.ae/external/vehiclerenewaljourney/api/vehicle-renewal" , submitProcessInfoEndpoint, RestActions.RequestType.POST)
                .setRequestBody(submitPrcessInfoReq)
                .setContentType(ContentType.JSON)
                .addHeader("rta-unified-number",rtaUnifiedNumber)
                .setAuthentication("CIS_USER","lVzBSZ2S/oGkN6WYbe+QhA==", RequestBuilder.AuthenticationType.BASIC)
                .performRequest();
        return response;
    }
}