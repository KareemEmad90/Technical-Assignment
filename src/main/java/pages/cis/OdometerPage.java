package pages.cis;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OdometerPage {
    private WebDriver driver ;
    By odometerReadingTxt = By.id("finishForm:odometerNewReadingInputId:odometerNewReadingInputIdField");
    By nextBtn = By.xpath("//form[@id='defectsForm']//ancestor::div[@class='ui-grid-a custom-ins-odometer']/following-sibling::*[@onclick='goToValidate()']");

    public void fillOdometer(){
        ElementActions.type(driver,odometerReadingTxt,"9000");
        ElementActions.click(driver,nextBtn);
    }
    public OdometerPage(WebDriver driver) {this.driver = driver;}
}
