package pages.cis;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CisLoginPage {

    By userNameTxt = By.id("j_username");
    By passwordTxt = By.id("j_password");

    By loginBtn = By.xpath("//*[@id='sign-in']//*[@name='submit']");

    By langBtn(String lang) {
        return By.xpath("//form[@id='sign-in']/following-sibling::div//a[contains(@class,'" + lang + "')]");
    }

    private WebDriver driver;

    public CisLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("User enters his/her credintiols for log in CIS")
    public void login(String username, String password) {
        ElementActions.waitForElementToBePresent(driver, userNameTxt, 5, true);
        ElementActions.type(driver, userNameTxt, username);
        ElementActions.type(driver, passwordTxt, password);
        ElementActions.click(driver,loginBtn);
    }

    @Step("User atttempt to change language")
    public void changeLanguage(String language){
        ElementActions.click(driver,langBtn(language));
    }

}

