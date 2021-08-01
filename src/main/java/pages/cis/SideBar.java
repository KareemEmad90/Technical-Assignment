package pages.cis;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SideBar {

    By toggleBtn = By.id("menu-toggle");

    By homeBtn = By.className("home-menu");

    By sideMenuCont = By.className("main-menu");


    By oneBtn(String menuItem) {
        return By.xpath("//ul[@class='main-menu']/li//a[@title='" + menuItem + "']");
    }

    By twoBtn(String menuItem, String secondMenuItem) {
        return By.xpath("//ul[@class='main-menu']/li//a[@title='" + menuItem + "]/parent::div/following-sibling::ul/li//a[@title='" + secondMenuItem + "']");
    }

    WebDriver driver;

    public SideBar(WebDriver driver) {
        this.driver = driver;
    }

    @Step("user click on home button from side menu")
    public void sideMenuGoHome() {
        ElementActions.waitForElementToBePresent(driver, sideMenuCont, 3, true);
        ElementActions.click(driver, toggleBtn);
        ElementActions.click(driver, homeBtn);
    }

    @Step("User click on side menu item")
    public void clickMenuItem(String menuItem) {
        ElementActions.waitForElementToBePresent(driver, sideMenuCont, 3, true);
        ElementActions.click(driver, oneBtn(menuItem));
    }

    @Step("User click on side menu item")
    public void clickMenuItem(String menuItem, String secondMenuItem) {
        ElementActions.waitForElementToBePresent(driver, sideMenuCont, 3, true);
        ElementActions.click(driver, oneBtn(menuItem));
        ElementActions.click(driver, twoBtn(menuItem, secondMenuItem));
    }
}
