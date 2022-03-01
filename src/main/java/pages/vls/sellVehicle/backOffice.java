package pages.vls.sellVehicle;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class backOffice {
    private WebDriver driver;

    public backOffice(WebDriver driver) {
        this.driver = driver;
    }

    By empNameFld = By.id("employeeUserName");
    By login = By.id("empLoginbtn");
    By searchTable = By.className("searchCriteria");


    private By navBar(String item){
        return By.xpath("//div[@id='navbarSupportedContent']//a[contains(text(),'"+item+"')]");
    }

    private By applicationItems(String text){
        return By.xpath("//tbody//td[contains(text(),'"+text+"')]");
    }

    public void selectItem(String item){
        ElementActions.click(driver,navBar(item));
    }

    public void login(String empName){
        ElementActions.type(driver,empNameFld,empName);
        ElementActions.click(driver,login);
    }

    public void selectByTrafficCode(String trfCode){
        ElementActions.waitForElementToBePresent(driver,searchTable,3,true);
        List<WebElement> tableItems = driver.findElements(applicationItems(trfCode));
        for (int i = 0; i <= tableItems.size(); i++) {
            tableItems.iterator().next().click();
        }
    }

}
