package pages.sauceDemoPage;

import com.shaft.gui.element.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Utils;

public class LoginPage {

    private WebDriver driver ;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    By userNameTxt=By.id("user-name");
    By passwordTxt=By.id("password");
    By loginBtn=By.id("login-button");




    @Step("Login to Website")
    public void enterLoginInfo()  {

        ElementActions.type(driver, userNameTxt , "standard_user");
        ElementActions.type(driver, passwordTxt , "secret_sauce");
        ElementActions.click(driver, loginBtn);
    }
}
