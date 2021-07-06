package pages.cis;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChasisInspectionPage {

    WebDriver driver;

    By reReadResultBtn = By.id("rereadButtonId");

    public ChasisInspectionPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("user attempt to click on Reread btn")
    public void clickOnRereadBtn() throws InterruptedException {
        Thread.sleep(2000);
        ElementActions.click(driver,reReadResultBtn);
        Thread.sleep(6000);
    }
}
