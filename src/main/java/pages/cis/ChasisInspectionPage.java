package pages.cis;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChasisInspectionPage {

    WebDriver driver;

    By reReadResultBtn = By.id("rereadButtonId");
    By nextBtn = By.xpath("(//a[@data-theme='b']//span[contains(text(),'Next')])[1]");
    By startInspection = By.id("insStartInspectionButton");

    public ChasisInspectionPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("user attempt to click on Reread btn")
    public void clickOnRereadBtn() throws InterruptedException {
        Thread.sleep(2000);
        ElementActions.click(driver,reReadResultBtn);
        Thread.sleep(6000);
    }

    @Step("user click on next")
    public void clickOnNext(){
        ElementActions.click(driver,nextBtn);
    }

    @Step("ckick on next button")
    public void clickOnStartInspection(){
        ElementActions.click(driver,startInspection);
    }
}
