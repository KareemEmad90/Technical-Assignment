package pages.cis;

import com.shaft.gui.element.ElementActions;
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

    public SearchVehiclePage(WebDriver driver) {
        this.driver = driver;
    }

    By searchVhcleType(String Searhctype) {
        return By.xpath("//ul[@id='shift-tabs']/li[not(@class='active')]/a[text()='" + Searhctype + "']");
    }


    @Step("user select search type")
    public void selectSearchVhcleType(SearchVehicleType searchType) {
        if (!ElementActions.getText(driver, selectedSearchType).equals(searchType)) {
            ElementActions.click(driver, searchVhcleType(String.valueOf(searchType)));
        }else{
            System.out.println(searchType+" selected allready user proceed to provide search data");
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
}
