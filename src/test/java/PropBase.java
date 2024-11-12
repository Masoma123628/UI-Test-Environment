import com.microsoft.playwright.Page;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.Properties;


public class PropBase {
    //To pass locators property file to Test case
    public static Properties locprop = new Properties();
    public static FileReader loca;



    @BeforeTest
    public void setup() throws IOException {

        //Take backup of allure reports


        // To delete existing allure reports
        ReportCleaner reportCleaner = new ReportCleaner();
        reportCleaner.deleteAllureReport();

        //To pass locators property file to Test case. Here "user.dir" will read file from any directory so no need to mention exact directory location.
        loca = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\locators.properties");
        locprop.load(loca);
    }


}
