package pages.vls;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.element.*;
import com.shaft.validation.*;
import data.DbQueries;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import utils.Utils;

import java.sql.SQLException;

public class PaymentPage {
    private WebDriver driver;
    public String result;

    Utils utils = new Utils(driver);

    DbQueries dbQueries = new DbQueries();
    String flag = dbQueries.PaymentFlag();
    static Logger log = Logger.getLogger(PaymentPage.class.getName());

    By totalAmount = By.xpath("//*[@class=\'row TFooter\']/div[last()]");
    By payNowBtn = By.xpath("//div[@id='processing']//div[contains(@class,'paymentActins')]/button");
    By nonRenewalFeesAmount = By.xpath("//div[contains(@id, 'Non-renewal fines')]/following-sibling::div[contains(@id, 'fee')]");
    By nonRenewalMotorFeesAmount = By.xpath("//div[contains(@id, 'Non-renewal fines fee for motorcycle')]/following-sibling::div[contains(@id, 'fee')]");
    By vehicleNonRenewalFine = By.xpath("//div[contains(@id, 'VEHICLE NON-RENEWAL')]/following-sibling::div[contains(@id, 'fee')]");
    By stickerFee = By.xpath("//div[contains(@id, 'Sticker')]/following-sibling::div[contains(@id, 'fee')]");
    By licenseRenewalFine = By.xpath("//div[contains(@id, 'License renewal')]/following-sibling::div[contains(@id, 'fee')]");
    By knowledgeAndInovationFee = By.xpath("//div[contains(@id, 'Knowledge and innovation fee')]/following-sibling::div[contains(@id, 'fee')]");
    By rmsPayNow = By.name("btnPay");
    By creditCardTextField = By.id("txtCardNo");
    By dateMonth = By.id("ddlMonth");
    By dateYear = By.id("ddlYear");
    By cvvField = By.id("txtCvvNo");
    By rmsPayButton = By.id("payButton");
    By sendAnyWayButton = By.id("proceed-button");
    By deregisterPayNowBtn = By.id("payNowId");
    By renewalPayNoButton = By.xpath("//button[contains(text(),'Pay now')]");
    By transactionNoText = By.xpath("//span[@class='heading__transactionNumber']");
    String getRenewalTransactionNo;

    public PaymentPage(WebDriver driver) throws SQLException, ClassNotFoundException {
        this.driver = driver;
    }

    public void clickOnPayNowForDeregister() {
        ElementActions.click(driver, deregisterPayNowBtn);
    }

    public void clickOnPayNowForRenewal() {
        ElementActions.click(driver, renewalPayNoButton);
    }

    public String getRenewalTransactionNo() {
        ElementActions.waitForElementToBePresent(driver,transactionNoText,5,true);
        String renewalTransactionNo = ElementActions.getText(driver, transactionNoText);
        String[] TransactionNo = renewalTransactionNo.split("#");
        getRenewalTransactionNo = TransactionNo[1];
        return getRenewalTransactionNo;
    }

    public void payUsingRms() {

        if (flag.equals("true")) {
            System.out.println("Flag is true");
          //  ElementActions.click(driver, rmsPayNow);
            ElementActions.type(driver, creditCardTextField, "4111111111111111");
            ElementActions.type(driver, dateMonth, "10");
            ElementActions.type(driver, dateYear, "26");
            ElementActions.type(driver, cvvField, "123");
            ElementActions.click(driver, rmsPayButton);
          //  ElementActions.click(driver, sendAnyWayButton);
            System.out.println("am Here");
        } else {
            System.out.println("----------------Auto Payment done successfully ----------------");
            log.info("=========================Auto Payment done successfully=======================");
        }

    }



    public void assertKnowledgeAndInnovationFeeAmount(String expectedFees) {
        String actualknowledgeAndInovationFeeFine = ElementActions.getText(driver, knowledgeAndInovationFee);
        //Assertions.assertEquals(expectedFees, actualknowledgeAndInovationFeeFine);
        utils.verifyValues(actualknowledgeAndInovationFeeFine, expectedFees);
    }

    public void assertLicenseRenewalFineAmount(String expectedFees) {
        String actuallicenseRenewalFine = ElementActions.getText(driver, licenseRenewalFine);
        //Assertions.assertEquals(expectedFees, actuallicenseRenewalFine);
        utils.verifyValues(actuallicenseRenewalFine, expectedFees);

    }

    public void assertStickerFeeAmount(String expectedFees) {
        String actualStickerFee = ElementActions.getText(driver, stickerFee);
        //Assertions.assertEquals(expectedFees, actualStickerFee);
        utils.verifyValues(actualStickerFee, expectedFees);

    }

    public void assertVehicleNonRenewalFineAmount(String expectedFees) {
        String actualvehicleNonRenewalFine = ElementActions.getText(driver, vehicleNonRenewalFine);
        //Assertions.assertEquals(expectedFees, actualvehicleNonRenewalFine);
        utils.verifyValues(actualvehicleNonRenewalFine, expectedFees);

    }


    public void assertNonRenewalFeesAmount(String expectedFees) {
        String actualNonRenewalFee = ElementActions.getText(driver, nonRenewalFeesAmount);
        //Assertions.assertEquals(expectedFees, actualNonRenewalFee);
        utils.verifyValues(actualNonRenewalFee, expectedFees);

    }

    public void assertVehicleRenewalFine(String expectedFees) {
        String actualNonRenewalFee = ElementActions.getText(driver, nonRenewalFeesAmount);
        //Assertions.assertEquals(expectedFees, actualNonRenewalFee);
        utils.verifyValues(actualNonRenewalFee, expectedFees);
    }

    public void assertMotorRenewalFine(String expectedFees) {
        String actualNonRenewalFee = ElementActions.getText(driver, nonRenewalMotorFeesAmount);
        //Assertions.assertEquals(expectedFees, actualNonRenewalFee);
        utils.verifyValues(actualNonRenewalFee, expectedFees);
    }

    public void clickOnPayNow()
    {
        ElementActions.click(driver, payNowBtn);
    }


    public void checkTotalAmount(String expectedTotalAmount){
        ElementActions.waitForElementToBePresent(driver, totalAmount,5,true);
        result = ElementActions.getText(driver, totalAmount);
        //Assertions.assertEquals(expectedTotalAmount, result,"380");
        utils.verifyValues(result, expectedTotalAmount);

    }
}
