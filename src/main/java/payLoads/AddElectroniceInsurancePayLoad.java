package payLoads;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddElectroniceInsurancePayLoad {

    public static String insourancePayLoad(String rtaUnifiedNo,String chassisNo) {

        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        cal.add(Calendar.DATE, -1);
        Date startInsuranceDate=cal.getTime();
        cal.add(Calendar.YEAR, 1);
        Date dateAfterYear=cal.getTime();


        int min = 1000000;
        int max = 9000000;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        System.out.println("random_int  "+random_int);
        String insouranceXMLPayload="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:ElectronicInsuranceService\">\n" +
                "   <soapenv:Header>\n" +
                "      <urn:insuranceUserName>InsuranceComp23</urn:insuranceUserName>\n" +
                "      <urn:password>justfortest</urn:password>\n" +
                "      <urn:username>tibco_user</urn:username>\n" +
                "   </soapenv:Header>\n" +
                "   <soapenv:Body>\n" +
                "      <urn:addVehicleInsurance>\n" +
                "         <urn:insuranceInfo>\n" +
                "            <urn:policyNo>"+random_int+"</urn:policyNo>\n" +
                "            <urn:insuranceType>Comprehensive</urn:insuranceType>\n" +
                "            <urn:policyDate>"+dateFormat.format(startInsuranceDate)+"T21:19:00.000Z</urn:policyDate>\n" +
                "            <urn:policyExpiryDate>"+dateFormat.format(dateAfterYear)+"T21:19:00.000Z</urn:policyExpiryDate>\n" +
                "            <urn:insuranceCompanyId>64070</urn:insuranceCompanyId>\n" +
                "            <urn:chassisNo>"+chassisNo+"</urn:chassisNo>\n" +
                "            <urn:modelYear>2018</urn:modelYear>\n" +
                "            <urn:transactionType>201</urn:transactionType>\n" +
                "            <urn:trafficFileNo>"+rtaUnifiedNo+"</urn:trafficFileNo>\n" +
                "           <urn:insuranceHolderNameEn>Test Oriental</urn:insuranceHolderNameEn>\n" +
                "           <urn:isCoveredFamilyEmployees>1</urn:isCoveredFamilyEmployees>\n" +
                "            <urn:geographicCoverage>\n" +
                "               <urn:countryCode>AE</urn:countryCode>\n" +
                "            </urn:geographicCoverage>\n" +
                "            <urn:vehicleValue>50000</urn:vehicleValue>\n" +
                "            <urn:insuranceAmount>1500</urn:insuranceAmount>\n" +
                "            <urn:isCoveredDriver>1</urn:isCoveredDriver>\n" +
                "         </urn:insuranceInfo>\n" +
                "      </urn:addVehicleInsurance>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        return insouranceXMLPayload;
    }
}
