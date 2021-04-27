package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChassisGeneration {
    public String ChassisNo (){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
        SimpleDateFormat sorceDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String sourceDate = sorceDateFormat.format(cal.getTime());
        String chassis = sdf.format(cal.getTime()).replace("-", "").replace(":", "") + "00";

        return chassis;
    }
}
