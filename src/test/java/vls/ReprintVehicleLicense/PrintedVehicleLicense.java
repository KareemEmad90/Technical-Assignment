package vls.ReprintVehicleLicense;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.LoadProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.UMS.DxLogin;
import pages.common.ChromeCertificatePage;
import pages.corporate.AddOwnerOrPartnerPage;
import pages.vls.LoginPage;
import pages.vls.sellVehicle.IdentityVerificationPage;
import pages.vls.sellVehicle.VehicleInfoPage;
import pages.vls.sellVehicle.VehicleInspectionPage;

import static com.shaft.driver.DriverFactory.DriverType.DESKTOP_CHROME;

public class PrintedVehicleLicense {
    private WebDriver driver;
    ChromeOptions options;
    DxLogin dxLogin;
    String emiratesIDNumber="13961366";
    String emiratesIDExpiryDate="16/11/1988";

    @BeforeMethod
    public void setup() {
        options = new ChromeOptions();
        options.addArguments("incognito");
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("UMSURL"));
        dxLogin= new DxLogin(driver);

    }

    @Test
    public void TestLogin() throws InterruptedException {
        dxLogin.LoginWithEid(emiratesIDNumber,emiratesIDExpiryDate);
        Thread.sleep(10000);

    }
}
