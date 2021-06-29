package pages.cis;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehicleDetails {
    private WebDriver driver;
    By dispatchBtn = By.id("dispatch-button");
    By mobileTextField = By.id("formId:mobileNo:mobileNoField");
    By saveBtn = By.id("btn-save-id");
    By continueBtn = By.name("insVhlDetailsForm:j_idt777");

    @Step("Click on dispatch button")
    public void clickDispatchBtn() {
        ElementActions.waitForElementToBePresent(driver, dispatchBtn, 10, true);
        ElementActions.click(driver, dispatchBtn);
    }

    @Step("Type mobile number")
    public void typeMobileNo(String mobile) {
        ElementActions.switchToIframe(driver, By.xpath("//div[@id='group-modal']/iframe"));
        ElementActions.waitForElementToBePresent(driver, mobileTextField, 10, true);
        ElementActions.type(driver, mobileTextField, mobile);
    }

    @Step("Click on save button")
    public void clickSaveBtn() {
        ElementActions.click(driver, saveBtn);
        ElementActions.switchToDefaultContent();
    }

    @Step("Click on continue button")
    public void clickContinueBtn() {
        ElementActions.waitForElementToBePresent(driver, continueBtn, 10, true);
        ElementActions.click(driver, continueBtn);
    }

    public VehicleDetails(WebDriver driver) {
        this.driver = driver;
    }
}


//*[@id="element"]/div/div/div[2]/div[1]/div/div/div/input[2]