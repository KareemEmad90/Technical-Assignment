package vls.registerNewVehicle.mainJourney;

import api.AddInsuranceAPI;
import api.DeclareVehicleAPI;
import api.registerNewVehicleAPIs.ApplicationReceiptAPI;
import api.registerNewVehicleAPIs.PayApplicationAPI;
import api.registerNewVehicleAPIs.RegisterNewVehicleAPI;
import com.shaft.api.RestActions;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.DbQueries;
import data.ExcelReader;
import data.LoadProperties;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.common.ChromeCertificatePage;
import pages.vls.LoginPage;
import pages.vls.sellVehicle.RegisterNewVehicleForCorpPage;
import utils.ChassisGeneration;

import java.io.IOException;

import static com.shaft.driver.DriverFactory.DriverType.DESKTOP_CHROME;

public class RegisterNewVehicleJourney {

    private WebDriver driver;
    static Logger log = Logger.getLogger(RegisterNewVehicleJourney.class.getName());


    @Step(" Vehicle Test case")
    @Test()
    public void declareVehicleAPITestCase() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSURL"));
        ChromeCertificatePage ChromeCertificatePage = new ChromeCertificatePage(driver);
        ChromeCertificatePage.skipUnsafePage();
        LoginPage vlsLoginPage = new LoginPage(driver);
        vlsLoginPage.corpLogin("123301","2025/08/20","DED-83");

        RegisterNewVehicleForCorpPage register= new RegisterNewVehicleForCorpPage(driver);
        register.importCertificateDubaiCustoms();
        register.uploadDocuments();
    Thread.sleep(10000);

    }

    @BeforeTest()
    public void beforeMethod() throws InterruptedException {

    }

}
