import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class PassFailTest extends PassFailed {

    @Test
    public void checkStatus() {

        page.navigate("https://www.saucedemo.com/");
        //  page.fill(locprop.getProperty("user_name"), "");

        page.fill(locprop.getProperty("user_name"), "standard_user");
        page.fill(locprop.getProperty("user_pass"), "secret_sauce");
        page.click(locprop.getProperty("signin_button"));


// To get information after inspect
        page.onResponse(response -> {
            //if (page.getByLabel("Products").isVisible() && response.status() == 200) {
            if ( response.status() == 200) {

                System.out.println("Login successful");
                System.out.println(response.headers().get("date"));
                System.out.println(response.headers().get("last-modified"));
            } else {
                System.out.println("Login failed");
            }
        });


        page.waitForTimeout(5000);


    }


}
