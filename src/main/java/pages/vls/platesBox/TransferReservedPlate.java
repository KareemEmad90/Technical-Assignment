package pages.vls.platesBox;

import data.LoadProperties;
import org.openqa.selenium.By;

public class TransferReservedPlate {

    By plateDetails=By.xpath("plate-details-section");
    By eIdText=By.id("nationalId");
    By mobileNo=By.id("mobileNumber");
    By uploadattachment=By.xpath("//div[@class='MuiDropzoneArea-textContainer']");
    By continueBtn=By.xpath("//button[contains(text(),'Continue')]");

//System.getProperty("user.dir")+ LoadProperties.userData.getProperty("attachmentFilePngPath")



}
