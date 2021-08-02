package pages.cis;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransactionSummaryPage {

    WebDriver driver;

    String trafficStatus;

    String paymentStatus;

    By transactionInputFld = By.id("insTrsForm:trsNo:trsNoField");

    By searchTransaction = By.id("insTrsForm:searchBtn");

    By trafficStatusTxt(String transaction){return By.xpath("//table[@id='transactionData']/tbody/tr/td[@data-label='Transaction No.' and (contains(text(),'"+transaction+"'))]/following-sibling::td[@data-label='Traffic Status']");}

    By paymentStatusTxt(String transaction){return By.xpath("//table[@id='transactionData']/tbody/tr/td[@data-label='Transaction No.' and (contains(text(),'"+transaction+"'))]/following-sibling::td[@data-label='Status']");}

    public TransactionSummaryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("user search using transaction number")
    public void searchTransactionNumber(String transaction){
        ElementActions.type(driver,transactionInputFld,transaction);
        ElementActions.click(driver,searchTransaction);
        this.trafficStatus = ElementActions.getText(driver,trafficStatusTxt(transaction));
        this.paymentStatus = ElementActions.getText(driver,paymentStatusTxt(transaction));
    }

    public String getTrafficStatus() {
        return trafficStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}
