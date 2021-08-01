package pages.cis;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class inspectionResultsPage {

    WebDriver driver;
    String result;

    By resultTxt = By.xpath("//table[@id='table-custom-3']/tbody//td[last()]/span");

    By finishBtn = By.id("nav:finishBtn");

    public inspectionResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getResult() {
        return result;
    }

    public void checkResult(){
        ElementActions.waitForElementToBePresent(driver,resultTxt,5,true);
        this.result = ElementActions.getText(driver,resultTxt);
    }

    @Step("user click on finish")
    public void clickOnFinish(){
        ElementActions.click(driver,finishBtn);
    }

}
