package pages.cis;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehicleDetails {
    private WebDriver driver;

    private String plateNumber;

    By dispatchBtn = By.id("dispatch-button");
    By mobileTextField = By.id("formId:mobileNo:mobileNoField");
    By saveBtn = By.id("btn-save-id");
    By ProceedWithMobile = By.id("formId:init-jrny-button");
    By continueBtn = By.name("insVhlDetailsForm:j_idt777");

    By modelNumber = By.xpath("//label[@for='chassis-no' and (contains(text(),'Plate Details'))]/following-sibling::div/p");

    @Step("Click on dispatch button")
    public void clickDispatchBtn() {
        ElementActions.waitForElementToBePresent(driver, dispatchBtn, 10, true);
        ElementActions.click(driver, dispatchBtn);
    }
    public VehicleDetails(WebDriver driver) {
        this.driver = driver;
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
        getmodelnumber();
        ElementActions.waitForElementToBePresent(driver, continueBtn, 10, true);
        ElementActions.click(driver, continueBtn);
    }


    public void getmodelnumber(){
        String filtered = ElementActions.getText(driver,modelNumber).replaceAll("[^0-9,]","");
        String[] numbers = filtered.split(",");
        this.plateNumber = filtered;
    }

    public String plateNumber(){
        return plateNumber;
    }


    public void proceedWithMobileBtn(String mobileNum){
        ElementActions.switchToIframe(driver, By.xpath("//div[@id='group-modal']/iframe"));
        ElementActions.waitForElementToBePresent(driver, mobileTextField, 10, true);
        ElementActions.type(driver, mobileTextField, mobileNum);
        ElementActions.click(driver,saveBtn);
        ElementActions.switchToDefaultContent();
    }
}



//*[@id="element"]/div/div/div[2]/div[1]/div/div/div/input[2]