package pages.vls;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver ;
    public LoginPage(WebDriver driver){this.driver=driver;}
    By eidTxt = By.id("idNationalId");
    By loginBtn = By.xpath("//button[text()='Login']");
    By tradeLicenseNo=By.xpath("//input[@placeholder='Trade License Number']");
    By tradeLicenseExpiryDate=By.id("tradeLicenseExpiryDateId");
    By tradeLicenseSource=By.xpath("//input[@placeholder='Trade License source']");


    public void login(String EID){
        ElementActions.type(driver,eidTxt, EID);
        ElementActions.click(driver,loginBtn);
    }


    public void corpLogin(String tradeLicense,String licenseExpiryDate , String licenseSource){
        ElementActions.type(driver,tradeLicenseNo, tradeLicense);
        ElementActions.type(driver,tradeLicenseExpiryDate, licenseExpiryDate);
        ElementActions.type(driver,tradeLicenseSource, licenseSource);
        ElementActions.click(driver,loginBtn);
    }

}


