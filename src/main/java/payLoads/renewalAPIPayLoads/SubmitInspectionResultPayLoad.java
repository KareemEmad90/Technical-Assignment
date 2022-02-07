package payLoads.renewalAPIPayLoads;

public class SubmitInspectionResultPayLoad {

    public static String SubmitInspectionPayLoad(String applicationRefNo,String chasissNo, String result){

        String payload="{\n" +
                "\t\"applicationRefNo\": \""+applicationRefNo+"\",\n" +
                "\t\"vehicleSpecs\": {\n" +
                "\t\t\"chassisNumber\": \""+chasissNo+"\",\n" +
                "\t\t\"numberOfDoors\": 5,\n" +
                "\t\t\"numberOfPassengers\": 5,\n" +
                "\t\t\"emptyWeight\": 1500,\n" +
                "\t\t\"grossWeight\": 2500,\n" +
                "\t\t\"numberOfAxles\": 4,\n" +
                "\t\t\"driverSeatPosition\": \"RIGHT\",\n" +
                "\t\t\"dimension\": {\n" +
                "\t\t\t\"height\": 120,\n" +
                "\t\t\t\"weight\": 1500,\n" +
                "\t\t\t\"length\": 260\n" +
                "\t\t},\n" +
                "\t\t\"engineInfo\": {\n" +
                "\t\t\t\"number\": \"123456\",\n" +
                "\t\t\t\"power\": 350,\n" +
                "\t\t\t\"capacity\": 3500,\n" +
                "\t\t\t\"numberOfCylinders\": 6,\n" +
                "\t\t\t\"wheelDriveType\": \"FOUR_WD\",\n" +
                "\t\t\t\"gearType\": \"MANUAL\",\n" +
                "\t\t\t\"fuelInfo\": {\n" +
                "\t\t\t\t\"energyType\": \"ELECTRIC\",\n" +
                "\t\t\t\t\"subEnergyType\": \"BATTERY\",\n" +
                "\t\t\t\t\"fuelType\": \"BENZENE\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"class\": {\n" +
                "\t\t\t\"code\": \"VCL_ID_3\",\n" +
                "\t\t\t\"name\": {\n" +
                "\t\t\t\t\"ar\": \"مركبة خفيفه\",\n" +
                "\t\t\t\t\"en\": \"Light Vehicle\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"bodyType\": {\n" +
                "\t\t\t\"code\": \"SUV\",\n" +
                "\t\t\t\"name\": {\n" +
                "\t\t\t\t\"ar\": \"تجربة\",\n" +
                "\t\t\t\t\"en\": \"Test\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"vehiclePurpose\": {\n" +
                "\t\t\t\"code\": \"VDS_ID_2\",\n" +
                "\t\t\t\"name\": {\n" +
                "\t\t\t\t\"ar\": \"مركبة خفيفه\",\n" +
                "\t\t\t\t\"en\": \"Light Vehicle\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"manufacturer\": {\n" +
                "\t\t\t\"code\": \"123\",\n" +
                "\t\t\t\"name\": \"Toyota\",\n" +
                "\t\t\t\"model\": {\n" +
                "\t\t\t\t\"code\": \"VSM_ID_310000000891\",\n" +
                "\t\t\t\t\"name\": \"Corola\",\n" +
                "\t\t\t\t\"year\": 2015,\n" +
                "\t\t\t\t\"trim\": {\n" +
                "\t\t\t\t\t\"code\": \"ASD\",\n" +
                "\t\t\t\t\t\"name\": \"Test\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"origin\": {\n" +
                "\t\t\t\t\"code\": \"ARE\",\n" +
                "\t\t\t\t\"name\": {\n" +
                "\t\t\t\t\t\"ar\": \"فلسطين\",\n" +
                "\t\t\t\t\t\"en\": \"Palestine\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"color\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"code\": \"RED\",\n" +
                "\t\t\t\t\"name\": {\n" +
                "\t\t\t\t\t\"ar\": \"احمر\",\n" +
                "\t\t\t\t\t\"en\": \"Red\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"code\": \"BLUE\",\n" +
                "\t\t\t\t\"name\": {\n" +
                "\t\t\t\t\t\"ar\": \"ازرق\",\n" +
                "\t\t\t\t\t\"en\": \"BLUE\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t},\n" +
                "\t\"inspectionInfo\": {\n" +
                "\t\t\"cisReferenceNo\": \"1232312329\",\n" +
                "\t\t\"result\": \""+result+"\",\n" +
                "\t\t\"purposeType\": \"VEHICLE_LICENSING\",\n" +
                "\t\t\"centerCode\": 1234,\n" +
                "\t\t\"failedCount\": 2,\n" +
                "\t\t\"cisServiceCode\": \"1\",\n" +
                "\t\t\"paid\": false,\n" +
                "\t\t\"center\": {\n" +
                "\t\t\t\"type\": \"AGENCY\",\n" +
                "\t\t\t\"englishName\": \"Tasjeel Al Qusais\",\n" +
                "\t\t\t\"arabicName\": \"تسجيل القصيص\",\n" +
                "\t\t\t\"email\": \"email@m.com\",\n" +
                "\t\t\t\"mobileNo\": \"0557287193\"\n" +
                "\t\t},\n" +
                "\t\t\"emirate\": {\n" +
                "\t\t\t\"code\": \"DXB\",\n" +
                "\t\t\t\"name\": {\n" +
                "\t\t\t\t\"en\": \"Dubai\",\n" +
                "\t\t\t\t\"ar\": \"دبي\"\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t},\n" +
                "    \"inspectionFees\": {\n" +
                "\t\t\"counterDetails\": {\n" +
                "\t\t\t\"code\": \"1234\"\n" +
                "\t\t},\n" +
                "\t\t\"fees\":[\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"code\": \"LPG_CIS_VTP_FEE_001\",\n" +
                "\t\t\t\t\"description\": {\n" +
                "\t\t\t\t\t\"en\": \"VTP Fees\",\n" +
                "\t\t\t\t\t\"ar\": \"رسوم مركز الفحص\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"amount\": 70\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"code\": \"LPG_CIS_RTA_FEE_001\",\n" +
                "\t\t\t\t\"description\": {\n" +
                "\t\t\t\t\t\"en\": \"RTA Fees\",\n" +
                "\t\t\t\t\t\"ar\": \"رسوم هيئة\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"amount\": 80\n" +
                "\t\t\t},\n" +
                "            {\n" +
                "\t\t\t\t\"code\": \"LPG_CIS_VTP_FEE_002\",\n" +
                "\t\t\t\t\"description\": {\n" +
                "\t\t\t\t\t\"en\": \"Vehicle Inspection - Knowledge and Innovation Fee\",\n" +
                "\t\t\t\t\t\"ar\": \"فحص المركبه - درهم معرفة و إبتكار\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"amount\": 20\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}\n" +
                "}";

        return payload;
    }
}
