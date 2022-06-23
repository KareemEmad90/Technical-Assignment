package vls.deregistervehicle.mainScenarios;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import com.shaft.validation.Assertions;
import data.DbQueries;
import data.LoadProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import pages.UMS.DxLogin;
import pages.vls.ReturnPlates;
import pages.vls.SelectVehicle;
import pages.vls.PaymentPage;
import utils.DateFormatter;

import java.sql.SQLException;
import java.text.ParseException;

import static com.shaft.driver.DriverFactory.DriverType.DESKTOP_CHROME;

public class DeregistervehicleReservePlates {  DbQueries dbQueries = new DbQueries();
    String eidNUMBER ;
    String rtaUnifiedNumber;
    String eidExpiryDate;
    String chassisNo ;
    private WebDriver driver;

    @Step("DeRegester Vehicle Test case")
    @Test
    public void deregistervehicleTestCase() throws ParseException, SQLException, ClassNotFoundException, InterruptedException {
        DxLogin dxLogin = new DxLogin(driver);
        dxLogin.fillUserEIDInfo(eidNUMBER.substring(3), DateFormatter.dateFormat(eidExpiryDate));
        dxLogin.verifyOTP("correct");
        SelectVehicle vehicleselection = new SelectVehicle(driver);
        vehicleselection.selectvehicle(chassisNo);

        ReturnPlates plateaction = new ReturnPlates(driver);
        plateaction.plateActionReserve();

        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.clickOnPayNowForDeregister();
       // paymentPage.checkTotalAmount("350");
       // Assertions.assertEquals("550",paymentPage.getResult(),"380");
        paymentPage.payUsingRms();
        System.out.println("Done");

    }

    @BeforeTest()
    public void beforeMethod() {
        String[] vehicle = dbQueries.getVehicleNotMortgaged("false");
        eidNUMBER = vehicle[2];
       chassisNo = vehicle[0];
       rtaUnifiedNumber = vehicle[1];
       eidExpiryDate  = vehicle[8];
     //   eidNUMBER = "784198520761689";
     //   chassisNo = "2C3CDZAG4JH276362";
     //   rtaUnifiedNumber = "12595017";
      //  eidExpiryDate  = "2022-01-23";
        dbQueries.resetviloation(rtaUnifiedNumber, chassisNo);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);

        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSDeRegister"));
        //ChromeCertificatePage chromeCertificatePage = new ChromeCertificatePage(driver);
        //chromeCertificatePage.skipUnsafePage();
    }


    @AfterMethod()
    public void tearDown() {
        BrowserActions.closeCurrentWindow(driver);
    }
}
