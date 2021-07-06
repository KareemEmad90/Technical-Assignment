package pages.common;

import org.openqa.selenium.By;

public class OpenShiftPage {
    By loginLink = By.xpath("//*[@title='Log in with isoftadmin']");
    By userNameTxt = By.id("inputUsername");
    By passwordTxt = By.id("inputPassword");
    By loginBtn = By.xpath("//*[@type='submit']");

}
