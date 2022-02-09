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
    By loadingBall= By.xpath("//div[@class='double-bounce1']");
    By loadingBall2= By.xpath("//div[@class='double-bounce2']");

    public void login(String EID){
        ElementActions.type(driver,eidTxt, EID);
        ElementActions.click(driver,loginBtn);
    }

    public void bounceWait(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(loadingBall2));
    }

}


