package payLoads;

public class VehicleDeclarePayLoad {

    public static String declarePayLoad(String chassisNo , String weight  ,String vehicleClassCode, String arName, String enName , String year) {
        String declareJSONBody = "{\n" +
                "    \"vehicleSpecs\": {\n" +
                "        \"chassisNumber\": \""+chassisNo+"\",\n" +
                "        \"numberOfDoors\": 5,\n" +
                "        \"emptyWeight\": "+weight+",\n" +
                "        \"grossWeight\": 2722,\n" +
                "        \"numberOfAxles\": 2,\n" +
                "        \"odometerReading\": 65909,\n" +
                "        \"driverSeatPosition\": \"LEFT\",\n" +
                "        \"gearType\": \"AUTO\",\n" +
                "        \"dimension\": {\n" +
                "            \"height\": 100,\n" +
                "            \"weight\": 100,\n" +
                "            \"length\": 100\n" +
                "        },\n" +
                "        \"engineInfo\": {\n" +
                "            \"number\": \"VQ35 122108X\",\n" +
                "            \"power\": 265,\n" +
                "            \"capacity\": 3700,\n" +
                "            \"numberOfCylinders\": 6,\n" +
                "            \"fuelInfo\": {\n" +
                "                \"energyType\": \"CONVENTIONAL_FUEL\",\n" +
                "                \"subEnergyType\": \"BENZENE\",\n" +
                "                \"fuelType\": \"BENZENE\"\n" +
                "            },\n" +
                "            \"gearType\": \"AUTO\",\n" +
                "            \"wheelDriveType\": \"FOUR_WD\"\n" +
                "        },\n" +
                "        \"class\": {\n" +
                "            \"code\": \""+vehicleClassCode+"\",\n" +
                "            \"name\": {\n" +
                "                \"ar\": \""+arName+"\",\n" +
                "                \"en\": \""+enName+"\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"manufacturer\": {\n" +
                "            \"code\": \"VMK_ID_10125\",\n" +
                "            \"name\": \"INFINITI\",\n" +
                "            \"model\": {\n" +
                "                \"code\": \"VSM_ID_9730007161\",\n" +
                "                \"name\": \"JX35\",\n" +
                "                \"year\": "+year+"\n" +
                "            },\n" +
                "            \"origin\": {\n" +
                "                \"code\": \"USA\",\n" +
                "                \"name\": {\n" +
                "                    \"ar\": \"الولايات المتحدة\",\n" +
                "                    \"en\": \"United States\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        \"color\": [\n" +
                "            {\n" +
                "                \"sequence\": 1,\n" +
                "                \"code\": \"WHITE\",\n" +
                "                \"name\": {\n" +
                "                    \"ar\": \"ابيض\",\n" +
                "                    \"en\": \"White\"\n" +
                "                }\n" +
                "            }\n" +
                "        ],\n" +
                "        \"numberOfPassengers\": 7,\n" +
                "        \"bodyType\": {\n" +
                "            \"code\": \"Sedan\",\n" +
                "            \"name\": {\n" +
                "                \"ar\": \"سيدان\",\n" +
                "                \"en\": \"Sedan\"\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"declarationSummaryInfo\": {\n" +
                "        \"sourceDetails\": {\n" +
                "            \"referenceNumber\": \"9548297\",\n" +
                "            \"sourceType\": \"CUSTOM\",\n" +
                "            \"issueDate\": \"2016-04-20\",\n" +
                "            \"emirate\": {\n" +
                "                \"code\": \"DXB\",\n" +
                "                \"name\": {\n" +
                "                    \"ar\": \"دبي\",\n" +
                "                    \"en\": \"Dubai\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"country\": {\n" +
                "                \"code\": \"UAE\",\n" +
                "                \"name\": {\n" +
                "                    \"ar\": \"الإمارات العربية المتحدة\",\n" +
                "                    \"en\": \"United Arab Emirates\"\n" +
                "                },\n" +
                "                \"emirate\": {\n" +
                "                    \"code\": \"DXB\",\n" +
                "                    \"name\": {\n" +
                "                        \"ar\": \"دبي\",\n" +
                "                        \"en\": \"Dubai\"\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        return declareJSONBody;
    }
}
