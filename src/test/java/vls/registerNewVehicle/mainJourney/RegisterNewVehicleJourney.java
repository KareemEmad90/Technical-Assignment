package vls.registerNewVehicle.mainJourney;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.LoadProperties;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.common.ChromeCertificatePage;
import pages.vls.LoginPage;
import pages.vls.sellVehicle.VehicleInfoPage;

import static com.shaft.driver.DriverFactory.DriverType.DESKTOP_CHROME;

public class RegisterNewVehicleJourney {

    private WebDriver driver;
    static Logger log = Logger.getLogger(RegisterNewVehicleJourney.class.getName());
    ChromeOptions options;
    LoginPage vlsLoginPage;
    VehicleInfoPage register;

    @BeforeMethod
    public void setup() {
        options = new ChromeOptions();
        options.addArguments("incognito");
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("VLSURL"));
        ChromeCertificatePage ChromeCertificatePage = new ChromeCertificatePage(driver);
        ChromeCertificatePage.skipUnsafePage();
        vlsLoginPage = new LoginPage(driver);
        register = new VehicleInfoPage(driver);
    }

    @Step(" Vehicle Test case")
    @Test()
    public void declareVehicleAPITestCase() throws InterruptedException {
        vlsLoginPage.corpLogin("123301", "2025/08/20", "DED-83");
        register.importCertificateDubaiCustoms();
//        register.uploadDocuments();
//        Thread.sleep(10000);
    }




}
