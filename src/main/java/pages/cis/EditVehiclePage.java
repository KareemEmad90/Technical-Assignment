package pages.cis;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditVehiclePage {
    private WebDriver driver;
    By continueBtn = By.id("formId:btn-continue");


    @Step("Click on continue button")
    public void clickContinueBtn() {
        ElementActions.waitForElementToBePresent(driver, continueBtn, 10, true);
        ElementActions.click(driver, continueBtn);
    }

    public EditVehiclePage(WebDriver driver) {
        this.driver = driver;
    }
}
