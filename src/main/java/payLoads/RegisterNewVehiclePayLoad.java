package payLoads;

public class RegisterNewVehiclePayLoad {

    public static String registerPayLoad(String chassisNo,String EID , String plateCategory , String frontPlateSize, String backPlateSize) {

        String registerVehicleJSONPayload = "{\n" +
                "    \"chassisNumber\": \""+chassisNo+"\",\n" +
                "    \"buyerEmiratesId\": \""+EID+"\",\n" +
                "    \"plateCategory\": \""+plateCategory+"\",\n" +
                "    \"frontPlateSize\": \""+frontPlateSize+"\",\n" +
                "    \"backPlateSize\": \""+backPlateSize+"\",\n" +
                "    \"plateDesign\": \"NORMAL\",\n" +
                "    \"plateSource\": \"FREE\",\n" +
                "    \"purpose\": \"PRIVATE\"\n" +
                "}";


return registerVehicleJSONPayload;
    }

}
