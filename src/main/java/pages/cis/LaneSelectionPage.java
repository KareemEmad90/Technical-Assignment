package pages.cis;

import com.shaft.gui.element.ElementActions;
import io.appium.java_client.internal.ElementMap;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LaneSelectionPage {
    WebDriver driver;
    String referenceNumber;
    By startInspectionBtn = By.id("accept-vehicle");
    By startInspectionBtn2 = By.xpath("//*[@id='ipCamerasImages']/following-sibling::a");
    By startInspectionFrame = By.xpath("//*[@id='popupVehicleInfo']/iframe");
    By axlesTxt = By.id("elementsForm:j_idt15:0:j_idt17:tvl");
    By saveAxlesNumber = By.id("elementsForm:doSaveBtn");
    By missingInfoCont = By.id("missing-elements-ul-details");
    By WidthTxt = By.id("elementsForm:j_idt15:1:j_idt17:tvl");
    By odometerReadingTxt = By.id("finishForm:odometerNewReadingInputId:odometerNewReadingInputIdField");
    By lengthTxt = By.id("elementsForm:j_idt15:2:j_idt17:tvl");
    By missingInfoLbls = By.xpath("//div[@id='missing-elements-popup']//input[not(contains(@type,'hidden'))]");
    By saveMissingInfo = By.id("elementsForm:doSaveBtn");
    By vehicleInfoIframe = By.xpath("//div[@id='popupVehicleInfo']/iframe");
    By referancNumber = By.xpath("//div[@id='vehicleInfo']//label[text()='Transaction Reference Number']/parent::li");
    By unwantedTextRef = By.xpath("//div[@id='vehicleInfo']//label[text()='Transaction Reference Number']/parent::li/label");

    public LaneSelectionPage(WebDriver driver) {
        this.driver = driver;
    }

    private By selectionCar(String carPlate) {
        return By.xpath("//span[@class=' textWrapClass' and (contains(text(),'" + carPlate + "'))]/ancestor::a");
    }

    @Step("Select car from booth")
    public void proceedWithSelectedCar(String carPlate) throws InterruptedException {
        ElementActions.waitForElementToBePresent(driver, selectionCar(carPlate), 3, true);
        ElementActions.click(driver, selectionCar(carPlate));
        Thread.sleep(2000);
        ElementActions.switchToIframe(driver,vehicleInfoIframe);
        this.referenceNumber = ElementActions.getText(driver,referancNumber).replace(ElementActions.getText(driver,unwantedTextRef),"");
        System.out.println("reference number "+this.referenceNumber);
    }
    public String getReferenceNumber() {
        return referenceNumber;
    }

    @Step("Start inspection")
    public void startInspection() {
        ElementActions.switchToIframe(driver, startInspectionFrame);
        ElementActions.click(driver, startInspectionBtn);
        ElementActions.click(driver, startInspectionBtn2);
        int count = ElementActions.getElementsCount(driver, axlesTxt, 2);
        if (count == 1) {
            ElementActions.type(driver, axlesTxt, "2");
            ElementActions.click(driver, saveAxlesNumber);
        }
    }

    @Step("user start inspection for renwal test")
    public void StartInspectionRenwalTest() {
        //ElementActions.switchToIframe(driver, vehicleInfoIframe);
        ElementActions.click(driver, startInspectionBtn);
        ElementActions.click(driver, startInspectionBtn2);
        if (ElementActions.getElementsCount(driver,odometerReadingTxt,3)==1) {
            System.out.println("no missing info for the vehicle");
            //ElementActions.waitForElementToBePresent(driver, missingInfoCont, 5, true);
        } else {
            List<WebElement> missingFlds = driver.findElements(missingInfoLbls);
            for (int i = 0; i < missingFlds.size(); i++) {
                if (missingFlds.iterator().hasNext()){
                    missingFlds.iterator().next().sendKeys(Integer.valueOf(i+2).toString());
                }
            }
            ElementActions.click(driver,saveMissingInfo);
        }
    }
}
