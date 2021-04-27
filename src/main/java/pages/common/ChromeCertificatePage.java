package pages.common;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChromeCertificatePage  {

    By advancedBtn = By.id("details-button");
    By proccessBtn = By.id("proceed-link");
    private  WebDriver driver ;
    @Step("Go to skip unsafe page")
    public void skipUnsafePage()  {
        boolean skipCertificate = ElementActions.isElementDisplayed(driver,advancedBtn);

        if (skipCertificate == true) {
            ElementActions.click(driver,advancedBtn);
            ElementActions.click(driver,proccessBtn);
        }

    }
    public ChromeCertificatePage(WebDriver driver) {
        this.driver = driver;
    }
}
