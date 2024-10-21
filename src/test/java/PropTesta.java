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

            page.fill(locprop.getProperty("user_name"), "standard_user");
            page.fill(locprop.getProperty("user_pass"), "secret_sauce");
            page.click(locprop.getProperty("signin_button"));



            page.waitForTimeout(5000);
            browser.close();


        }



    }
}
