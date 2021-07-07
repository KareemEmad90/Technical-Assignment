package vls;

import cis.*;
import com.shaft.gui.browser.*;
import com.shaft.validation.*;
import data.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import pages.cis.*;
import pages.common.*;
import pages.vls.LoginPage;
import pages.vls.PaymentPage;

public class Persona1  extends SubmitExportInspection {

    String Expected = "Success";
    DbQueries dbQueries;
    String EID_NUMBER;
    String Chassis_no;
    private WebDriver driver;


    @Test
    public void Persona1TestCase() throws InterruptedException {

        LoginPage vlsLoginPage = new LoginPage(driver);
        vlsLoginPage.login(EID_NUMBER);
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.checkTotalAmount();
        Assertions.assertEquals(Expected,paymentPage.getResult(),"380");
        paymentPage.payment();

    }

    @BeforeTest()
    public void CisInspection()throws InterruptedException {
        SubmitExportInspection submitExportInspection = new SubmitExportInspection();
        submitExportInspection.beforeMethod();
        submitExportInspection.SubmitNewInspection();
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSURL"));
        String[] vehicle = dbQueries.getVehicle();
        EID_NUMBER = vehicle[2].substring(3);
        Chassis_no = vehicle[0];
        dbQueries.updatelicenseexpirydate(Chassis_no, "90");

    }


    @AfterMethod()
    public void tearDown() {
        //BrowserActions.closeCurrentWindow(driver);
    }
}

