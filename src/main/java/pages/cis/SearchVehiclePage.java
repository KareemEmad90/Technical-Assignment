package pages.cis;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class SearchVehiclePage {
    private WebDriver driver;
    By chasisNumberOption = By.linkText("Chassis Number");
    By chasisNumber = By.id("chassis-no-0");
    By searchBtn = By.xpath("//*[@id=\"insSlctVhlForm:tabsContentId\"]/div[3]/a[1]");

    public SearchVehiclePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Select Chasis Number Option")
    public void selectChasisNumberOption() {
        ElementActions.waitForElementToBePresent(driver, chasisNumberOption, 3, true);
        ElementActions.click(driver, chasisNumberOption);
    }

    @Step("type chasis number")
    public void typeChasisNumber(String chasisNo)  {
        ElementActions.doubleClick(driver, chasisNumber);
        ElementActions.typeAppend(driver, chasisNumber, chasisNo);
    }

    @Step("Click search button")
    public void clickSearchButton() {
        ElementActions.click(driver, searchBtn);
    }
}
