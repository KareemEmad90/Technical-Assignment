package pages.vls;

import com.shaft.gui.element.ElementActions;
import com.shaft.validation.Assertions;
import com.shaft.validation.Verifications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RenewVehiclePage {
    private WebDriver driver ;
    private By vehicleLicenseStatus = By.xpath("//*[@id=\"processing\"]/h4/span[1]");
    private By laneNumberLabel = By.xpath("//*[@id=\"processing\"]/div/div/div[1]/span");
    private By tokenLabel = By.xpath("//*[@class=\"token\"]/span");
    private By inspectingStatusLabel = By.xpath("//*[@class=\"attachments\"]/p");
    public RenewVehiclePage(WebDriver driver) {
        this.driver = driver;
    }
    private By checkLicensingPhase = By.xpath("//*[@id=\"processing\"]/h4/span[1]");
    private By downloadRegistrationCardButton = By.xpath("//*[@id=\"processing\"]/div/div[2]/button");

    private By viewInspectionReport=By.xpath("//a[contains(text(),'VIEW INSPECTION REPORT')]");
    private By registrationLicense=By.xpath("//a[contains(text(),'Download Registration vehicle license document')]");
    private By registraionCard=By.xpath("//div[@class='slick-slide slick-active slick-current']");
    private By downloadRegistrationCard=By.xpath("//button[@class='pushBTNDown']");


    public void verifyInspectingStatusIsDisplayed() {
        Verifications.verifyElementExists(driver, inspectingStatusLabel, "Token exists");
        String actualtokenText = ElementActions.getText(driver, inspectingStatusLabel);
        Assertions.assertEquals("Inspecting your vehicle", actualtokenText);
    }

    public void downloadRegisterCard() {
        Verifications.verifyElementExists(driver, downloadRegistrationCardButton, "register card buton is displayed");
        ElementActions.click(driver, downloadRegistrationCardButton);
    }


    public void verifyTokenNumber(String expectedToken) {
        Verifications.verifyElementExists(driver, tokenLabel, "Token exists");
        String actualtokenText = ElementActions.getText(driver, tokenLabel);
        Assertions.assertEquals(expectedToken, actualtokenText);
    }

    public void verifyLaneNumber(String expectedLaneNumber) {
        Verifications.verifyElementExists(driver, laneNumberLabel, "Token exists");
        String actualLaneNumberText = ElementActions.getText(driver, laneNumberLabel);
        Assertions.assertEquals(expectedLaneNumber, actualLaneNumberText);
    }

    public void verifyVehicleLicenseStatus(String expectedVehicleLicenseStatus) {
        Verifications.verifyElementExists(driver, vehicleLicenseStatus, "Token exists");
        String actualVehicleLicenseStatus = ElementActions.getText(driver, vehicleLicenseStatus);
        Assertions.assertEquals(expectedVehicleLicenseStatus, actualVehicleLicenseStatus);
    }

    public void verifyLicensingInsiurancePhase() {
        Verifications.verifyElementExists(driver, checkLicensingPhase);
        String actualVehicleLicenseStatus = ElementActions.getText(driver, checkLicensingPhase);
        Assertions.assertEquals("PENDING", actualVehicleLicenseStatus);
    }

    public void verifyConfirmationRenewalPage() {
       ElementActions.isElementDisplayed(driver,viewInspectionReport);
        ElementActions.isElementDisplayed(driver,registrationLicense);
        ElementActions.isElementDisplayed(driver,registraionCard);
        ElementActions.isElementDisplayed(driver,downloadRegistrationCard);
    }

    public void verifyConfirmationFailedRenewalPage() {
        ElementActions.isElementDisplayed(driver,viewInspectionReport);

    }

}
