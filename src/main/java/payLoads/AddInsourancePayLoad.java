package payLoads;

public class AddInsourancePayLoad {

    public static String insourancePayLoad(String rtaUnifiedNo,String chassisNo) {

        String insouranceJSONPayload="{\n" +
                "  \"customerInfo\": {\n" +
                "    \"rtaUnifiedNo\": \""+rtaUnifiedNo+"\",\n" +
                "    \"arabicFullName\": \"سيف على احمد حسن المسلمى\",\n" +
                "    \"englishFullName\": \"SAIF ALI AHMED HASAN ALMUSALLAMI\",\n" +
                "    \"gender\": \"MALE\",\n" +
                "    \"pod\": false,\n" +
                "    \"professionCode\": \"12240\",\n" +
                "    \"birthdate\": \"1997-08-25\",\n" +
                "    \"mobile\": \"971523500866\",\n" +
                "    \"email\": \"XY@RTA.COM\"\n" +
                "  },\n" +
                "  \"policyInfo\": {\n" +
                "    \"referenceNumber\": \"2123035112\",\n" +
                "    \"chassisNumber\": \""+chassisNo+"\",\n" +
                "    \"purposeType\": \"VEHICLE_LICENSING\",\n" +
                "    \"status\": \"AVAILABLE\",\n" +
                "    \"insuranceAmount\": 650,\n" +
                "    \"coveringDriver\": false,\n" +
                "    \"coveringPassengers\": false,\n" +
                "    \"insuranceType\": {\n" +
                "      \"type\": \"THIRD_PARTY\",\n" +
                "      \"description\": {\n" +
                "        \"ar\": \"ضد الغير\",\n" +
                "        \"en\": \" Third Party\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"startDate\": \"2021-10-25\",\n" +
                "    \"expiryDate\": \"2022-11-25\",\n" +
                "    \"vehicleInsuredValue\": 0,\n" +
                "    \"insuranceCompany\": {\n" +
                "      \"tradeLicenseInfo\": {\n" +
                "        \"licenseNumber\": \"617826\",\n" +
                "        \"source\": {\n" +
                "          \"code\": \"ARE\",\n" +
                "          \"name\": {\n" +
                "            \"ar\": \"الإمارات العربية المتحدة\",\n" +
                "            \"en\": \"United Arab Emirates \"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"arabicName\": \"شركة ميثاق للتأمين التكافلي  ش م ع  فرع دبي\",\n" +
                "      \"englishName\": \"METHAQ TAKAFUL INSURANCE  P S C  DUBAI BR\"\n" +
                "    },\n" +
                "    \"geographicCoverage\": [\n" +
                "      {\n" +
                "        \"code\": \"ARE\",\n" +
                "        \"name\": {\n" +
                "          \"ar\": \"الامارات\",\n" +
                "          \"en\": \"Emirates\"\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"businessAction\": \"Add\"\n" +
                "}";

        return insouranceJSONPayload;
    }
}
