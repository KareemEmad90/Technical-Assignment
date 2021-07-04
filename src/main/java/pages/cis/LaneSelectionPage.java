package pages.cis;

import com.shaft.gui.element.ElementActions;
import io.appium.java_client.internal.ElementMap;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LaneSelectionPage {
    WebDriver driver;
    By startInspectionBtn = By.id("accept-vehicle");
    By startInspectionBtn2 = By.id("insStartInspectionButton1");
    By startInspectionFrame = By.xpath("//*[@id='popupVehicleInfo']/iframe");
    By axlesTxt = By.id("elementsForm:j_idt15:0:j_idt17:tvl");

    By saveAxlesNumber = By.id("elementsForm:doSaveBtn");

    public LaneSelectionPage(WebDriver driver) {
        this.driver = driver;
    }

    private By selectionCar(String carPlate) {
        return By.xpath("//span[@class=' textWrapClass' and (contains(text(),'" + carPlate + "'))]/ancestor::a");
    }

    @Step("Select car from booth")
    public void proceedWithSelectedCar(String carPlate) {
        ElementActions.waitForElementToBePresent(driver, selectionCar(carPlate), 3, true);
        ElementActions.click(driver, selectionCar(carPlate));
    }

    @Step("Start inspection")
    public void startInspection() {
        ElementActions.switchToIframe(driver, startInspectionFrame);
        ElementActions.click(driver, startInspectionBtn);
        ElementActions.click(driver, startInspectionBtn2);
        int count = ElementActions.getElementsCount(driver,axlesTxt,2);
        if (count == 1) {
            ElementActions.type(driver, axlesTxt, "2");
            ElementActions.click(driver, saveAxlesNumber);
        }
    }
}
