package payLoads;

public class RegisterNewVehiclePayLoad {

    public static String registerPayLoad(String chassisNo,String EID , String plateCategory , String frontPlateSize, String backPlateSize,String logoType) {

        String registerVehicleJSONPayload = "{\n" +
                "    \"chassisNumber\": \""+chassisNo+"\",\n" +
                "    \"buyerEmiratesId\": \""+EID+"\",\n" +
                "    \"plateCategory\": \""+plateCategory+"\",\n" +
                "    \"frontPlateSize\": \""+frontPlateSize+"\",\n" +
                "    \"backPlateSize\": \""+backPlateSize+"\",\n" +
                "    \"plateDesign\": \""+logoType+"\",\n" +
                "    \"plateSource\": \"FREE\",\n" +
                "    \"purpose\": \"PRIVATE\"\n" +
                "}";


return registerVehicleJSONPayload;
    }

}
