package utils;

import data.LoadProperties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    private WebDriver driver;

    public Utils(WebDriver driver) {
        this.driver = driver;
    }

    private static String GetCodeFrom(String Url) {
        String code = Url.split("=")[1];
        return code;
    }

    public static void renameInspectionFile(String codeFromUrl) throws IOException {
        File file = new File(System.getProperty("user.dir") + "\\InspectionFiles\\" + getInspectionFil());
        File file2 = new File(System.getProperty("user.dir") + "\\InspectionFiles\\" + GetCodeFrom(codeFromUrl) + ".txt");
        if (file2.exists()) {
            throw new java.io.IOException("file exists");
        }
        boolean success = file.renameTo(file2);
        try {
            Files.write(Paths.get(file2.getAbsolutePath()), "       ".getBytes(), StandardOpenOption.APPEND);
            Thread.sleep(4000);
            try {
                Path src = Paths.get(System.getProperty("user.dir") + "\\InspectionFiles\\" + GetCodeFrom(codeFromUrl) + ".txt");
                File destination = new File(LoadProperties.userData.getProperty("inspectiosDir")+ GetCodeFrom(codeFromUrl) + ".txt");
                Path dest = destination.toPath().toAbsolutePath();
                Files.copy(src,dest, StandardCopyOption.COPY_ATTRIBUTES);
                if (checkFileCopied(codeFromUrl)){
                    System.out.println("file copied correctly to "+LoadProperties.userData.getProperty("inspectiosDir")+" successfully");
                }else {
                    System.out.println("file not copied");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println();
        } catch (IOException | InterruptedException e) {
            System.out.println("file not exist in the target directory");
        }
        if (!success) {
        }
    }

    private static String getInspectionFil() {
        File folder = new File(System.getProperty("user.dir") + "\\InspectionFiles");
        File[] listOfFiles = folder.listFiles();
        String fileName = "";
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println(listOfFiles[i].getName());
                fileName = listOfFiles[i].getName();
            }
        }
        return fileName;
    }

    private static boolean checkFileCopied(String codeFromUrl) {
        File folder = new File(System.getProperty("user.dir") + "\\InspectionFiles");
        File[] listOfFiles = folder.listFiles();
        boolean isFileFound = false;
        String fileName = GetCodeFrom(codeFromUrl) + ".txt";
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                if (listOfFiles[i].getName().equals(fileName)) {
                    isFileFound = true;
                    break;
                } else {
                    isFileFound = false;
                    break;
                }
            } else if (listOfFiles[i].isDirectory()) {
                List<String> files = new ArrayList(Arrays.asList(listOfFiles[i]));
                if (files.contains(fileName)) {
                    isFileFound = true;
                    break;
                } else {
                    isFileFound = false;
                    break;
                }
            }
        }
        return isFileFound;
    }



    public void editElementAttributeValue(By locator, String attribute, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "')", driver.findElement(locator));
    }
}
