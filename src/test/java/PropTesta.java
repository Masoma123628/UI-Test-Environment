import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class PropTesta extends PropBase{
    @Test
    public void testsearch(){
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));  // setHeadless(false) to see the browser
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("https://www.saucedemo.com/");

            page.fill(locprop.getProperty("user_name"), "Masoma");
            page.fill(locprop.getProperty("user_pass"), "12345");

            page.waitForTimeout(5000);
            browser.close();


        }



    }
}
