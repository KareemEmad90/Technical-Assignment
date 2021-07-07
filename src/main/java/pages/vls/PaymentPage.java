package pages.vls;

import com.shaft.gui.element.*;
import com.shaft.validation.*;
import org.openqa.selenium.*;

public class PaymentPage {

    By totalAmount = By.xpath("//*[@id='processing']/div/div/div[1]/div/div[6]/div[2]");
    By payNowBtn = By.xpath("//button[text() = 'Pay now']");

    private WebDriver driver;
    String result;
    public void payment()
    {
        ElementActions.click(driver,payNowBtn);
    }

    public String getResult() {
        return result;
    }

    public void checkTotalAmount(){
        ElementActions.waitForElementToBePresent(driver,totalAmount,5,true);
        this.result = ElementActions.getText(driver,totalAmount);
    }

    public PaymentPage(WebDriver driver){
        this.driver = driver ;
    }
}
