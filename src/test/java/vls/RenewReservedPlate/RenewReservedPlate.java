package vls.RenewReservedPlate;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.LoadProperties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Individual.IndividualDashboardPage;
import pages.UMS.DashBoard;
import pages.common.ChromeCertificatePage;
import pages.common.OnlineLogin;
import pages.vls.platesBox.PlateMainPage;
import pages.vls.platesBox.RenewPlatePage;

import static com.shaft.driver.DriverFactory.DriverType.DESKTOP_CHROME;

public class RenewReservedPlate {

    private WebDriver driver;
    static Logger log = Logger.getLogger(RenewReservedPlate.class.getName());
    ChromeOptions options;
    OnlineLogin onlineLogin;
    IndividualDashboardPage individualDashboardPage;
    RenewPlatePage renewPlatePage;
    PlateMainPage plateMainPage;
    String trfNo="13961366";
    String plateNo="90387";

    @BeforeMethod
    public void setup() {
        options = new  ChromeOptions();
        options.addArguments("incognito");
        driver = BrowserFactory.getBrowser(DESKTOP_CHROME, options);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("test_Login"));
        ChromeCertificatePage ChromeCertificatePage = new ChromeCertificatePage(driver);
        ChromeCertificatePage.skipUnsafePage();
        onlineLogin = new OnlineLogin(driver);
        onlineLogin.login(trfNo);

    }

    @Test
    public void renewReservedPlate() throws InterruptedException {
        individualDashboardPage= new IndividualDashboardPage(driver);
        plateMainPage = new PlateMainPage(driver);
        renewPlatePage= new RenewPlatePage(driver);

        individualDashboardPage.verifyBoxsDisplayed();
        individualDashboardPage.clickPlatesBox();

        plateMainPage.verifySwitchDisplayed();
        plateMainPage.switchOwnedOff();
        plateMainPage.switchOnVehicleOff();
        plateMainPage.selectReservedPlate();
        plateMainPage.renewPlateLinkAction();

        renewPlatePage.verifyReservationPeriodsDisplayed();
        renewPlatePage.reserveForSixMonths();
        renewPlatePage.payNow();
        renewPlatePage.confirmPage();

    }


}
