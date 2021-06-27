package cis;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import data.LoadProperties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.cis.CisLoginPage;

public class SubmitExportInspection {
    private WebDriver driver;
    CisLoginPage cisLoginPage ;
    @Test
    public void SubmitNewInspection(){
        cisLoginPage = new CisLoginPage(driver);
        cisLoginPage.login("shi_koshis","Qc_123456");
    }

    @BeforeMethod()
    public void beforeMethod() {
        driver = BrowserFactory.getBrowser();

        BrowserActions.navigateToURL(driver, LoadProperties.userData.getProperty("cisURL"),LoadProperties.userData.getProperty("cisURL"));

    }
        @AfterMethod()
        public void tearDown() {
            BrowserActions.closeCurrentWindow(driver);
        }


    }

