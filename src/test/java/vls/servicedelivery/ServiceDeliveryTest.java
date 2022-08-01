package vls.servicedelivery;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.LoadProperties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Dashboard.Corporate.CorporateDashboardPage;
import pages.Individual.IndividualDashboardPage;
import pages.common.ChromeCertificatePage;
import pages.vls.VehiclesPage;

public class ServiceDeliveryTest {
    WebDriver driver;
    CorporateDashboardPage corporateDashboardPage;
    ChromeCertificatePage chromeCertificatePage;
    IndividualDashboardPage individualDashboardPage;
    VehiclesPage vehiclesPage ;

    @BeforeMethod
    public void beforeTest(){
        driver = BrowserFactory.getBrowser();
        chromeCertificatePage = new ChromeCertificatePage(driver);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("test_Login"));
        corporateDashboardPage = new CorporateDashboardPage(driver);
        individualDashboardPage = new IndividualDashboardPage(driver);
        chromeCertificatePage.skipUnsafePage();
        corporateDashboardPage.sddiLogin("10351032");
        individualDashboardPage.clickVehiclesBox();
        vehiclesPage.searchForVehicle("WMWZC5106DWP01958");
        vehiclesPage.selectVehicle();


    }
    @Test
    public void standardDeliveryTest(){

    }


    @AfterMethod
    public void afterMethod() {
        BrowserActions.closeCurrentWindow(driver);
    }

}
