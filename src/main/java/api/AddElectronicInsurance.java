package api;



import data.LoadProperties;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import payLoads.AddElectroniceInsurancePayLoad;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class AddElectronicInsurance {

    public void elecInsuranceAPI(String rtaUnifiedNo,String chassisNo,
                                 String PlateNo,String PlateCode) {

        String payload = AddElectroniceInsurancePayLoad.insourancePayLoad(rtaUnifiedNo,
                chassisNo, PlateNo, PlateCode);

    Response getElectronicInsuranceResponse;
        HashMap<String, String> headersMap = new HashMap<String, String>() ;
        headersMap.put("Content-Type","text/xml");
        headersMap.put("SOAPAction","");

        getElectronicInsuranceResponse = given().config(RestAssured.config()
            .encoderConfig(EncoderConfig.encoderConfig()
                    .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
            .relaxedHTTPSValidation().headers(headersMap)
            .body(payload)
            .when()
            .post(LoadProperties.userData.getProperty("Electronic_Insurance"));

        Assert.assertEquals(getElectronicInsuranceResponse.statusCode(),200);
        XmlPath xml = new XmlPath(getElectronicInsuranceResponse.asString());
        String message = xml.getString("status");
        Assert.assertTrue(message.contains("successfully"));

    }
}
