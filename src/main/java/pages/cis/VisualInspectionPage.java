package pages.cis;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VisualInspectionPage {
    private WebDriver driver;

    By continueBtn = By.xpath("//*[@onclick='validateNonVerified()'][1]");

    public void visualInsContinue(){
        ElementActions.click(driver,continueBtn);
    }

    public VisualInspectionPage(WebDriver driver){this.driver=driver;}
}
