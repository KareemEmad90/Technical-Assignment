package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {
    private WebDriver driver ;
    public Utils(WebDriver driver){
        this.driver = driver ;
    }
    public void editElementAttributeValue(By locator, String attribute, String value)
    {
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('"+ attribute +"', '"+ value +"')", driver.findElement(locator));
    }
}
