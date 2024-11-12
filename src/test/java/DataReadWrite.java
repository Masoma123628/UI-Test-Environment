import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class DataReadWrite extends PropBase {

   @Test(dataProvider = "ProvideData", dataProviderClass = DataProvider.class)
    public void readData(String nameProvide, String passwordProvide) { //To read data from Excel file
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));  // setHeadless(false) to see the browser
            BrowserContext context = browser.newContext();


            Page page = context.newPage(); //Page provides methods to interact with a single tab in a Browse

            page.navigate("https://www.saucedemo.com/");
            page.fill(locprop.getProperty("user_name"), nameProvide);
            page.fill(locprop.getProperty("user_pass"), passwordProvide);
            page.click(locprop.getProperty("signin_button"));

            page.waitForTimeout(5000);
            browser.close();
        }
    }


    @Test (dataProvider = "ProvideJsonData", dataProviderClass = DataProvider.class)
    public void readJSONData(String nameJSON, String passwordJSON) {//To read data from Excel file
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));  // setHeadless(false) to see the browser
            BrowserContext context = browser.newContext();



            Page page = context.newPage(); //Page provides methods to interact with a single tab in a Browse

            page.navigate("https://www.saucedemo.com/");
            page.fill(locprop.getProperty("user_name"), nameJSON);
            page.fill(locprop.getProperty("user_pass"), passwordJSON);
            page.click(locprop.getProperty("signin_button"));



            page.waitForTimeout(5000);
            browser.close();
        }
    }
}