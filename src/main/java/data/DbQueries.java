package data;

import io.qameta.allure.Step;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbQueries extends DBConnections{

    String[] vehicle = new String[9];
    public String[] getVehicle(String Vehicle_Type) {

        setConnection();
        ResultSet result = databaseActions.executeSelectQuery("SELECT JSON_VALUE (VEHE.PRODUCT_DOCUMENT,\n" +
                "                   '$.vehicleInfo.vehicleSpecs.chassisNumber'\n" +
                "                   RETURNING VARCHAR2 (200)\n" +
                "                   NULL ON ERROR)\n" +
                "          CHASSIS_NO,\n" +
                "       JSON_VALUE (INPR.PROFILE_DOCUMENT,\n" +
                "                   '$.summaryInfo.rtaUnifiedNo'\n" +
                "                   RETURNING NUMBER (15, 0)\n" +
                "                       NULL ON ERROR)\n" +
                "               RTA_UNIFIED_NO,\n" +
                "           JSON_VALUE (\"PROFILE_DOCUMENT\",\n" +
                "                       '$.customerInfo.categoryInfo.eidNumber'\n" +
                "                       RETURNING NUMBER (15, 0)\n" +
                "                       NULL ON ERROR)\n" +
                "               EID_NUMBER,\n" +
                "           JSON_VALUE (VELI.PRODUCT_DOCUMENT,\n" +
                "                       '$.vehicleLicenseInfo.expiryDate'\n" +
                "                       RETURNING VARCHAR2 (200)\n" +
                "                       NULL ON ERROR)\n" +
                "               EXPIRY_DATE,\n" +
                "           JSON_VALUE (\n" +
                "               VELI.PRODUCT_DOCUMENT,\n" +
                "               '$.vehicleLicenseInfo.plateSummaryInfo.plateNumberDetails.plateNumber'\n" +
                "               RETURNING VARCHAR2 (200)\n" +
                "               NULL ON ERROR)\n" +
                "               Plate,\n" +
                "           JSON_VALUE (\n" +
                "               VELI.PRODUCT_DOCUMENT,\n" +
                "               '$.vehicleLicenseInfo.plateSummaryInfo.plateNumberDetails.plateCategory.plateCode.code'\n" +
                "               RETURNING VARCHAR2 (200)\n" +
                "               NULL ON ERROR)\n" +
                "               Code,\n" +
                "           INPR.ID\n" +
                "               RTA_ID,\n" +
                "           JSON_VALUE (VELI.PRODUCT_DOCUMENT,\n" +
                "                       '$.vehicleLicenseInfo.vehicleSummaryInfo.class.code')\n" +
                "               VCL_TYPE\n" +
                "               ,\n" +
                "          JSON_VALUE (INPR.PROFILE_DOCUMENT,\n" +
                "                      '$.customerInfo.categoryInfo.eidExpiryDate'\n" +
                "                      RETURNING VARCHAR2 (200)\n" +
                "                       NULL ON ERROR)\n" +
                "               EID_EXPIRY_DATE\n" +
                "\n" +
                "                                                     --,VCL_TYPE\n" +
                "      FROM LS_UAA.UM_INDIVIDUAL_PROFILE             INPR,\n" +
                "           VLS_VEHICLE.REP_VEHICLE                  VEHE,\n" +
                "           VLS_VEHICLE_LICENSE.PRD_VEHICLE_LICENSE  VELI\n" +
                "     WHERE     JSON_VALUE (INPR.PROFILE_DOCUMENT,\n" +
                "                           '$.summaryInfo.rtaUnifiedNo'\n" +
                "                           RETURNING NUMBER (15, 0)\n" +
                "                           NULL ON ERROR) =\n" +
                "               JSON_VALUE (VEHE.PRODUCT_DOCUMENT,\n" +
                "                           '$.customerInfo.rtaUnifiedNo'\n" +
                "                           RETURNING NUMBER (15, 0)\n" +
                "                           NULL ON ERROR)\n" +
                "           AND JSON_VALUE (VEHE.PRODUCT_DOCUMENT,\n" +
                "                           '$.vehicleInfo.vehicleSpecs.chassisNumber'\n" +
                "                           RETURNING VARCHAR2 (200)\n" +
                "                           NULL ON ERROR) =\n" +
                "               JSON_VALUE (\n" +
                "                   VELI.PRODUCT_DOCUMENT,\n" +
                "                   '$.vehicleLicenseInfo.vehicleSummaryInfo.chassisNumber'\n" +
                "                   RETURNING VARCHAR2 (200)\n" +
                "                   NULL ON ERROR)\n" +
                "           --AND TO_DATE(JSON_VALUE(VELI.PRODUCT_DOCUMENT  , '$.vehicleLicenseInfo.expiryDate' RETURNING VARCHAR2(200) NULL ON ERROR),'DD-MM-YYYY') < TRUNC(SYSDATE)\n" +
                "           --AND JSON_VALUE(VEHE.PRODUCT_DOCUMENT  , '$.vehicleInfo.vehicleSpecs.chassisNumber' RETURNING VARCHAR2(200) NULL ON ERROR) = 'JN8AY2NY3G9156793'\n" +
                "           AND TO_DATE (JSON_value (VELI.PRODUCT_DOCUMENT,\n" +
                "                                    '$.vehicleLicenseInfo.expiryDate'\n" +
                "                                    RETURNING VARCHAR2 (200)\n" +
                "                                    NULL ON ERROR),\n" +
                "                        'yyyy-mm-dd') < SYSDATE\n" +
                "           AND NOT EXISTS\n" +
                "                   (SELECT 1\n" +
                "                      FROM VLS_RENEW_VEHICLE.TRN_APPLICATION TRN\n" +
                "                     WHERE     JSON_VALUE (INPR.PROFILE_DOCUMENT,\n" +
                "                                           '$.summaryInfo.rtaUnifiedNo'\n" +
                "                                           RETURNING NUMBER (15, 0)\n" +
                "                                           NULL ON ERROR) =\n" +
                "                               JSON_VALUE (TRN.CUSTOMER_INFO,\n" +
                "                                           '$.rtaUnifiedNo'\n" +
                "                                           RETURNING VARCHAR2 (200)\n" +
                "                                           NULL ON ERROR)\n" +
                "                           AND STATUS NOT IN ('COMPLETED'))\n" +
                "\n" +
                "                          AND NOT EXISTS (SELECT 1\n" +
                "\n" +
                "  FROM VLS_SYNC.SYNC_INSPECTION SYIN,\n" +
                "       TRAFFIC.TF_STP_VEHICLE_TESTS VHT\n" +
                " WHERE VHT.ID = SYIN.ENTITY_ID\n" +
                "   AND VHT.CHASISS_NO =  JSON_VALUE (VEHE.PRODUCT_DOCUMENT ,\n" +
                "                     '$.vehicleInfo.vehicleSpecs.chassisNumber'\n" +
                "                     RETURNING VARCHAR2 (200)\n" +
                "                     NULL ON ERROR)\n" +
                "   AND SYIN.STATUS <> 'SYNCED_TO_DESTINATION'\n" +
                "\n" +
                ")\n" +
                "AND NOT EXISTS (SELECT 1\n" +
                "\n" +
                "  FROM VLS_SYNC.SYNC_INSURANCE_POLICY SYIP,\n" +
                "       TRAFFIC.TF_VHL_ELECTRONIC_INSURANCES EIR\n" +
                " WHERE EIR.ID = SYIP.ENTITY_ID\n" +
                "   AND EIR.CHASISS_NO = JSON_VALUE (VEHE.PRODUCT_DOCUMENT ,\n" +
                "                     '$.vehicleInfo.vehicleSpecs.chassisNumber'\n" +
                "                     RETURNING VARCHAR2 (200)\n" +
                "                     NULL ON ERROR)\n" +
                "   AND SYIP.STATUS <> 'SYNCED_TO_DESTINATION')\n" +
                "\n" +
                "   AND NOT EXISTS ( SELECT 1\n" +
                "\n" +
                "  FROM VLS_SYNC.SYNC_MORTGAGE SYMO,\n" +
                "       TRAFFIC.TF_VHL_BOOKLETS BKT\n" +
                " WHERE BKT.ID = SYMO.ENTITY_ID\n" +
                "   AND BKT.CHASISS_NO = JSON_VALUE (VEHE.PRODUCT_DOCUMENT ,\n" +
                "                     '$.vehicleInfo.vehicleSpecs.chassisNumber'\n" +
                "                     RETURNING VARCHAR2 (200)\n" +
                "                     NULL ON ERROR)\n" +
                "   AND SYMO.SOURCE_TABLE = 'BOOKLET'\n" +
                "   AND SYMO.STATUS <> 'SYNCED_TO_DESTINATION'\n" +
                ")\n" +
                " AND NOT EXISTS ( SELECT 1\n" +
                "\n" +
                "  FROM VLS_SYNC.SYNC_MORTGAGE SYMO,\n" +
                "       TRAFFIC.TF_VHL_ELECTRONIC_MORTGAGES VEM\n" +
                " WHERE VEM.ID = SYMO.ENTITY_ID\n" +
                "   AND VEM.CHASSIS_Number = JSON_VALUE (VEHE.PRODUCT_DOCUMENT ,\n" +
                "                     '$.vehicleInfo.vehicleSpecs.chassisNumber'\n" +
                "                     RETURNING VARCHAR2 (200)\n" +
                "                     NULL ON ERROR)\n" +
                "   AND SYMO.SOURCE_TABLE = 'MORTGAGE'\n" +
                "   AND SYMO.STATUS <> 'SYNCED_TO_DESTINATION'\n" +
                ")\n" +
                "\n" +
                "\n" +
                "\n" +
                "           AND NOT EXISTS\n" +
                "                   (SELECT 1\n" +
                "                      FROM VLS_SYNC.SYNC_VEHICLE    SYVE,\n" +
                "                           TRAFFIC.TF_VHL_VEHICLES  VLE\n" +
                "                     WHERE     VLE.ID = SYVE.ENTITY_ID\n" +
                "                           AND VLE.CHASISS_NO =\n" +
                "                               JSON_VALUE (\n" +
                "                                   VEHE.PRODUCT_DOCUMENT,\n" +
                "                                   '$.vehicleInfo.vehicleSpecs.chassisNumber'\n" +
                "                                   RETURNING VARCHAR2 (200)\n" +
                "                                   NULL ON ERROR)\n" +
                "                           AND SYVE.STATUS <> 'SYNCED_TO_DESTINATION')\n" +
                "           AND NOT EXISTS\n" +
                "                   (SELECT 1\n" +
                "                      FROM VLS_SYNC.SYNC_VEHICLE_LICENSE  SYVL,\n" +
                "                           TRAFFIC.TF_VHL_BOOKLETS        BKT\n" +
                "                     WHERE     BKT.ID = SYVL.ENTITY_ID\n" +
                "                           AND BKT.CHASISS_NO =\n" +
                "                               JSON_VALUE (\n" +
                "                                   VEHE.PRODUCT_DOCUMENT,\n" +
                "                                   '$.vehicleInfo.vehicleSpecs.chassisNumber'\n" +
                "                                   RETURNING VARCHAR2 (200)\n" +
                "                                   NULL ON ERROR)\n" +
                "                           AND SYVL.STATUS <> 'SYNCED_TO_DESTINATION')\n" +
                "           AND NOT EXISTS\n" +
                "                   (SELECT 1\n" +
                "                      FROM VLS_SYNC.SYNC_MORTGAGE             SYMO,\n" +
                "                           TRAFFIC.TF_VHL_E_MORTGAGE_RELEASE  EMR\n" +
                "                     WHERE     EMR.ID = SYMO.ENTITY_ID\n" +
                "                           AND EMR.CHASSIS_NUMBER =\n" +
                "                               JSON_VALUE (\n" +
                "                                   VEHE.PRODUCT_DOCUMENT,\n" +
                "                                   '$.vehicleInfo.vehicleSpecs.chassisNumber'\n" +
                "                                   RETURNING VARCHAR2 (200)\n" +
                "                                   NULL ON ERROR)\n" +
                "                           AND SYMO.SOURCE_TABLE = 'RELEASE_MORTGAGE'\n" +
                "                           AND SYMO.STATUS <> 'SYNCED_TO_DESTINATION')\n" +
                "\n" +
                "\n" +
                "\n" +
                "/*Fetch first 1 rows only;*/\n" +
                " AND ROWNUM < 2");

        try {
            vehicle[0] = result.getString(1);
            vehicle[1] = result.getString(2);
            vehicle[2] = result.getString(3);
            vehicle[3] = result.getString(4);
            vehicle[4] = result.getString(5);
            vehicle[5] = result.getString(6);
            vehicle[6] = result.getString(7);
            vehicle[7] = result.getString(8);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Chassis = " + vehicle[0] + "  --- RTA_UNIFIED_NO ID = " + vehicle[1] + "  --- EID_NUMBER ID = " + vehicle[2] + "  --- EXPIRY_DATE ID = " + vehicle[3] +
                "  --- PLATE No = " + vehicle[4] + "  --- Plate Code = " + vehicle[5]+ "  --- RTA ID = " + vehicle[6]+ " --- EID ExpiryDate = " + vehicle[7]);
        return vehicle;
    }


    public String[] getVehicleNotMortgaged(String mortgageStatus) {

        setConnection();
        ResultSet result = databaseActions.executeSelectQuery("SELECT JSON_VALUE(VEHE.PRODUCT_DOCUMENT FORMAT JSON , '$.vehicleInfo.vehicleSpecs.chassisNumber' RETURNING VARCHAR2(200) NULL ON ERROR) CHASSIS_NO,\n" +
                "JSON_VALUE(INPR.PROFILE_DOCUMENT FORMAT JSON , '$.summaryInfo.rtaUnifiedNo' RETURNING NUMBER(15,0) NULL ON ERROR) RTA_UNIFIED_NO,\n" +
                "JSON_VALUE(\"PROFILE_DOCUMENT\" FORMAT JSON , '$.customerInfo.categoryInfo.eidNumber' RETURNING NUMBER(15,0) NULL ON ERROR) EID_NUMBER,\n" +
                "JSON_VALUE(VELI.PRODUCT_DOCUMENT FORMAT JSON , '$.vehicleLicenseInfo.expiryDate' RETURNING VARCHAR2(200) NULL ON ERROR) EXPIRY_DATE,\n" +
                "JSON_VALUE(VELI.PRODUCT_DOCUMENT FORMAT JSON , '$.vehicleLicenseInfo.plateSummaryInfo.plateNumberDetails.plateNumber' RETURNING VARCHAR2(200) NULL ON ERROR) Plate,\n" +
                "JSON_VALUE(VELI.PRODUCT_DOCUMENT FORMAT JSON , '$.vehicleLicenseInfo.plateSummaryInfo.plateNumberDetails.plateCategory.plateCode.code' RETURNING VARCHAR2(200) NULL ON ERROR) Code,\n" +
                "INPR.RTA_ID,\n" +
                "JSON_VALUE(VELI.PRODUCT_DOCUMENT FORMAT JSON , '$.vehicleLicenseInfo.vehicleSummaryInfo.class.code' RETURNING VARCHAR2(200) NULL ON ERROR) VCL_TYPE," +
                "JSON_VALUE(\"PROFILE_DOCUMENT\" FORMAT JSON , '$.customerInfo.categoryInfo.eidExpiryDate' RETURNING VARCHAR2(200) NULL ON ERROR) EID_ExpiryDate\n" +
                "\n" +
                "FROM LS_UAA.UM_INDIVIDUAL_PROFILE INPR,\n" +
                "VLS_VEHICLE.REP_VEHICLE VEHE,\n" +
                "VLS_VEHICLE_LICENSE.PRD_VEHICLE_LICENSE VELI\n" +
                "WHERE JSON_VALUE(INPR.PROFILE_DOCUMENT FORMAT JSON , '$.summaryInfo.rtaUnifiedNo' RETURNING NUMBER(15,0) NULL ON ERROR) =\n" +
                "JSON_VALUE(VEHE.PRODUCT_DOCUMENT FORMAT JSON , '$.customerInfo.rtaUnifiedNo' RETURNING NUMBER(15,0) NULL ON ERROR)\n" +
                "AND JSON_VALUE(VEHE.PRODUCT_DOCUMENT FORMAT JSON , '$.vehicleInfo.vehicleSpecs.chassisNumber' RETURNING VARCHAR2(200) NULL ON ERROR) =\n" +
                "JSON_VALUE(VELI.PRODUCT_DOCUMENT FORMAT JSON , '$.vehicleLicenseInfo.vehicleSummaryInfo.chassisNumber' RETURNING VARCHAR2(200) NULL ON ERROR)\n" +
                "AND JSON_VALUE(VELI.PRODUCT_DOCUMENT FORMAT JSON , '$.vehicleLicenseInfo.mortgage' RETURNING VARCHAR2(200) NULL ON ERROR) = '" + mortgageStatus +"'\n" +
                "\n" +
                "--AND TO_DATE(JSON_VALUE(VELI.PRODUCT_DOCUMENT FORMAT JSON , '$.vehicleLicenseInfo.expiryDate' RETURNING VARCHAR2(200) NULL ON ERROR),'DD-MM-YYYY') < TRUNC(SYSDATE)\n" +
                "--AND JSON_VALUE(VEHE.PRODUCT_DOCUMENT FORMAT JSON , '$.vehicleInfo.vehicleSpecs.chassisNumber' RETURNING VARCHAR2(200) NULL ON ERROR) = '6T1BF4FK3FX530891'\n" +
                "--AND TO_DATE(JSON_value(VELI.PRODUCT_DOCUMENT FORMAT JSON , '$.vehicleLicenseInfo.expiryDate' RETURNING VARCHAR2(200) NULL ON ERROR), 'yyyy-mm-dd') < SYSDATE  \n" +
                "\n" +
                "--AND NOT EXISTS (SELECT 1 FROM VLS_RENEW_VEHICLE.TRN_APPLICATION TRN WHERE JSON_VALUE(INPR.PROFILE_DOCUMENT FORMAT JSON , '$.summaryInfo.rtaUnifiedNo' RETURNING NUMBER(15,0) NULL ON ERROR) = \n" +
                "--JSON_VALUE(TRN.CUSTOMER_INFO FORMAT JSON , '$.rtaUnifiedNo' RETURNING NUMBER(15,0) NULL ON ERROR) \n" +
                "--AND STATUS NOT IN ('COMPLETED') )\n" +
                "AND ROWNUM <= 1");

        try {
            vehicle[0] = result.getString(1);
            vehicle[1] = result.getString(2);
            vehicle[2] = result.getString(3);
            vehicle[3] = result.getString(4);
            vehicle[4] = result.getString(5);
            vehicle[5] = result.getString(6);
            vehicle[6] = result.getString(7);
            vehicle[7] = result.getString(8);
            vehicle[8] = result.getString(9);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Chassis = " + vehicle[0] + "  --- RTA_UNIFIED_NO ID = " + vehicle[1] + "  --- EID_NUMBER ID = " + vehicle[2] + "  --- EXPIRY_DATE ID = " + vehicle[3] +
                "  --- PLATE No = " + vehicle[4] + "  --- Plate Code = " + vehicle[5]+ "  --- RTA ID = " + vehicle[6]+ "  --- VCL TYPE = " + vehicle[7]+ " --- EID ExpiryDate = " + vehicle[8]);
        return vehicle;
    }

    @Step("Update Vehicle License Expiry Date")
    public void updatelicenseexpirydate(String ChassisNo, String numberofdays) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.UPDATE_VEHICLE_EXPIRYDATE ('"+ChassisNo+"', '"+numberofdays+"');END;");
    }

    @Step("Update Vehicle License Future Expiry Date")
    public void updatelicenseexpirydateinFuture(String ChassisNo, String numberofdays) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.UPDATE_VEHICLE_EXPIRYDATE_FUTURE ('"+ChassisNo+"', '"+numberofdays+"');END;");

        System.out.println();
    }

    @Step("Update Vehicle Empity Weight")
    public void updatevehicleempityweight(String ChassisNo, String weight) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.UPDATE_VEHICLE_EMPTY_WEIGHT ('"+ChassisNo+"', '"+weight+"');END;");
    }


    @Step("Update Vehicle Gross Weight")
    public void updatevehiclegrossweight(String ChassisNo, String weight) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.UPDATE_VEHICLE_GROSS_WEIGHT ('"+ChassisNo+"', '"+weight+"');END;");
    }

    @Step("Reset Viloations")
    public void resetviloation(String traffic_no, String chassisNo) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.PKG_ADDING_VIOLATIONS.P_RESET_VIOLATIONS(" + traffic_no + ", '"+chassisNo + "');END;");
    }

    @Step("ADD Electronic Insurance")
    public void addelectronicinsurance(String traffic_no, String ChassisNo) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.Add_SYNC_INSURANCE('"+traffic_no+"', '"+ChassisNo+"');END;");
    }

    @Step("ADD Payable Fines")
    public void addpayablefine(String traffic_no, String ChassisNo) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.PKG_ADDING_VIOLATIONS.P_ADD_FINES(" + traffic_no + ",'" + ChassisNo + "',2,5);END;");
    }

    @Step("ADD Payable Dubai , AbuDhabi And Parking Fines")
    public void addUAEAndGCCFines(String traffic_no, String ChassisNo) {
        setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.PKG_ADDING_VIOLATIONS.P_ADD_FINES(" + traffic_no + ",'" + ChassisNo + "',2,2,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,2,2,2);END;");
    }

    @Step("ADD Payable And Salik Fines")
    public void hasUAEandSalikFines(String traffic_no, String ChassisNo) {
        setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.PKG_ADDING_VIOLATIONS.P_ADD_FINES(" + traffic_no + ",'" + ChassisNo + "',2,2,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1);END;");
    }

    @Step("Cancel application for vehicle renewal")
    public void cancelReferenceApplication(String applicationReferenceNo) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN VLS_RENEW_VEHICLE.P_CANCEL_APPLICATION('" + applicationReferenceNo + "');\nEND;");
    }

    @Step("Add Mortgage on vehicle")
    public void AddMortgage(String traffic_no, String ChassisNo, String status) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.P_ADD_MORTGAGE(" + traffic_no + "," + ChassisNo + ",'" + status + "');END;");
    }

    @Step("Update Insurance Expiry Date")
    public void UpdateInsuranceExpiryDate(String ChassisNo, String numberofdays) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN C_USERS.UPDATE_INSURANCE_EXPIRYDATE ('"+ChassisNo+"', '"+numberofdays+"');END;");

    }

    @Step("Update special Plate Info")
    public void UpdatespecialPlateInfo(String ChassisNo, String Status) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.UPDATE_SPECIAL_PLATES ('"+ChassisNo+"', '"+Status+"');END;");

    }

    @Step("Update Owned Plate Info")
    public void UpdateOwnedlPlateInfo(String ChassisNo, String Status) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.UPDATE_OWNED_PLATES ('"+ChassisNo+"', '"+Status+"');END;");

    }

    @Step("Update Insurance Status")
    public void updateInsuranceStatus(String ChassisNo, String Status) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.UPDATE_INSURANCE_STATUS ('"+ChassisNo+"', '"+Status+"');END;");
    }

    @Step("Update Inspection Status")
    public void updateInspectionStatus(String ChassisNo, String Status) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.INSPECTION_STATUS ('"+ChassisNo+"', '"+Status+"');END;");
    }

    @Step("Update inspection service request")
    public void updateInspectionServiceRequest(String ChassisNo) {
          setConnection();
        databaseActions.executeUpdateQuery("DECLARE\n" +
                "V_PRODUCT_DOCUMENT CLOB;\n" +
                "\n" +
                "V_JJ JSON := json();\n" +
                "BEGIN\n" +
                "ROLLBACK;\n" +
                "SELECT SERVICE_DOCUMENT\n" +
                "INTO V_PRODUCT_DOCUMENT\n" +
                "FROM VLS_INSPECTION.TRN_SERVICE_REQUEST\n" +
                "WHERE JSON_VALUE(SERVICE_DOCUMENT, '$.parameters.transactionInfo.chassisNumber' RETURNING VARCHAR2(200)) in ('"+ChassisNo+"');\n" +
                "\n" +
                "\n" +
                "V_JJ := JSON (V_PRODUCT_DOCUMENT);\n" +
                "json_ext.put( V_JJ ,'parameters.processInfo.code','COMPLETED');\n" +
                "\n" +
                "UPDATE VLS_INSPECTION.TRN_SERVICE_REQUEST\n" +
                "SET SERVICE_DOCUMENT = PLJSON_PRINTER.PRETTY_PRINT1 (V_JJ)\n" +
                "WHERE JSON_VALUE(SERVICE_DOCUMENT, '$.parameters.transactionInfo.chassisNumber' RETURNING VARCHAR2(200)) in ('"+ChassisNo+"');\n" +
                "commit;\n" +
                "END;");
    }

    @Step("Update Application Phase")
    public void updateAppPhase(String AppRefNo) {
          setConnection();
        databaseActions.executeUpdateQuery("Update VLS_RENEW_VEHICLE.TRN_APPLICATION\n" +
                "set ACTIVE_PHASE = 'LICENSING'\n" +
                "where APPLICATION_REF_NO in ('"+AppRefNo+"')");
    }

    @Step("Add Vehicle Inspection From Sync")
    public void addInspectionSync(String ChassisNo) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.ADD_VEHICLE_TEST_SYNC ('"+ChassisNo+"');END;");
    }

    //Blockers Procedures
    @Step("ADDING_UNPAYABLE_FINES")
    public void addunpayablefines(String traffic_no, String ChassisNo) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.Add_UnPayable_Fines(" + traffic_no + ",'" + ChassisNo + "');END;");
    }

    @Step("ADDING_VEHICLE_CONFISCATION")
    public void addVehicleCONFISCATION(String traffic_no, String ChassisNo) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.PKG_ADDING_VIOLATIONS.P_ADDING_VEHICLE_CONFISCATION(" + traffic_no + ",'" + ChassisNo + "',1,3);END;");
    }

    @Step("ADDING_CIRCULAR_NOTE_VLD")
    public void addCircularNoteVLD(String traffic_no, String ChassisNo) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.PKG_ADDING_VIOLATIONS.P_ADDING_CIRCULAR_NOTE_VLD(" + traffic_no + ",'" + ChassisNo + "',1,2);END;");
    }

    @Step("ADDING_CIRCULAR_NOTE_TRF")
    public void addCircularNoteTRF(String traffic_no) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.PKG_ADDING_VIOLATIONS.P_ADDING_CIRCULAR_NOTE_TRF(" + traffic_no + ",1,1);END;");
    }

    @Step("ADDING_CIRCULAR_NOTE_DRL")
    public void addCircularNoteDRL(String traffic_no) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.PKG_ADDING_VIOLATIONS.P_ADDING_CIRCULAR_NOTE_DRL(" + traffic_no + ",1,1);END;");
    }

    @Step("DDING_LICENSE_CONFISCATION")
    public void addLicenseConfiscation(String traffic_no) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.PKG_ADDING_VIOLATIONS.P_ADDING_LICENSE_CONFISCATION(" + traffic_no + ");END;");
    }

    @Step("ADDING_VEHICLE_NOTES")
    public void addVehicleNote(String traffic_no, String ChassisNo) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.PKG_ADDING_VIOLATIONS.P_ADDING_VEHICLE_NOTES(" + traffic_no + ",'" + ChassisNo + "',1,'204');END;");
    }

    @Step("ADDING_BLACK_POINTS")
    public void addBlackPoint(String traffic_no) {
          setConnection();
        databaseActions.executeUpdateQuery("BEGIN QC_USERS.PKG_ADDING_VIOLATIONS.P_BLACK_POINTS(" + traffic_no + ");END;");
    }

    public String getDeclaredVehicleStatus(String DeclareApplicationRefNo , String chassis,String weight  ,String vehicleClassCode, String arName, String enName , String year) {

        String declareStatus = null;
        setConnection();
        ResultSet result = databaseActions.executeSelectQuery("SELECT DISTINCT 'SUCCESS'\n" +
                "  FROM VLS_BUY_NEW_VEHICLE.TRN_APPLICATION          APP,\n" +
                "       VLS_VEHICLE_LICENSE.PRD_VEHICLE_CERTIFICATE  CER,\n" +
                "       VLS_VEHICLE.REP_VEHICLE                      ERP\n" +
                " WHERE     APP.APPLICATION_REF_NO = '"+DeclareApplicationRefNo+"'\n" +
                "       AND ERP.APPLICATION_REF_NO = APP.APPLICATION_REF_NO\n" +
                "       AND JSON_VALUE (APP.APPLICATION_CRITERIA,\n" +
                "                       '$.parameters.chassisNumber') =\n" +
                "           '"+chassis+"'\n" +
                "       AND JSON_VALUE (APP.APPLICATION_CRITERIA,\n" +
                "                       '$.parameters.chassisNumber') =\n" +
                "           JSON_VALUE (ERP.product_document,\n" +
                "                       '$.vehicleInfo.vehicleSpecs.chassisNumber')\n" +
                "       AND JSON_VALUE (APP.APPLICATION_CRITERIA,\n" +
                "                       '$.parameters.chassisNumber') =\n" +
                "           JSON_VALUE (CER.product_document,\n" +
                "                       '$.certificateInfo.vehicleSummaryInfo.chassisNumber')\n" +
                "       AND JSON_VALUE (CER.product_document,\n" +
                "                       '$.certificateInfo.vehicleSummaryInfo.emptyWeight')="+weight+"\n" +
                "       AND JSON_VALUE (CER.product_document,\n" +
                "                       '$.certificateInfo.vehicleSummaryInfo.class.code')='"+vehicleClassCode+"'\n" +
                "       AND JSON_VALUE (CER.product_document,\n" +
                "                       '$.certificateInfo.vehicleSummaryInfo.class.name.ar')='"+arName+"'\n" +
                "       AND JSON_VALUE (CER.product_document,\n" +
                "                       '$.certificateInfo.vehicleSummaryInfo.class.name.en')='"+enName+"'\n" +
                "       AND JSON_VALUE (CER.product_document,\n" +
                "                       '$.certificateInfo.vehicleSummaryInfo.manufacturer.model.year')='"+year+"'\n" +
                "       AND JSON_VALUE (CER.product_document,\n" +
                "                       '$.certificateInfo.vehicleSummaryInfo.declaredBy')='AGENCY'\n" +
                "       AND JSON_VALUE (ERP.product_document,\n" +
                "                       '$.vehicleInfo.statusDetails.value')='DECLARED'" +
                "       AND JSON_VALUE (CER.product_document,\n" +
                "                '$. certificateInfo.status')='ACTIVE'");

        try {
            declareStatus= result.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return declareStatus;

    }


    public String getInsuranceVehicleStatus(String chassisNo,String status, String eID) {

        String declareStatus = null;
        setConnection();
        ResultSet result = databaseActions.executeSelectQuery("SELECT DISTINCT 'SUCCESS'\n" +
                "  FROM VLS_INSURANCE.PRD_INSURANCE_POLICY INS\n" +
                " WHERE JSON_VALUE (INS.product_document, '$.policyInfo.chassisNumber') ='"+chassisNo+"'\n" +
                "       AND JSON_VALUE (INS.product_document, '$.policyInfo.status') ='"+status+"'\n" +
                "       AND JSON_VALUE (INS.product_document, '$.customerInfo.rtaUnifiedNo') ='"+eID+"'");

        try {
            declareStatus= result.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return declareStatus;

    }



    public String getRegisterVehicleStatus(String APPLICATION_REF_NO,String chassisNo, String rtaUnifiedNo,
                                           String weight ,String mortgageStatus ,String vehicleClassCode,
                                           String arName, String enName , String year,String plateCategory,
                                           String logoType,String frontPlateSize ,String BackPlateSize,
                                           String insurancePeriod ,String licensePeriod, String inspectedStatus) {

        String declareStatus = null;
        setConnection();
        ResultSet result = databaseActions.executeSelectQuery("SELECT DISTINCT 'SUCCESS'\n" +
                "  FROM VLS_BUY_NEW_VEHICLE.TRN_APPLICATION          APP,\n" +
                "       VLS_VEHICLE_LICENSE.PRD_VEHICLE_CERTIFICATE  CER,\n" +
                "       VLS_VEHICLE.REP_VEHICLE                      ERP,\n" +
                "       VLS_VEHICLE_LICENSE.PRD_VEHICLE_LICENSE LIC       \n" +
                " WHERE     APP.APPLICATION_REF_NO = '"+APPLICATION_REF_NO+"'\n" +
                "       AND ERP.APPLICATION_REF_NO = APP.APPLICATION_REF_NO\n" +
                "       AND LIC.APPLICATION_REF_NO=APP.APPLICATION_REF_NO\n" +
                "       AND JSON_VALUE (APP.APPLICATION_CRITERIA,'$.parameters.chassisNumber') ='"+chassisNo+"'\n" +
                "       AND JSON_VALUE (APP.APPLICATION_CRITERIA,'$.parameters.chassisNumber') =JSON_VALUE(ERP.product_document,'$.vehicleInfo.vehicleSpecs.chassisNumber')\n" +
                "       AND JSON_VALUE (APP.APPLICATION_CRITERIA,'$.parameters.chassisNumber') =JSON_VALUE(CER.product_document, '$.certificateInfo.vehicleSummaryInfo.chassisNumber')\n" +
                "       AND JSON_VALUE (APP.APPLICATION_CRITERIA,'$.parameters.chassisNumber') =JSON_VALUE(LIC.PRODUCT_DOCUMENT, '$.vehicleLicenseInfo.vehicleSummaryInfo.chassisNumber')\n" +
                "       AND JSON_VALUE (LIC.product_document,'$.customerInfo.rtaUnifiedNo')='"+rtaUnifiedNo+"'\n" +
                "       AND JSON_VALUE (LIC.product_document,'$.vehicleLicenseInfo.vehicleSummaryInfo.emptyWeight')="+weight+"\n" +
                "       AND JSON_VALUE (LIC.product_document,'$.vehicleLicenseInfo.vehicleSummaryInfo.class.code')='"+vehicleClassCode+"'\n" +
                "       AND JSON_VALUE (LIC.product_document,'$.vehicleLicenseInfo.vehicleSummaryInfo.class.name.ar')='"+arName+"'\n" +
                "       AND JSON_VALUE (LIC.product_document,'$.vehicleLicenseInfo.vehicleSummaryInfo.class.name.en')='"+enName+"'\n" +
                "       AND JSON_VALUE (LIC.product_document,'$.vehicleLicenseInfo.vehicleSummaryInfo.manufacturer.model.year')='"+year+"'\n" +
                "       AND JSON_VALUE (LIC.product_document,'$.vehicleLicenseInfo.vehicleSummaryInfo.declaredBy')='AGENCY' \n" +
                "       AND JSON_VALUE (LIC.product_document,'$.vehicleLicenseInfo.mortgage')='"+mortgageStatus+"'  \n" +
                "       AND JSON_VALUE (ERP.product_document,'$.vehicleInfo.statusDetails.value')='LICENSED'\n" +
                "       AND JSON_VALUE (LIC.product_document,'$.vehicleLicenseInfo.plateSummaryInfo.plateNumberDetails.plateCategory.code')='"+plateCategory+"'   \n" +
                "       AND JSON_VALUE (LIC.product_document,'$.vehicleLicenseInfo.plateSummaryInfo.metalPlateDetails.plateLogoType')='"+logoType+"'  \n" +
                "       AND JSON_VALUE (LIC.product_document,'$.vehicleLicenseInfo.plateSummaryInfo.metalPlateDetails.frontPlateSize')='"+frontPlateSize+"'    \n" +
                "       AND JSON_VALUE (LIC.product_document,'$.vehicleLicenseInfo.plateSummaryInfo.metalPlateDetails.backPlateSize')='"+BackPlateSize+"'" +
                "       AND MONTHS_BETWEEN (TO_DATE (JSON_VALUE (LIC.PRODUCT_DOCUMENT,'$.vehicleLicenseInfo.insuranceSummaryInfo.expiryDate'),'YYYY-MM-DD'),\n" +
                "       TO_DATE (JSON_VALUE (LIC.PRODUCT_DOCUMENT,'$.vehicleLicenseInfo.insuranceSummaryInfo.startDate'),'YYYY-MM-DD'))="+insurancePeriod+" " +
                "       AND length (JSON_VALUE(LIC.PRODUCT_DOCUMENT,'$.vehicleLicenseInfo.plateSummaryInfo.plateNumberDetails.plateCategory.plateCode.code'))=1\n" +
                "       AND JSON_VALUE(LIC.PRODUCT_DOCUMENT,'$.vehicleLicenseInfo.plateSummaryInfo.plateNumberDetails.plateNumber') is not null" +
                "       AND MONTHS_BETWEEN (TO_DATE (JSON_VALUE (LIC.PRODUCT_DOCUMENT,'$.vehicleLicenseInfo.expiryDate'),'YYYY-MM-DD')," +
                "       TO_DATE (JSON_VALUE (LIC.PRODUCT_DOCUMENT,'$.vehicleLicenseInfo.issueDate'),'YYYY-MM-DD'))="+licensePeriod+"\n" +
                "       AND JSON_VALUE(LIC.PRODUCT_DOCUMENT,'$.vehicleLicenseInfo.inspected')='"+inspectedStatus+"'");

        try {
            declareStatus= result.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return declareStatus;

    }



    public String[] getRTAUnitfiedIdAndEid(String eIdStatus) {

        setConnection();
        String[] customerDetails = new String[2];

        ResultSet result = databaseActions.executeSelectQuery("SELECT JSON_VALUE (UM.PROFILE_DOCUMENT , '$.summaryInfo.rtaUnifiedNo') rtaUnifiedNo,\n" +
                "       JSON_VALUE (PROFILE_DOCUMENT,'$.customerInfo.categoryInfo.eidNumber') eid\n" +
                "  FROM LS_UAA.UM_INDIVIDUAL_PROFILE UM\n" +
                " WHERE JSON_VALUE (UM.PROFILE_DOCUMENT , '$.customerInfo.categoryInfo.eidExpiryDate' ) "+eIdStatus+" to_char (sysdate+60,'yyyy-mm-dd')\n" +
                " and JSON_VALUE (UM.PROFILE_DOCUMENT , '$.summaryInfo.rtaUnifiedNo') is not null\n" +
                " AND JSON_VALUE (UM.PROFILE_DOCUMENT , '$.customerInfo.categoryInfo.category')='DXB_RESIDENT'\n" +
                " and ROWNUM  =1");

        try {
            customerDetails[0] = result.getString(1);
            customerDetails[1] = result.getString(2);

        } catch (SQLException e) {
            e.printStackTrace();
        }

         return customerDetails;
    }



    public String[] getExpiredVehicle(String vehicle_Class,String morgageStatus,String vehicleWeight,String vehicleLicenseStatus , String profileClassification) {

        setConnection();
        ResultSet result = databaseActions.executeSelectQuery("SELECT JSON_VALUE (VEHE.PRODUCT_DOCUMENT,\n" +
                "                   '$.vehicleInfo.vehicleSpecs.chassisNumber'\n" +
                "                   RETURNING VARCHAR2 (200)\n" +
                "                   NULL ON ERROR)\n" +
                "           CHASSIS_NO,\n" +
                "       JSON_VALUE (INPR.PROFILE_DOCUMENT,\n" +
                "                   '$.summaryInfo.rtaUnifiedNo'\n" +
                "                   RETURNING NUMBER (15, 0)\n" +
                "                   NULL ON ERROR)\n" +
                "           RTA_UNIFIED_NO,\n" +
                "       JSON_VALUE (PROFILE_DOCUMENT,\n" +
                "                   '$.customerInfo.categoryInfo.eidNumber'\n" +
                "                   RETURNING NUMBER (15, 0)\n" +
                "                   NULL ON ERROR)\n" +
                "           EID_NUMBER,\n" +
                "       JSON_VALUE (VELI.PRODUCT_DOCUMENT,\n" +
                "                   '$.vehicleLicenseInfo.expiryDate'\n" +
                "                   RETURNING VARCHAR2 (200)\n" +
                "                   NULL ON ERROR)\n" +
                "           EXPIRY_DATE,\n" +
                "       JSON_VALUE (\n" +
                "           VELI.PRODUCT_DOCUMENT,\n" +
                "           '$.vehicleLicenseInfo.plateSummaryInfo.plateNumberDetails.plateNumber'\n" +
                "           RETURNING VARCHAR2 (200)\n" +
                "           NULL ON ERROR)\n" +
                "           Plate,\n" +
                "       JSON_VALUE (\n" +
                "           VELI.PRODUCT_DOCUMENT,\n" +
                "           '$.vehicleLicenseInfo.plateSummaryInfo.plateNumberDetails.plateCategory.plateCode.code'\n" +
                "           RETURNING VARCHAR2 (200)\n" +
                "           NULL ON ERROR)\n" +
                "           Code,\n" +
                "       INPR.ID\n" +
                "           RTA_ID,\n" +
                "       JSON_VALUE (VELI.PRODUCT_DOCUMENT,\n" +
                "                   '$.vehicleLicenseInfo.vehicleSummaryInfo.class.code')\n" +
                "           VCL_TYPE,\n" +
                "       JSON_VALUE (INPR.PROFILE_DOCUMENT,\n" +
                "                   '$.customerInfo.categoryInfo.eidExpiryDate'\n" +
                "                   RETURNING VARCHAR2 (200)\n" +
                "                   NULL ON ERROR)\n" +
                "           EID_EXPIRY_DATE\n" +
                "  --,VCL_TYPE\n" +
                "  FROM LS_UAA.UM_INDIVIDUAL_PROFILE             INPR,\n" +
                "       VLS_VEHICLE.REP_VEHICLE                  VEHE,\n" +
                "       VLS_VEHICLE_LICENSE.PRD_VEHICLE_LICENSE  VELI\n" +
                " WHERE     JSON_VALUE (INPR.PROFILE_DOCUMENT,\n" +
                "                       '$.summaryInfo.rtaUnifiedNo'\n" +
                "                       RETURNING NUMBER (15, 0)\n" +
                "                       NULL ON ERROR) =\n" +
                "           JSON_VALUE (VEHE.PRODUCT_DOCUMENT,\n" +
                "                       '$.customerInfo.rtaUnifiedNo'\n" +
                "                       RETURNING NUMBER (15, 0)\n" +
                "                       NULL ON ERROR)\n" +
                "       AND JSON_VALUE (VEHE.PRODUCT_DOCUMENT,\n" +
                "                       '$.vehicleInfo.vehicleSpecs.chassisNumber'\n" +
                "                       RETURNING VARCHAR2 (200)\n" +
                "                       NULL ON ERROR) =\n" +
                "           JSON_VALUE (\n" +
                "               VELI.PRODUCT_DOCUMENT,\n" +
                "               '$.vehicleLicenseInfo.vehicleSummaryInfo.chassisNumber'\n" +
                "               RETURNING VARCHAR2 (200)\n" +
                "               NULL ON ERROR)\n" +
                "        AND JSON_VALUE (INPR.PROFILE_DOCUMENT,\n" +
                "                       '$.customerInfo.categoryInfo.category'\n" +
                "                       RETURNING VARCHAR2 (200)\n" +
                "                       NULL ON ERROR)='"+profileClassification+"'\n" +
                "        AND JSON_VALUE (VELI.PRODUCT_DOCUMENT,\n" +
                "                       '$.vehicleLicenseInfo.vehicleSummaryInfo.emptyWeight'\n" +
                "                       RETURNING VARCHAR2 (200)\n" +
                "                       NULL ON ERROR) between 1000 and "+vehicleWeight+" --\n" +
                "        AND JSON_VALUE (VELI.PRODUCT_DOCUMENT,\n" +
                "                       '$.vehicleLicenseInfo.vehicleSummaryInfo.class.code'\n" +
                "                       RETURNING VARCHAR2 (200)\n" +
                "                       NULL ON ERROR)='"+vehicle_Class+"'\n" +
                "        AND JSON_VALUE (VEHE.PRODUCT_DOCUMENT,\n" +
                "AND JSON_VALUE (VELI.PRODUCT_DOCUMENT,\n" +
                "                       '$.vehicleLicenseInfo.mortgage'\n" +
                "                       RETURNING VARCHAR2 (200)\n" +
                "                       NULL ON ERROR)='"+morgageStatus+"'"+
                "                       '$.vehicleInfo.statusDetails.value'\n" +
                "                       RETURNING VARCHAR2 (200)\n" +
                "                       NULL ON ERROR) ='"+vehicleLicenseStatus+"' \n" +
                "       AND TO_DATE (JSON_value (VELI.PRODUCT_DOCUMENT,\n" +
                "                                '$.vehicleLicenseInfo.expiryDate'\n" +
                "                                RETURNING VARCHAR2 (200)\n" +
                "                                NULL ON ERROR),\n" +
                "                    'yyyy-mm-dd') < SYSDATE\n" +
                "       /*Fetch first 1 rows only;*/\n" +
                "--vehicleLicenseStatus is  UNLICENSED or LICENSED or DECLARED"+
                "       AND ROWNUM < 2");

        try {
            vehicle[0] = result.getString(1);
            vehicle[1] = result.getString(2);
            vehicle[2] = result.getString(3);
            vehicle[3] = result.getString(4);
            vehicle[4] = result.getString(5);
            vehicle[5] = result.getString(6);
            vehicle[6] = result.getString(7);
            vehicle[7] = result.getString(8);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Chassis = " + vehicle[0] + "  --- RTA_UNIFIED_NO ID = " + vehicle[1] + "  --- EID_NUMBER ID = " + vehicle[2] + "  --- EXPIRY_DATE ID = " + vehicle[3] +
                "  --- PLATE No = " + vehicle[4] + "  --- Plate Code = " + vehicle[5]+ "  --- RTA ID = " + vehicle[6]+ " --- EID ExpiryDate = " + vehicle[7]);
        return vehicle;
    }

}
