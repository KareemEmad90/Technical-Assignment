package payLoads;

public class PayApplicationPayLoad {

    public static String payApplicationPayLoad(String applicationRefNo) {

        String payApplicationJSONPayload = "{\n" +
                "    \"applicationReferenceNo\":\"" + applicationRefNo + "\",\n" +
                "    \"paymentDateTime\" : \"\",\n" +
                "    \"rtaPaymentReference\" : \"\",\n" +
                "    \"paymentMethod\" : 1\n" +
                "}";


        return payApplicationJSONPayload;
    }
}