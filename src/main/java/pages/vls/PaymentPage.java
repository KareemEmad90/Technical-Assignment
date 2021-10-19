package pages.vls;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.element.*;
import com.shaft.validation.*;
import org.openqa.selenium.*;
import utils.Utils;

public class PaymentPage {
    private WebDriver driver;
    public String result;
    Utils utils = new Utils(driver);

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

    public PaymentPage(WebDriver driver){
        this.driver = driver ;
    }

    public void clickOnPayNowForDeregister() {
        ElementActions.click(driver, deregisterPayNowBtn);
    }

    public void payUsingRms() {
        BrowserActions.refreshCurrentPage(driver);
        ElementActions.click(driver, rmsPayNow);
        BrowserActions.refreshCurrentPage(driver);
        ElementActions.type(driver, creditCardTextField, "4111111111111111");
        ElementActions.type(driver, dateMonth, "10");
        ElementActions.type(driver, dateYear, "26");
        ElementActions.type(driver, cvvField, "123");
        ElementActions.click(driver, rmsPayButton);
        ElementActions.click(driver, sendAnyWayButton);
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
