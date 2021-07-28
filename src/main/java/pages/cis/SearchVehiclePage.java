package pages.cis;

import com.shaft.gui.element.ElementActions;
import enums.CertificateType;
import enums.SearchVehicleType;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchVehiclePage {
    private final WebDriver driver;
    By chasisNumberOption = By.linkText("Chassis Number");
    By chasisNumber = By.id("chassis-no-0");
    By searchBtn = By.xpath("//div[@id='insSlctVhlForm:tabsContentId']//a[contains(@class,'proceed')]");
    By selectedSearchType = By.xpath("//ul[@id='shift-tabs']/li[@class='active']/a");
    By searchContainer = By.id("shift-tabs");
    By certificateDDl = By.id("insSlctVhlForm:certificateType:certificateTypeField");
    By documentNumebrTxt = By.id("insSlctVhlForm:documentNumber:documentNumberField");
    By certificateDateTxt = By.id("insSlctVhlForm:certificateDate:certificateDateField");
    By emirateDDL = By.xpath("//div[@id='s2id_test-emirate']/a");
    By selecetedEmire = By.xpath("//div[@id='select2-drop']/ul/li[contains(@class,'highlighted')]");
    By emirateSearchBox = By.xpath("//div[@id='select2-drop']//input");
    By emirateDDLItem(String emirate){return By.xpath("//div[@id='select2-drop']//div[contains(text(),'"+emirate+"')]");}



    public SearchVehiclePage(WebDriver driver) {
        this.driver = driver;
    }

    By searchVhcleType(String Searhctype) {
        return By.xpath("//ul[@id='shift-tabs']/li[not(@class='active')]/a[text()='" + Searhctype + "']");
    }


    @Step("user select search type")
    public void selectSearchType(SearchVehicleType searchType) {
        ElementActions.waitForElementToBePresent(driver, searchContainer, 4, true);
        if (!ElementActions.getText(driver, selectedSearchType).equals(searchType)) {
            ElementActions.click(driver, searchVhcleType(String.valueOf(searchType)));
        } else {
            System.out.println(searchType + " selected allready user proceed to provide search data");
        }
    }

    @Step("Select Chasis Number Option")
    public void selectChasisNumberOption() {
        ElementActions.waitForElementToBePresent(driver, chasisNumberOption, 3, true);
        ElementActions.click(driver, chasisNumberOption);
    }

    @Step("type chasis number")
    public void typeChasisNumber(String chasisNo) {
        ElementActions.doubleClick(driver, chasisNumber);
        ElementActions.typeAppend(driver, chasisNumber, chasisNo);
    }

    @Step("Click search button")
    public void clickSearchButton() {
        ElementActions.click(driver, searchBtn);
    }

    @Step("User select certificate type")
    public void selectCertificate(CertificateType certificateType, String docNum) {
        ElementActions.select(driver, certificateDDl, certificateType.toString());
        ElementActions.type(driver, documentNumebrTxt, docNum);
    }

    @Step("User select certificate type")
    public void selectCustomCertificate(String docNum, String date) {
        ElementActions.select(driver, certificateDDl, CertificateType.CustomCertificate.toString());
        ElementActions.type(driver, documentNumebrTxt, docNum);
        ElementActions.type(driver, certificateDateTxt, date);
    }

    @Step("user select emirate")
    public void selectEmirate(String emirate){
        if (!ElementActions.getText(driver,selecetedEmire).equals(emirate)){
            ElementActions.click(driver,emirateDDL);
            ElementActions.type(driver,emirateSearchBox,emirate);
            ElementActions.click(driver,emirateDDLItem(emirate));
        }else {
            System.out.println("targated emirate allready selected");
        }
    }

}

