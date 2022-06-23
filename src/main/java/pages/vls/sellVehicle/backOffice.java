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
    By licensingHelpDesk=By.xpath("//a[contains(text(),'Licensing Helpdesk')]");
    By employeeUserName=By.id("employeeUserName");
    By employeePassword=By.id("employeePassword");
    By empLoginbtn=By.id("empLoginbtn");

    By listOfVehicles=By.xpath("//div[@class='vehicleSideList']//ul//li");
    By listOfAttachments=By.xpath("//div[@class='attachTabs']//div");
    By approveDocument=By.xpath("//button[@class='nextBTN' and contains(text(),'Approve Document')]");
    By confirmApproveDocument=By.xpath("//div[@class='p-dialog-footer']//button[contains(text(),'Approve Document')]");
    By submitApplication=By.xpath("//button[@class='btn btn-submit']");
    By confirmSubmitApplication=By.xpath("//button[contains(text(),'Approve Document')]");

    private By navBar(String item){
        return By.xpath("//div[@id='navbarSupportedContent']//a[contains(text(),'"+item+"')]");
    }

    public void acceptAttachments() throws InterruptedException {
        List<WebElement> vehiclesList=driver.findElements(listOfVehicles);

        for (int i=1;i<=vehiclesList.size();i++){
            ElementActions.click(driver,By.xpath("//div[@class='vehicleSideList']//ul//li["+i+"]"));
           // Thread.sleep(3000);
            List<WebElement> attachedList=driver.findElements(listOfAttachments);
            for (int y=1;y<=attachedList.size();y++){
                ElementActions.click(driver,By.xpath("//div[@class='attachTabs']//div["+y+"]"));
                    ElementActions.click(driver,approveDocument);
                    ElementActions.click(driver,confirmApproveDocument);

            }

        }
        Thread.sleep(2000);
        ElementActions.click(driver,submitApplication);
        ElementActions.click(driver,confirmSubmitApplication);

    }

    public void selectLicensingHelpDeskAndLogin(){
        ElementActions.click(driver,licensingHelpDesk);
        ElementActions.type(driver,employeeUserName,"Tvtc188");
        ElementActions.type(driver,employeePassword,"Tvtc188");
        ElementActions.click(driver,empLoginbtn);
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


    public void selectApplication(String appRefNo) throws InterruptedException {
       By selectApplication=  By.xpath("//td//div[contains(text(),'"+appRefNo+"')]");
       ElementActions.click(driver,selectApplication);
       Thread.sleep(3000);
    }

    public void selectByTrafficCode(String trfCode){
        ElementActions.waitForElementToBePresent(driver,searchTable,3,true);
        List<WebElement> tableItems = driver.findElements(applicationItems(trfCode));
        for (int i = 0; i <= tableItems.size(); i++) {
            tableItems.iterator().next().click();
        }
    }

}
