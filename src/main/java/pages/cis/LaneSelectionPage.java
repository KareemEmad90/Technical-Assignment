package pages.cis;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LaneSelectionPage {
    WebDriver driver;

    public LaneSelectionPage(WebDriver driver) {
        this.driver = driver;
    }

    private By selectionCar(String carPlate){return By.xpath("//span[@class=' textWrapClass' and (contains(text(),'"+carPlate+"'))]/ancestor::a");}

    @Step
    public void proceedWithSelectedCar(String carPlate){
        ElementActions.waitForElementToBePresent(driver,selectionCar(carPlate),3,true);
        ElementActions.click(driver,selectionCar(carPlate));
    }
}
