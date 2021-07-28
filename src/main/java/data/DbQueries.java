package data;

import com.shaft.db.DatabaseActions;
import io.qameta.allure.*;
import org.stringtemplate.v4.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbQueries extends DBConnections{

    String[] vehicle = new String[6];
    public String[] getVehicle (){
        //databaseActions = new DatabaseActions();
     setConnection();
        ResultSet result = databaseActions.executeSelectQuery("SELECT * FROM QC_USERS.GET_EXPIRED_VEHICLE_VLS  where RTA_UNIFIED_NO not in (11295399) and rownum <2");

        try {
            vehicle[0] = result.getString(1);
            vehicle[1] = result.getString(2);
            vehicle[2] = result.getString(3);
            vehicle[3] = result.getString(4);
            vehicle[4] = result.getString(5);
            vehicle[5] = result.getString(6);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Chassis = " + vehicle[0] + "  --- RTA_UNIFIED_NO ID = " + vehicle[1] + "  --- EID_NUMBER ID = " + vehicle[2] + "  --- EXPIRY_DATE ID = " + vehicle[3]+
                "  --- PLATE No = " + vehicle[4]+ "  --- Plate Code = " + vehicle[5]);

        return vehicle;
    }

    @Step("Update Vehicle License Expiry Date")
    public void updatelicenseexpirydate(String chassis_no , String numberofdays) {
        DBConnections dbConnections = new DBConnections();
        dbConnections.setConnection();
        databaseActions.executeUpdateQuery("DECLARE\n" +
                "            V_PRODUCT_DOCUMENT CLOB;\n" +
                "            V_JJ JSON := json();\n" +
                "            BEGIN\n" +
                "            ROLLBACK;\n" +
                "            SELECT PRODUCT_DOCUMENT\n" +
                "            INTO V_PRODUCT_DOCUMENT\n" +
                "            FROM VLS_VEHICLE_LICENSE.PRD_VEHICLE_LICENSE\n" +
                "            WHERE  JSON_VALUE(PRODUCT_DOCUMENT, '$.vehicleLicenseInfo.vehicleSummaryInfo.chassisNumber' RETURNING VARCHAR2(200)) in ("+chassis_no+");\n" +
                "            V_JJ := JSON (V_PRODUCT_DOCUMENT);\n" +
                "            json_ext.put( V_JJ ,'vehicleLicenseInfo.expiryDate',to_char(sysdate-"+numberofdays+",'YYYY-MM-DD'));\n" +
                "            UPDATE VLS_VEHICLE_LICENSE.PRD_VEHICLE_LICENSE\n" +
                "            SET PRODUCT_DOCUMENT = PLJSON_PRINTER.PRETTY_PRINT1 (V_JJ)\n" +
                "            WHERE JSON_VALUE(PRODUCT_DOCUMENT, '$.vehicleLicenseInfo.vehicleSummaryInfo.chassisNumber' RETURNING VARCHAR2(200)) in ("+chassis_no+");\n" +
                "            commit;\n" +
                "            END;");
    }

    public String[] getCertificate() {
        String [] certificate = new String[6];
        return certificate;
    }


  /*  @Step("Update Vehicle Empity Weight")
    public void updatevehicleempityweight(chassis_no , weight) {
        DatabaseActionsCustom databaseActionsCustom = new DatabaseActionsCustom();
        databaseActionsCustom.executeUpdateQuery("DECLARE\n" +
                "            V_PRODUCT_DOCUMENT CLOB;\n" +
                "            V_JJ JSON := json();\n" +
                "            BEGIN\n" +
                "            ROLLBACK;\n" +
                "            SELECT PRODUCT_DOCUMENT\n" +
                "            INTO V_PRODUCT_DOCUMENT\n" +
                "            FROM VLS_VEHICLE.REP_VEHICLE\n" +
                "            WHERE  JSON_VALUE(PRODUCT_DOCUMENT, '$.vehicleInfo.vehicleSpecs.chassisNumber' RETURNING VARCHAR2(200)) in ("+chassis_no+");\n" +
                "            V_JJ := JSON (V_PRODUCT_DOCUMENT);\n" +
                "            json_ext.put( V_JJ ,'vehicleInfo.vehicleSpecs.emptyWeight',"+weight+");\n" +
                "            UPDATE VLS_VEHICLE.REP_VEHICLE\n" +
                "            SET PRODUCT_DOCUMENT = PLJSON_PRINTER.PRETTY_PRINT1 (V_JJ)\n" +
                "            WHERE JSON_VALUE(PRODUCT_DOCUMENT, '$.vehicleInfo.vehicleSpecs.chassisNumber' RETURNING VARCHAR2(200)) in ("+chassis_no+");\n" +
                "            commit;\n" +
                "            END;");
    }


    @Step("Update Vehicle Gross Weight")
    public void updatevehiclegrossweight(chassis_no , weight) {
        DatabaseActionsCustom databaseActionsCustom = new DatabaseActionsCustom();
        databaseActionsCustom.executeUpdateQuery("DECLARE\n" +
                "            V_PRODUCT_DOCUMENT CLOB;\n" +
                "            V_JJ JSON := json();\n" +
                "            BEGIN\n" +
                "            ROLLBACK;\n" +
                "            SELECT PRODUCT_DOCUMENT\n" +
                "            INTO V_PRODUCT_DOCUMENT\n" +
                "            FROM VLS_VEHICLE.REP_VEHICLE\n" +
                "            WHERE  JSON_VALUE(PRODUCT_DOCUMENT, '$.vehicleInfo.vehicleSpecs.chassisNumber' RETURNING VARCHAR2(200)) in ("+chassis_no+");\n" +
                "            V_JJ := JSON (V_PRODUCT_DOCUMENT);\n" +
                "            json_ext.put( V_JJ ,'vehicleInfo.vehicleSpecs.grossWeight',"+weight+");\n" +
                "            UPDATE VLS_VEHICLE.REP_VEHICLE\n" +
                "            SET PRODUCT_DOCUMENT = PLJSON_PRINTER.PRETTY_PRINT1 (V_JJ)\n" +
                "            WHERE JSON_VALUE(PRODUCT_DOCUMENT, '$.vehicleInfo.vehicleSpecs.chassisNumber' RETURNING VARCHAR2(200)) in ("+chassis_no+");\n" +
                "            commit;\n" +
                "            END;");
    }

    @Step("Reset Viloations")
    public void resetviloation(traffic_no , chassis_no) {
        DatabaseActionsCustom databaseActionsCustom = new DatabaseActionsCustom();
        databaseActionsCustom.executeUpdateQuery("EXEC QC_USERS.PKG_ADDING_VIOLATIONS.P_RESET_VIOLATIONS("+traffic_no+", "+chassis_no+");");
    }

    @Step("ADD Electronic Insurance")
    public void addelectronicinsurance(traffic_no , chassis_no) {
        DatabaseActionsCustom databaseActionsCustom = new DatabaseActionsCustom();
        databaseActionsCustom.executeUpdateQuery("EXEC QC_USERS.Add_SYNC_INSURANCE("+traffic_no+", "+chassis_no+");");
    }
*/
}
