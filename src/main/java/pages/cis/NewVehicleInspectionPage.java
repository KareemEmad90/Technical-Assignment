package pages.cis;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewVehicleInspectionPage {
    private WebDriver driver ;

    By renewalTestService = By.xpath("//*[@id=\"insSlctSrvForm:j_idt799:0:j_idt800\"]/span[text()='Renewal Test']");
    By exportTestService = By.xpath("//*[@id=\"insSlctSrvForm:j_idt799:3:j_idt800\"]/span[text()='Export Test']");
    By continueBtn = By.xpath("//*[@id=\"insSlctSrvForm:proceedBtnDivId\"]/div[1]/div/a[1]");

    public NewVehicleInspectionPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Unselect renewal test service")
    public void unselectRenewalTestService() {
        ElementActions.waitForElementToBePresent(driver, renewalTestService, 3, true);
        ElementActions.click(driver, renewalTestService);
    }

    @Step("select export test")
    public void selectExportTest() throws InterruptedException {
        Thread.sleep(5000);
        ElementActions.click(driver, exportTestService);
    }

    @Step("Click continue button")
    public void clickContinue() {
        ElementActions.click(driver, continueBtn);
    }
}


