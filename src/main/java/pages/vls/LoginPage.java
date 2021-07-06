package pages.vls;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver ;
    public LoginPage(WebDriver driver){this.driver=driver;}
    By eidTxt = By.id("idNationalId");
    By loginBtn = By.xpath("//button[text()='Login']");

    public void login(String EID){
        ElementActions.type(driver,eidTxt, EID);
        ElementActions.click(driver,loginBtn);
    }
}
