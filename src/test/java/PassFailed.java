import com.microsoft.playwright.*;
import org.openqa.selenium.WebDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;


import org.apache.commons.io.FileUtils;



public class PassFailed {

    public static String captheImage;

    //To pass locators property file to Test case
    public static Properties locprop = new Properties();
    public static FileReader loca;

    public static WebDriver driver;


    // Shared between all tests in this class.
    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;


    @BeforeTest
    public void setup() throws IOException {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));  // setHeadless(false) to see the browser
        context = browser.newContext();
        page = context.newPage();

        
        //To pass locators property file to Test case. Here "user.dir" will read file from any directory so no need to mention exact directory location.
        loca = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\locators.properties");
        locprop.load(loca);
    }


   @AfterMethod
    public void resultIdentifier(ITestResult iTestResult) throws IOException {
        if (iTestResult.getStatus() == ITestResult.FAILURE) {
            manageFolderFile(iTestResult.getTestContext().getName() + "_" + iTestResult.getMethod().getMethodName()+"_"+ "Failed"+"_");
        } else if (iTestResult.getStatus() == ITestResult.SUCCESS) {
            manageFolderFile(iTestResult.getTestContext().getName() + "_" + iTestResult.getMethod().getMethodName() +"_"+ "Succeed"+"_");

        }

    }



    @AfterTest
    public void tearDown() {
        browser.close();
    }

    public void manageFolderFile(String imageName) throws IOException {
        //If image is not existing according to this time an image will be create with time
        if(captheImage == null) {
            LocalTime time = LocalTime.now();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");
            captheImage = time.format(timeFormatter);
        }
        //A folder will be create according to current date
        LocalDate date = LocalDate.now();
        DateTimeFormatter folderFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy/");
        String folder = date.format(folderFormatter);

        String screenshotpath = imageName + captheImage + ".png";
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotpath)));
        File sourceFile = new File(screenshotpath);
        File destFile = new File("./Screenshots/" + folder + screenshotpath);
        FileUtils.copyFile(sourceFile, destFile);
    }


}
