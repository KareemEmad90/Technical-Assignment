package pages.vls.publicVehicleRenewal;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.common.ChromeCertificatePage;

public class ReviewAndPayment {
    private WebDriver driver ;



    public ReviewAndPayment(WebDriver driver){this.driver=driver;}

    By DigitalCoptBTN=By.xpath("//label[@for='digitalSelection']");
    By NextBTN=By.xpath("//button[text() = 'Next']");
    By PayNowBTN=By.xpath("//button[text() = 'Pay now']");
    By totalAmount = By.xpath("//*[@class='row TFooter']/div[2]");



    @Step("Select digital copy as delivery option")
    public void selectDigitalDelivery(){

        ElementActions.click(driver , DigitalCoptBTN);
        ElementActions.click(driver , NextBTN);
    }

    @Step("Pay journey")
    public void reviewAndPaymentPage() throws InterruptedException {

        ElementActions.waitForElementToBePresent(driver , totalAmount, 3 ,true);
        ElementActions.click(driver , PayNowBTN);
        ChromeCertificatePage ChromeCertificatePage = new ChromeCertificatePage(driver);
        ChromeCertificatePage.skipUnsafePage();


    }


}


