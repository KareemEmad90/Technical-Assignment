package pages.common;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OnlineLogin {

    private WebDriver driver;

    public OnlineLogin(WebDriver driver) {
        this.driver = driver;
    }

    By trfFileNo= By.name("trafficNo");
    By loginBtn=By.name("Login");

    public void login(String trfNo){
        ElementActions.type(driver,trfFileNo,trfNo);
        ElementActions.click(driver,loginBtn);
    }

}
