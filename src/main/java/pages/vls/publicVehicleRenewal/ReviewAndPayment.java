package pages.vls.publicVehicleRenewal;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.common.ChromeCertificatePage;

public class ReviewAndPayment {
    private WebDriver driver ;



    public ReviewAndPayment(WebDriver driver){this.driver=driver;}

    By DigitalCoptBTN=By.xpath("//label[@for='digitalSelection']");
    By NextBTN=By.xpath("//button[text() = 'Next']");
    By PayNowBTN=By.xpath("//button[text() = 'Pay now']");
    By totalAmount = By.xpath("//*[@id='processing']/div/div/div[1]/div/div[10]/div[2]");




    public void reviewAndPaymentPage() throws InterruptedException {

        ElementActions.click(driver , DigitalCoptBTN);
        ElementActions.click(driver , NextBTN);
        ElementActions.waitForElementToBePresent(driver , totalAmount, 3 ,true);
        ElementActions.click(driver , PayNowBTN);
        ChromeCertificatePage ChromeCertificatePage = new ChromeCertificatePage(driver);
        ChromeCertificatePage.skipUnsafePage();


    }


}


