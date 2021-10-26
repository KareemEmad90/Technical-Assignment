package payLoads;

public class RegisterNewVehiclePayLoad {

    public static String registerPayLoad(String chassisNo,String EID) {

        String registerVehicleJSONPayload = "{\n" +
                "    \"chassisNumber\": \""+chassisNo+"\",\n" +
                "    \"buyerEmiratesId\": \""+EID+"\",\n" +
                "    \"plateCategory\": \"PRIVATE\",\n" +
                "    \"frontPlateSize\": \"SHORT\",\n" +
                "    \"backPlateSize\": \"SHORT\",\n" +
                "    \"plateDesign\": \"NORMAL\",\n" +
                "    \"plateSource\": \"FREE\",\n" +
                "    \"purpose\": \"PRIVATE\"\n" +
                "}";


return registerVehicleJSONPayload;
    }

}
