package api;

import com.shaft.api.RequestBuilder;
import com.shaft.api.RestActions;
import data.LoadProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.python.antlr.ast.Str;

public class SubmitProcessInfo extends BaseApi{
    private String submitProcessInfoEndpoint = "/inspection-process-info";
    static Logger log = Logger.getLogger(SubmitProcessInfo.class.getName());
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


        System.out.println("submitPrcessInfoReq "+submitPrcessInfoReq);
        System.out.println("processInfoObject "+processInfoObject);

        response = RestActions.buildNewRequest(LoadProperties.userData.getProperty("VLS_Vehicle_Renewal") , submitProcessInfoEndpoint, RestActions.RequestType.POST)
                .setRequestBody(submitPrcessInfoReq)
                .setContentType(ContentType.JSON)
                .addHeader("rta-unified-number",rtaUnifiedNumber)
                .setAuthentication(LoadProperties.userData.getProperty("CIS_UserName"),LoadProperties.userData.getProperty("CIS_Password"), RequestBuilder.AuthenticationType.BASIC)
                .performRequest();

        log.info("SubmitProcessInfo Response is >>> "+response.getBody().prettyPrint());
        return response;
    }
}