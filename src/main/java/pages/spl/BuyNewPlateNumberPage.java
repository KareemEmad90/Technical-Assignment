package pages.spl;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BuyNewPlateNumberPage {

    private WebDriver driver;
    By buyNewPlateNumberBtn=By.xpath("//button[contains(text(),'Buy New Plate Number')]");
    By viewAllBtn=By.xpath("//*[@id='root']/div/div/div[3]/div/div/div/div[5]/div[1]/div/button");
    //By viewAllBtn=By.xpath("//button[contains(text(),'View all')]");
    By addBasketBtn=By.xpath("/html/body/div/div/div/div[3]/div/div/div/div[2]/div[2]/div[1]/div[2]/div[1]/div/div[4]/button[1]");
    //By addBasketBtn = By.xpath("//button[@id='addToBasket_button_id'][1]");
    By openBasketDetails=By.xpath("//*[@id='root']/div/div/div[3]/div/div/div/div[1]/div/h5[2]");
    By checkoutBtn=By.xpath("//button[contains(text(),'Checkout')]");
    By payNowBtn=By.xpath("//button[contains(text(),'Pay online')]");

    By downloadCertificatesBtb=By.xpath("//button[contains(text(),'Download certificate')]");
    By downloadReceiptsBtb=By.xpath("//button[contains(text(),'Download reciept')]");




    public void addPlatesNumberToBasket(WebDriver driver) throws InterruptedException {

        ElementActions.click(driver,buyNewPlateNumberBtn);
        ElementActions.click(driver,viewAllBtn);
        ElementActions.click(driver,addBasketBtn);
    }

    public void viewBasketDetails(WebDriver driver) throws InterruptedException {

        ElementActions.click(driver,openBasketDetails);

    }
    public void payOnlineForPlates(WebDriver driver) throws InterruptedException {

        Thread.sleep(3000);
        ElementActions.click(driver,checkoutBtn);
        Thread.sleep(3000);
        ElementActions.click(driver,payNowBtn);

    }
    public void downloadDocuments(WebDriver driver) throws InterruptedException {

        Thread.sleep(5000);
        ElementActions.click(driver,downloadCertificatesBtb);
        Thread.sleep(3000);
        ElementActions.click(driver,downloadReceiptsBtb);

    }



}
