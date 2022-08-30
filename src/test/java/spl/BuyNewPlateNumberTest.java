package spl;


import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.LoadProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.Dashboard.Corporate.CorporateDashboardPage;
import pages.Individual.IndividualDashboardPage;
import pages.common.ChromeCertificatePage;
import pages.spl.BuyNewPlateNumberPage;
import java.sql.SQLException;

public class BuyNewPlateNumberTest {
    private WebDriver driver;
    IndividualDashboardPage indDashboard = new IndividualDashboardPage(driver);
    CorporateDashboardPage corDashboard = new CorporateDashboardPage(driver);
    BuyNewPlateNumberPage buyNewPlate = new BuyNewPlateNumberPage();
    ChromeCertificatePage chromeCertificatePage;
    String TrfNo = "13548788";





    @BeforeMethod
    public void beforeTest() throws SQLException, ClassNotFoundException {
        driver = BrowserFactory.getBrowser();
        chromeCertificatePage = new ChromeCertificatePage(driver);
        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("test_Login"));
        chromeCertificatePage.skipUnsafePage();
        corDashboard.sddiLoginByKnownTrafficFile(driver);


    }


    @Step("Buy New Plate Number Vehicle")
    @Test
    public void buyNewPlateNumber() throws  InterruptedException {


        indDashboard = new IndividualDashboardPage(driver);
        indDashboard.clickPlatesBox();
        buyNewPlate.addPlatesNumberToBasket(driver);
        buyNewPlate.viewBasketDetails(driver);
        buyNewPlate.payOnlineForPlates(driver);
        buyNewPlate.downloadDocuments(driver);


    }

    @BeforeTest()
    public void beforeMethod() throws InterruptedException {

    }
}
