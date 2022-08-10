package vls;

import api.AddElectronicInsurance;
import data.DbQueries;
import data.ExcelReader;
import data.LoadProperties;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class PrepareChassisTest {

    String ExcelfileName, sheetname = "CleanChassis";
    int TotalNumberOfCols = 2;
    ExcelReader ER = new ExcelReader();
    @DataProvider(name = "ChassisInfo")
    public Object[][] vehicleData(ITestContext context) throws IOException {
        ExcelfileName = LoadProperties.userData.getProperty("VLSData");
        return ER.getExcelData(ExcelfileName, sheetname, TotalNumberOfCols);
    }

    @Test(dataProvider = "ChassisInfo")
    public void prepareChassis(String rtaUnifiedNo,String chassisNum ) throws SQLException, ClassNotFoundException {
        AddElectronicInsurance insurance= new AddElectronicInsurance();
        insurance.elecInsuranceAPI(rtaUnifiedNo,chassisNum);
        DbQueries dbQueries= new DbQueries();
        //dbQueries.addTest(chassisNum);
        dbQueries.resetviloation(rtaUnifiedNo, chassisNum);
        dbQueries.removeBlocker(rtaUnifiedNo);
    }
}
