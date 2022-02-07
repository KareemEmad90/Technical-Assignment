package api;

import com.shaft.api.RequestBuilder;
import com.shaft.api.RestActions;
import data.LoadProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import payLoads.renewalAPIPayLoads.SubmitInspectionResultPayLoad;

public class SubmitInspectionResult extends BaseApi {
    private String submitInspectionResultEndpoint = "/submit-inspection-result";
    static Logger log = Logger.getLogger(SubmitInspectionResult.class.getName());
    public Response submitInspectionResult(String applicationReferenceNumber, String rtaUnifiedNumber, String chassisNumber, String result) throws ParseException {
        Response response;
        /*
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


         */
        JSONParser parser = new JSONParser();
        JSONObject submitInspectionResultReq = (JSONObject) parser.parse(SubmitInspectionResultPayLoad.SubmitInspectionPayLoad(applicationReferenceNumber,chassisNumber,result));
        System.out.println("submitInspectionResultReq  >>>> "+submitInspectionResultReq);
        response = RestActions.buildNewRequest("https://vlsgw.external.qa.rta.ae/external/vehiclerenewaljourney/api/vehicle-renewal" , submitInspectionResultEndpoint, RestActions.RequestType.POST)
                .setRequestBody(submitInspectionResultReq)
                .setContentType(ContentType.JSON)
                .addHeader("rta-unified-number",rtaUnifiedNumber)
                .setAuthentication(LoadProperties.userData.getProperty("CIS_UserName"),LoadProperties.userData.getProperty("CIS_Password"), RequestBuilder.AuthenticationType.BASIC)
                .performRequest();

        log.info("SubmitInspectionResult Response is >> "+response.getBody().prettyPrint());
        return response;
    }
}
