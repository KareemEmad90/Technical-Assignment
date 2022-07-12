package pages.vls.platesBox;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RenewPlatePage {

    private WebDriver driver ;
    By plateNo= By.xpath("//div[contains(@class, 'GC_plate')]");
    By threeMonthsReservation =By.xpath("//label[@for='threeMonth']");
    By sixMonthsReservation =By.xpath("//label[@for='sixMonth']");
    By TwelveMonthsReservation =By.xpath("//label[@for='twelveMonth']");
    By continueBtn=By.xpath("//button[contains(text(),'Continue')]");
    By payNowBtn=By.xpath("//button[contains(text(),'Pay now')]");
    By downloadPaymentReceipt=By.xpath("//span[contains(text(),'Download payment receipt')]");
    By confirmationMessage=By.xpath("//div[contains(text(),'You have successfully completed')]");
    By backToDashBoard=By.xpath("//button[contains(text(),'Back to Dashboard')]");
    By loadingComponents= By.xpath("//div[@class='smart-loading__body']");

    public RenewPlatePage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyReservationPeriodsDisplayed() throws InterruptedException {
        Thread.sleep(3000);
        ElementActions.waitForElementToBePresent(driver,plateNo,5,true);
        ElementActions.waitForElementToBePresent(driver,threeMonthsReservation,5,true);
        ElementActions.waitForElementToBePresent(driver,sixMonthsReservation,5,true);
        ElementActions.waitForElementToBePresent(driver,TwelveMonthsReservation,5,true);
    }

    public void reserveForSixMonths(){
        ElementActions.click(driver,sixMonthsReservation);
        ElementActions.waitForElementToBePresent(driver,continueBtn,5,true);
        ElementActions.click(driver,continueBtn);
    }

    public void payNow(){
        ElementActions.waitForElementToBePresent(driver,payNowBtn,5,true);
        ElementActions.click(driver,payNowBtn);
    }

    public void confirmPage(){
        ElementActions.waitForElementToBePresent(driver,downloadPaymentReceipt,5,true);
        ElementActions.waitForElementToBePresent(driver,plateNo,5,true);
        ElementActions.isElementDisplayed(driver,confirmationMessage);
        ElementActions.isElementDisplayed(driver,downloadPaymentReceipt);
        ElementActions.isElementDisplayed(driver,plateNo);
        ElementActions.isElementDisplayed(driver,backToDashBoard);
    }


}
