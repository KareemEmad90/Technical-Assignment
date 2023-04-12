package pages.sauceDemoPage;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utils.Utils;

public class CheckoutProduct {
    private WebDriver driver ;

    public CheckoutProduct(WebDriver driver) {
        this.driver = driver;
    }
    Utils utils = new Utils(driver);


    By getCheckOutBtn=By.id("checkout");
    By firstNameTxt=By.id("first-name");
    By lastNameTxt=By.id("last-name");
    By postalCode=By.id("postal-code");
    By continueBtn = By.id("continue");
    By finishBtn = By.id("finish");
    By getThanksText = By.xpath("//*[@id='checkout_complete_container']/h2");






    @Step("Check Out Product")
    public void enterCustomerDetails()  {


        ElementActions.click(driver,getCheckOutBtn);
        ElementActions.type(driver, firstNameTxt , "TestFName");
        ElementActions.type(driver, lastNameTxt , "TestLName");
        ElementActions.type(driver, postalCode , "1234");
        ElementActions.click(driver,continueBtn);
        ElementActions.click(driver,finishBtn);

    }

    @Step("Verify Thanks Message")
    public void assertThanksMessage(String msg)  {

        String getMessageFromPage = ElementActions.getText(driver, getThanksText);
        utils.verifyValues(getMessageFromPage, msg);
        System.out.println("Print Message: " + getMessageFromPage);

    }


}
