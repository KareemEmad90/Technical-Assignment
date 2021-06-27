package pages.cis;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CisLoginPage {

    By userNameTxt = By.id("j_username");
    By passwordTxt = By.id("j_password");
    By loginBtn = By.name("submit");
    private WebDriver driver;
    public void login(String username , String password)
    {
        ElementActions.type(driver , userNameTxt,username);
        ElementActions.type(driver,passwordTxt,password);
        ElementActions.click(driver,loginBtn);
    }
    public CisLoginPage(WebDriver driver){
        this.driver = driver ;
    }
}