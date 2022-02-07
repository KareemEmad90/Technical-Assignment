package api;

import com.shaft.api.RequestBuilder;
import com.shaft.api.RestActions;
import data.LoadProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import payLoads.VehicleDeclarePayLoad;
import utils.ChassisGeneration;

import static io.restassured.RestAssured.given;

public class DeclareVehicleAPI {

    private String declareEndpoint = "/declare";
   // String chassisNo;
    public Response declareSerivceResponse(String chassisNo,String weight  ,String vehicleClassCode, String arName, String enName , String year) throws ParseException {
        Response response;
       //  chassisNo=ChassisGeneration.ChassisNo();
        System.out.println("chassisNo "+chassisNo);
        JSONParser parser = new JSONParser();
        //JSONObject jsonObject = (JSONObject) parser.parse(VehicleDeclarePayLoad.declarePayLoad(chassisNo,weight,vehicleClassCode,arName ,enName,year) );
        String jsonObjectx = VehicleDeclarePayLoad.declarePayLoad(chassisNo,weight,vehicleClassCode,arName ,enName,year);
      //  System.out.println(jsonObject);
//        response = RestActions.buildNewRequest("https://vlsgw.external.apps.qa.licensing.rta.ae/external/buynewvehicle/api/application/vehicle" , declareEndpoint, RestActions.RequestType.POST)
//                .setRequestBody(jsonObject)
//                .setContentType(ContentType.JSON)
//                .setAuthentication("FUTAIM_NEW_VEHILCE_DEALER","mrxT1Xj3RFDo9LOfJkRP9A==", RequestBuilder.AuthenticationType.BASIC)
//                .performRequest();
//        return response;


      Response  responses  =given().header("Content-type", "application/json")
              .and().auth().preemptive().basic("FUTAIM_NEW_VEHILCE_DEALER", "mrxT1Xj3RFDo9LOfJkRP9A==")
              .body(jsonObjectx)
              .when()
              .post(LoadProperties.userData.getProperty("Declare_Vehicle"))
              .then()
              .extract().response();

      return responses;

   }
}
