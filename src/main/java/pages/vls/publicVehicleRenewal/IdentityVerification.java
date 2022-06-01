package pages.vls.publicVehicleRenewal;

import api.AddElectronicInsurance;
import com.shaft.gui.element.ElementActions;
import data.DbQueries;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

public class IdentityVerification {
    private WebDriver driver ;



    public IdentityVerification(WebDriver driver){this.driver=driver;}

    By PlateCategoryClick = By.xpath("//*[@id=\"processing\"]/div/div/div[2]/div[1]/div/span/span");
    By PlateCodeClick = By.xpath("//*[@id=\"processing\"]/div/div/div[2]/div[2]/div/span");
    By PlateCategoryLST = By.xpath("/html/body/div[2]/div[2]/ul/li[1]/div/div");
    By PlateCodeLST= By.xpath("/html/body/div[2]/div[1]/div/input");
    By PlateCodeLSTClick= By.xpath("/html/body/div[2]/div[2]/ul/li[1]");//*[@class='p-dropdown-item p-highlight']
    //By PlateCodeLSTClick= By.xpath("//a[contains(text(),' here')]");
    By PlateNumberTXT =By.id("plateNumber");
    By TrafficCodeNumberTXT=By.id("trafficCodeNo");
    By ContinueBTN=By.xpath("//button[text() = 'Continue']");
    By VerifyOTPTXT =By.id("mobileNumber");



    DbQueries dbQueries = new DbQueries();
    String[] vehicle = dbQueries.getVehiclesReadyForRenewal();
    String ChassisNo = vehicle[0];
    String TrafficCodeNumber = vehicle[1];
    String  PlateNumber= vehicle[2];
    String  PlateCode = vehicle[3];
    String  MobileNumber = vehicle[4];


    public void searchForVehicle(){


        ElementActions.click(driver, PlateCategoryClick);
        ElementActions.type(driver, PlateCategoryLST, "Private");
        ElementActions.click(driver, PlateCategoryLST);
        ElementActions.click(driver, PlateCodeClick);
        ElementActions.type(driver, PlateCodeLST, PlateCode);
        //ElementActions.select(driver ,PlateCodeLST,PlateCode );
        ElementActions.click(driver, PlateCodeLSTClick);
        //driver.findElement(By.xpath("//a[contains(text(),PlateCode)]")).click();
        ElementActions.type(driver, PlateNumberTXT ,PlateNumber );
        ElementActions.type(driver, TrafficCodeNumberTXT ,TrafficCodeNumber );
        ElementActions.click(driver , ContinueBTN);

    }

    public void checkMobileNumber() throws InterruptedException {
        ElementActions.click(driver , VerifyOTPTXT);
        /*Thread.sleep(3000);
        ElementActions.type(driver, VerifyOTPTXT , MobileNumber);*/
        driver.findElement(By.id("mobileNumber")).sendKeys(MobileNumber);
        ElementActions.click(driver , ContinueBTN);
    }

    public void verifyOTP() throws InterruptedException {
        int otpIndex = 0;
        String otp = "123456";
        Thread.sleep(2000);
        for (int i = 1; i <= 6; i++) {
            driver.findElement(By.xpath("//input[@type='number'][" + i + "]"))
                    .sendKeys(Character.toString(otp.charAt(otpIndex)));
            otpIndex++;
        }
        Thread.sleep(2000);
        ElementActions.click(driver, ContinueBTN);
    }

    public void addVehicleInspection() throws InterruptedException {
        dbQueries.deleteActiveInspectionFromVls(ChassisNo);
        dbQueries.addInspectionVls(ChassisNo);
    }


    public void addVehicleInsurance() throws InterruptedException {
        AddElectronicInsurance addElectronicInsurance = new AddElectronicInsurance();
        addElectronicInsurance.elecInsuranceAPI(TrafficCodeNumber, ChassisNo);
    }




}


