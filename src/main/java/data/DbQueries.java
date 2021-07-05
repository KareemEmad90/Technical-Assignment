package data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbQueries {
    DatabaseActionsCustom databaseActionsCustom;

    public String[] getVehicle() {
        String[] vehicle = new String[6];
        databaseActionsCustom = new DatabaseActionsCustom();
        ResultSet result = databaseActionsCustom.executeSelectQuery("SELECT * FROM QC_USERS.GET_EXPIRED_VEHICLE_VLS WHERE rownum < 2");

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

        System.out.println("Chassis = " + vehicle[0] + "  --- RTA_UNIFIED_NO ID = " + vehicle[1] + "  --- EID_NUMBER ID = " + vehicle[2] + "  --- EXPIRY_DATE ID = " + vehicle[3] +
                "  --- PLATE No = " + vehicle[4] + "  --- Plate Code = " + vehicle[5]);

        return vehicle;
    }
}
