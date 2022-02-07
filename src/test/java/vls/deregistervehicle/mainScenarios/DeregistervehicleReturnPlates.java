package vls.deregistervehicle.mainScenarios;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.DbQueries;
import data.LoadProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.uaa.DxLogin;
import pages.vls.ReturnPlates;
import pages.vls.SelectVehicle;
import pages.vls.PaymentPage;
import utils.DateFormatter;

import java.sql.SQLException;
import java.text.ParseException;

import static com.shaft.driver.DriverFactory.DriverType.DESKTOP_CHROME;

public class DeregistervehicleReturnPlates {
        DbQueries dbQueries = new DbQueries();
        String eidNUMBER ;
        String rtaUnifiedNumber;
        String eidExpiryDate;
        String chassisNo ;
        private WebDriver driver;

        @Step("DeRegester Vehicle Test case")
        @Test
        public void deregistervehicleTestCase() throws ParseException, SQLException, ClassNotFoundException {
            DxLogin dxLogin = new DxLogin(driver);
            dxLogin.fillUserEIDInfo(eidNUMBER.substring(3), DateFormatter.dateFormat(eidExpiryDate));
            dxLogin.verifyOTP("correct");
            SelectVehicle vehicleselection = new SelectVehicle(driver);
            vehicleselection.selectvehicle(chassisNo);

            ReturnPlates plateaction = new ReturnPlates(driver);
            plateaction.plateActionReturnToRTA();

            PaymentPage paymentPage = new PaymentPage(driver);
            paymentPage.clickOnPayNowForDeregister();
            //paymentPage.checkTotalAmount("350");
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
            eidExpiryDate  = vehicle[7];
            dbQueries.resetviloation(rtaUnifiedNumber, chassisNo);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("incognito");
            driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
            BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSDeRegister"), "https://uaa.qa.rta.ae/dx-login");
            //BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSDeRegister"));
            //ChromeCertificatePage chromeCertificatePage = new ChromeCertificatePage(driver);
            //chromeCertificatePage.skipUnsafePage();
        }


        @AfterMethod()
        public void tearDown() {
            //dbQueries.cancelReferenceApplication(applicationRefNumber);
            //BrowserActions.closeCurrentWindow(driver);
        }
}
