package pages.cis;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class paymentPage {

    WebDriver driver;

    By paymentMethodDDL = By.id("payCollectFeesForm:paymentMode:paymentModeField");

    By phoneNumBlock = By.id("payCollectFeesForm:newMobile:newMobileField");

    By paymentRecievedBtn = By.id("payCollectFeesForm:continue-button");

    By sendCertfFrame = By.xpath("(//div[@id='modal-dialog']/iframe)[1]");

    By mailFld = By.name("communicateCertificate:newEmail:newEmailField_editableInput");

    By confrimBtn = By.id("communicateCertificate:continue-button");

    public paymentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectPaymentMethod(String PaymentMethod) {
        ElementActions.select(driver, paymentMethodDDL, PaymentMethod);
    }

    public void enterPhoneNum(String phoneNum) {
        ElementActions.type(driver, phoneNumBlock, phoneNum);
    }

    public void clickOnPaymentRecieved() {
        ElementActions.click(driver, paymentRecievedBtn);
    }

    public void typeMailCertifcate(String mail) {
        ElementActions.switchToIframe(driver, sendCertfFrame);
        ElementActions.type(driver, mailFld, mail);
        ElementActions.click(driver, confrimBtn);
    }
}
