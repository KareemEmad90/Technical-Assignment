package Sprint_8;

import enums.CertificateType;
import enums.SearchVehicleType;
import enums.TestType;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import static org.apache.tika.metadata.XMPRights.CERTIFICATE;

public class US_19476 extends TestSetup {

    @Test(description = "Test Case 26210: Check activity code when user operator attempt to submit new vehicle using Existed Chasis_no and active integration with E-traffic", priority = 1)
    public void TC_26210() {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.ChasisNumber);
        searchVehiclePage.typeChasisNumber(getChassisNumber());
        searchVehiclePage.clickSearchButton();
        softAssert.assertTrue(editVehiclePage.verifyChasisNumberDisplyed(), "CIS not retreiving vehicle details");
        softAssert.assertEquals(getChassisNumber(), editVehiclePage.getChasisNumber());
        softAssert.assertAll();
    }

    @Test(description = "Test Case 26212: Check activity code when user operator attempt to submit new vehicle using NotExisted Chasis_no and active integration with E-traffic", priority = 2)
    public void TC_26212() {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.ChasisNumber);
        searchVehiclePage.typeChasisNumber(invalidChasisNumber);
        searchVehiclePage.clickSearchButton();
        Assert.assertTrue(editVehiclePage.verifyValidationMsg());
    }

    @Test(description = "Test Case 26215: Check activity code when user operator attempt to submit new vehicle using Existed Chasis_no and Inactive integration with E-traffic", priority = 3)
    public void TC_26215() {
        /*
        tbd for cutting connectivity on Etraffic
         */
    }

    @Test(description = "Test Case 26216: Check activity code when user operator attempt to submit new vehicle using Existed Custom Certificate and active integration with E-traffic", priority = 4)
    public void TC_26216() {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.Certificate);
        searchVehiclePage.selectCustomCertificate(docNum, Date);
        searchVehiclePage.clickSearchButton();
        //assertion goes here
    }

    @Test(description = "Test Case 26219: Check activity code when user operator attempt to submit new vehicle using NotExisting Custom Certificate and active integration with E-traffic", priority = 5)
    public void TC_26219() {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.Certificate);
        searchVehiclePage.selectCustomCertificate(NotExisting_docNum, Date);
        searchVehiclePage.clickSearchButton();
        //assertion goes here
    }

    @Test(description = "Test Case 26220: Check activity code when user operator attempt to submit new vehicle using Existing Custom Certificate and Inactive integration with E-traffic", priority = 6)
    public void TC_26220() {
        /*
        tbd for cutting connectivity on Etraffic
         */
    }

    @Test(description = "Test Case 26222: Check activity code when user operator attempt to submit new vehicle using Existed Transfer Certificate and active integration with E-traffic", priority = 7)
    public void TC_26222() {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.Certificate);
        searchVehiclePage.selectCertificate(CertificateType.TransferCertificate, docNum);
        searchVehiclePage.clickSearchButton();
        //assertion goes here
    }

    @Test(description = "Test Case 26224: Check activity code when user operator attempt to submit new vehicle using Not Existed Transfer Certificate and active integration with E-traffic", priority = 8)
    public void TC_26224() {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.Certificate);
        searchVehiclePage.selectCertificate(CertificateType.TransferCertificate, NotExisting_docNum);
        searchVehiclePage.clickSearchButton();
        //assertion goes here
    }

    @Test(description = "Test Case 26228: Check activity code when user operator attempt to submit new vehicle using Existed Transfer Certificate and Inactive integration with E-traffic", priority = 9)
    public void TC_26228() {
        /*
        tbd for cutting connectivity on Etraffic
         */
    }

    @Test(description = "Test Case 26229: Check activity code when user operator attempt to submit new vehicle using Existed Possession Certificate and active integration with E-traffic", priority = 10)
    public void TC_26229() {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.Certificate);
        searchVehiclePage.selectCertificate(CertificateType.PossessionCertificate, docNum);
        searchVehiclePage.clickSearchButton();
        //assertion goes here
    }

    @Test(description = "Test Case 26230: Check activity code when user operator attempt to submit new vehicle using Not Existed Possession Certificate and active integration with E-traffic", priority = 11)
    public void TC_26230() {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.Certificate);
        searchVehiclePage.selectCertificate(CertificateType.PossessionCertificate, NotExisting_docNum);
        searchVehiclePage.clickSearchButton();
        //assertion goes here
    }

    @Test(description = "", priority = 12)
    public void TC_26231() {
         /*
        tbd for cutting connectivity on Etraffic
         */
    }

    @Test(description = "Test Case 26240: Check activity code when user operator attempt to submit new vehicle using Existed Export Certificate and active integration with E-traffic", priority = 13)
    public void TC_26240() {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.Certificate);
        searchVehiclePage.selectCertificate(CertificateType.ExportCertificate, docNum);
        searchVehiclePage.clickSearchButton();
        //assertion goes here
    }

    @Test(description = "Test Case 26241: Check activity code when user operator attempt to submit new vehicle using Not Existed Export Certificate and active integration with E-traffic", priority = 14)
    public void TC_26241() {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.Certificate);
        searchVehiclePage.selectCertificate(CertificateType.ExportCertificate, NotExisting_docNum);
        searchVehiclePage.clickSearchButton();
        //assertion goes here
    }

    @Test(description = "Test Case 26242: Check activity code when user operator attempt to submit new vehicle using Existed Export Certificate and Inactive integration with E-traffic", priority = 15)
    public void TC_26242() {
         /*
        tbd for cutting connectivity on Etraffic
         */
    }

    @Test(description = "Test Case 26337: Check activity code when user operator attempt to submit new vehicle using Valid Plate Number(Dubai) and active connection with Etraffic", priority = 16)
    @Parameters({"Emirate"})
    public void TC_26337(@Optional("Dubai") String Emirate) {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.PlateNumber);
        searchVehiclePage.selectEmirate(Emirate);
        searchVehiclePage.TypePlateNumber(getPlateNo());
        searchVehiclePage.ClickOnSearchForPlateNum();
        //validation goes here
    }

    @Test(description = "Test Case 26338: Check activity code when user operator attempt to submit new vehicle using Valid Plate Number(Sharjah) and active connection with Etraffic", priority = 17)
    @Parameters({"Emirate"})
    public void TC_26338(@Optional("Sharjah") String Emirate) {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.PlateNumber);
        searchVehiclePage.selectEmirate(Emirate);
        searchVehiclePage.TypePlateNumber(getPlateNo());
        searchVehiclePage.ClickOnSearchForPlateNum();
        //validation goes here
    }

    @Test(description = "Test Case 26338: Check activity code when user operator attempt to submit new vehicle using Valid Plate Number(Sharjah) and active connection with Etraffic", priority = 18)
    @Parameters({"Emirate"})
    public void TC_26339(@Optional("Abu Dhabi") String Emirate) {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.PlateNumber);
        searchVehiclePage.selectEmirate(Emirate);
        searchVehiclePage.TypePlateNumber(getPlateNo());
        searchVehiclePage.ClickOnSearchForPlateNum();
        //validation goes here
    }

    @Test(description = "Test Case 26345: Check activity code when user operator attempt to submit new vehicle using InValid Plate Number(Dubai) and active connection with Etraffic", priority = 19)
    @Parameters({"Emirate", "PlateNumber"})
    public void TC_26345(@Optional("Dubai") String Emirate, @Optional("121212121") String PlateNumber) {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.PlateNumber);
        searchVehiclePage.selectEmirate(Emirate);
        searchVehiclePage.TypePlateNumber(PlateNumber);
        searchVehiclePage.ClickOnSearchForPlateNum();
        //validation goes here
    }

    @Test(description = "Test Case 26344: Check activity code when user operator attempt to submit new vehicle using InValid Plate Number(Sharjah) and active connection with Etraffic", priority = 20)
    @Parameters({"Emirate", "PlateNumber"})
    public void TC_26344(@Optional("Sharjah") String Emirate, @Optional("12121212") String PlateNumber) {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.PlateNumber);
        searchVehiclePage.selectEmirate(Emirate);
        searchVehiclePage.TypePlateNumber(PlateNumber);
        searchVehiclePage.ClickOnSearchForPlateNum();
        //validation goes here
    }

    @Test(description = "Test Case 26340: Check activity code when user operator attempt to submit new vehicle using InValid Plate Number(Sharjah) and active connection with Etraffic", priority = 21)
    @Parameters({"Emirate", "PlateNumber"})
    public void TC_26340(@Optional("Abu Dhabi") String Emirate, @Optional("12121212") String PlateNumber) {
        cisLoginPage.login(userName, passWrd);
        cisHomePage.supervisorAction(newInspection);
        newVehicleInspectionPage.SelectTestType(TestType.ExportTest);
        searchVehiclePage.selectSearchType(SearchVehicleType.PlateNumber);
        searchVehiclePage.selectEmirate(Emirate);
        searchVehiclePage.TypePlateNumber(PlateNumber);
        searchVehiclePage.ClickOnSearchForPlateNum();
        //validation goes here
    }

    @Test(description = "Test Case 26343: Check activity code when user operator attempt to submit new vehicle using InValid Plate Number(Sharjah) and Inactive connection with Etraffic", priority = 22)
    @Parameters({"Emirate", "PlateNumber"})
    public void TC_26343(@Optional("Sharjah") String Emirate, @Optional("12121212") String PlateNumber) {
         /*
        tbd for cutting connectivity on Etraffic
         */
    }

    @Test(description = "Test Case 26340: Check activity code when user operator attempt to submit new vehicle using InValid Plate Number(Dubai) and active connection with Etraffic", priority = 23)
    @Parameters({"Emirate", "PlateNumber"})
    public void TC_26342(@Optional("Dubai") String Emirate, @Optional("12121212") String PlateNumber) {
         /*
        tbd for cutting connectivity on Etraffic
         */
    }

    @Test(description = "Test Case 26340: Check activity code when user operator attempt to submit new vehicle using InValid Plate Number(Sharjah) and active connection with Etraffic", priority = 24)
    @Parameters({"Emirate", "PlateNumber"})
    public void TC_26341(@Optional("Abu Dhabi") String Emirate, @Optional("12121212") String PlateNumber) {
         /*
        tbd for cutting connectivity on Etraffic
         */
    }
}
