package pages.vls.sellVehicle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IdentityVerificationPage {
    private WebDriver driver;

    By orgOwnerBtn = By.id("OrganizationOwner");
    By authOwnerBtn = By.id("AuthorizedOwner");
    By applicationRefNum = By.name("registeredMobile");
    By authIssuedBtn = By.id("yes");
    By authNotIssuedBtn = By.id("no");
    By associationRefNum = By.xpath("//div[@class='GC_infoIcon']/parent::label/following-sibling::input");   //id missing
    By orgTradeLicenseUpload = By.xpath("//div[@id='uploaderAuthorised']//input"); //id missing
    By proceedToInfo = By.xpath("//div[@class='btnActions']/button"); //id missing


}
