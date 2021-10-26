package api;

import com.shaft.api.RequestBuilder;
import com.shaft.api.RestActions;
import data.LoadProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.simple.JSONObject;

public class SubmitInspectionResult extends BaseApi {
    private String submitInspectionResultEndpoint = "/submit-inspection-result";

    public Response submitInspectionResult(String applicationReferenceNumber, String rtaUnifiedNumber, String chassisNumber, String result) {
        Response response;
        JSONObject submitInspectionResultReq = new JSONObject();
        JSONObject vehicleSpecsObject = new JSONObject();
        JSONObject originName = new JSONObject();
        JSONObject manufacturerInfoObject = new JSONObject();
        JSONObject modelInfoObject = new JSONObject();
        JSONObject modelTrimInfoObject = new JSONObject();
        JSONObject originInfoObject = new JSONObject();
        JSONObject inspectionInfoObject = new JSONObject();


        originInfoObject.put("code", "ARE");
        originInfoObject.put("name", originName);

        originName.put("ar", "فلسطين");
        originName.put("en", "Palestine");

        modelTrimInfoObject.put("code", "ASD");
        modelTrimInfoObject.put("name", "Test");

        modelInfoObject.put("code", "VSM_ID_310000000891");
        modelInfoObject.put("name", "Corola");
        modelInfoObject.put("year", 2015);
        modelInfoObject.put("trim", modelTrimInfoObject);

        manufacturerInfoObject.put("code", "123");
        manufacturerInfoObject.put("name", "Toyota");
        manufacturerInfoObject.put("model", modelInfoObject);
        manufacturerInfoObject.put("origin", originInfoObject);


        vehicleSpecsObject.put("chassisNumber", chassisNumber);
        vehicleSpecsObject.put("manufacturer", manufacturerInfoObject);

        inspectionInfoObject.put("referenceNo", "1234567");
        inspectionInfoObject.put("result", result);
        inspectionInfoObject.put("purposeType", "VEHICLE_LICENSING");
        inspectionInfoObject.put("failedCount", 2);
        inspectionInfoObject.put("cisServiceCode", "1");
        inspectionInfoObject.put("paid", true);

        submitInspectionResultReq.put("applicationRefNo", applicationReferenceNumber);
        submitInspectionResultReq.put("vehicleSpecs", vehicleSpecsObject);
        submitInspectionResultReq.put("inspectionInfo", inspectionInfoObject);



        response = RestActions.buildNewRequest("http://vlsgw.external.apps.qa.licensing.rta.ae/external/vehiclerenewaljourney/api/vehicle-renewal" , submitInspectionResultEndpoint, RestActions.RequestType.POST)
                .setRequestBody(submitInspectionResultReq)
                .setContentType(ContentType.JSON)
                .addHeader("rta-unified-number",rtaUnifiedNumber)
                .setAuthentication("CIS_USER","lVzBSZ2S/oGkN6WYbe+QhA==", RequestBuilder.AuthenticationType.BASIC)
                .performRequest();
        return response;
    }
}
