import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import javax.naming.Context;
import java.nio.file.Paths;

public class PropTesta extends PropBase {
    @Test
    public void testsearch() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));  // setHeadless(false) to see the browser
            BrowserContext context = browser.newContext();

            //I have to write this code in BaseClass Before Class,Method or Test
            // Start tracing before creating / navigating a page.
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));

            //Record video
            context = browser.newContext(new Browser.NewContextOptions()
                    .setRecordVideoDir(Paths.get("videos/"))
                    .setRecordVideoSize(600, 600)
            );
// Make sure to close, so that videos are saved.
//            context.close();

            /*-----------------------------------------------------------------------------------------------------------------*/
            Page page = context.newPage(); //Page provides methods to interact with a single tab in a Browse

            page.navigate("https://www.saucedemo.com/");

            page.fill(locprop.getProperty("user_name"), "standard_user");
            page.fill(locprop.getProperty("user_pass"), "secret_sauce");
            page.click(locprop.getProperty("signin_button"));
            /*------------------------------------------------------------------------------------------------------------------*/

            //I have to write this code in BaseClass After Class,Method or Test
            context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));//Stop this Trace context
            context.close(); //Close this trace context


            page.waitForTimeout(5000);
            browser.close();


        }


    }
}
