package pages.cis;

import com.shaft.gui.element.ElementActions;
import data.DatabaseActionsCustom;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EditVehiclePage {

    By continueBtn = By.id("formId:btn-continue");

    By chasisLbl = By.xpath("//label[@for='chassis-no-0']");

    By chasisNumber = By.xpath("//label[@for='chassis-no-0']/following-sibling::div//span");

    By validationMsg = By.xpath("//li[contains(text(),'Integration with traffic is Success No Records')]");

    private WebDriver driver;

    public EditVehiclePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get Chasis details from DB")
    public static String[] getChasisDetails() {
        String[] chasisDetails = new String[4];
        DatabaseActionsCustom databaseActionsCustom = new DatabaseActionsCustom();
        ResultSet result = databaseActionsCustom.executeSelectQuery("SELECT * FROM  QC_USERS.GET_VLS_RENEW_DATA where rownum < 10;");

        try {
            // Get Passport number
            chasisDetails[0] = result.getString(1);
            // Get Date of birth
            chasisDetails[1] = result.getString(2);
            // Get Nationality
            chasisDetails[2] = result.getString(3);
            // Get Mobile
            chasisDetails[3] = result.getString(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Chasis number = " + chasisDetails[0] + "  RTA_UNIFIED_NO = " + chasisDetails[1] + " Emirates ID = " + chasisDetails[2] + " Expiry date = " + chasisDetails[3]);

        return chasisDetails;
    }

    @Step("Click on continue button")
    public void clickContinueBtn() {
        ElementActions.waitForElementToBePresent(driver, continueBtn, 10, true);
        ElementActions.click(driver, continueBtn);
    }

    @Step("check that chasis number is displayed")
    public boolean verifyChasisNumberDisplyed(){
        return ElementActions.isElementDisplayed(driver,chasisLbl);
    }

    @Step("check validation message appeared")
    public boolean verifyValidationMsg(){
        return ElementActions.isElementDisplayed(driver,validationMsg);
    }


    @Step("get chasis number")
    public String getChasisNumber() {
        return ElementActions.getText(driver,chasisNumber);
    }
}
