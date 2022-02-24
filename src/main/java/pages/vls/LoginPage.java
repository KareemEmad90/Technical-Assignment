package pages.vls;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.python.antlr.ast.Str;

public class LoginPage {
    private WebDriver driver ;



    private String selectedService;
    public LoginPage(WebDriver driver){this.driver=driver;}
    By eidTxt = By.id("idNationalId");
    By loginBtn = By.xpath("//button[text()='Login']");
    By tradeLicenseNo=By.xpath("//input[@placeholder='Trade License Number']");
    By tradeLicenseExpiryDate=By.id("tradeLicenseExpiryDateId");
    By tradeLicenseSource=By.xpath("//input[@placeholder='Trade License source']");
    By serviceDDL = By.xpath("//div[contains(@class,'GC_Dropdown')]/div");
    By selectedServiceOption = By.xpath("//div[contains(@class,'GC_Dropdown')]//div[contains(@class,'item-value')]");
    By serviceDDLOption(String option){return By.xpath("//li[@aria-label='"+option+"']");}


    private String getSelectedService() {
        selectedService = ElementActions.getText(driver,selectedServiceOption);
        return selectedService;
    }

    private void selectService(String service){
        if (service.equals(getSelectedService()))
            System.out.println("service Selected allready");
        else
            ElementActions.click(driver,serviceDDL);
            ElementActions.click(driver,serviceDDLOption(service));
    }

    private void writeEmiratesID(String id){
        ElementActions.type(driver,eidTxt,id);
    }


    private void WriteTradeLicense(String number){
        ElementActions.type(driver,tradeLicenseNo,number);
    }

    private void WriteExpireDate(String date){
        ElementActions.type(driver,tradeLicenseExpiryDate,date);
    }

    private void writeLicenseSource(String source){
        ElementActions.type(driver,tradeLicenseSource,source);
    }


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

    public void corpLogin(String service,String id,String tradeLicense,String expireDate,String source){
        selectService(service);
        writeEmiratesID(id);
        WriteTradeLicense(tradeLicense);
        WriteExpireDate(expireDate);
        writeLicenseSource(source);
    }

}


