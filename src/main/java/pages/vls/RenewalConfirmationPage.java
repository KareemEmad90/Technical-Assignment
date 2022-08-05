package pages.vls;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RenewalConfirmationPage {
    WebDriver pdriver ;
    By receiptDownload = By.xpath("//*[@class='downloadBtn'][1]");
    By mulkiaDownload=   By.xpath("//*[@class='downloadBtn'][2]");
    By licenseDownload=   By.xpath("//*[@class='downloadBtn'][3]");

    @Step("Download all products related to renewal service from confirmation page")
    public void downloadProducts(){
        ElementActions.click(pdriver,receiptDownload);
        ElementActions.click(pdriver,mulkiaDownload);
        ElementActions.click(pdriver,licenseDownload);
    }

    public RenewalConfirmationPage(WebDriver driver){
        this.pdriver = driver;
    }
}
