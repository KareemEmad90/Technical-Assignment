package Sprint_8;

import enums.SearchVehicleType;
import enums.TestType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class US_19476 extends TestSetup {

    @Test(description = "Test Case 26210: Check activity code when user operator attempt to submit new vehicle using Existed Chasis_no and active integration with E-traffic", priority = 1)
    public void TC1() {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.ChasisNumber);
        searchVehiclePage.typeChasisNumber(getChassisNumber());
        searchVehiclePage.clickSearchButton();
        softAssert.assertTrue(editVehiclePage.verifyChasisNumberDisplyed(),"CIS not retreiving vehicle details");
        softAssert.assertEquals(getChassisNumber(), editVehiclePage.getChasisNumber());
        softAssert.assertAll();
    }

    @Test(description = "Test Case 26212: Check activity code when user operator attempt to submit new vehicle using NotExisted Chasis_no and active integration with E-traffic",priority = 2)
    public void TC2() {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.ChasisNumber);
        searchVehiclePage.typeChasisNumber(invalidChasisNumber);
        searchVehiclePage.clickSearchButton();
        Assert.assertTrue(editVehiclePage.verifyValidationMsg());
    }



}
