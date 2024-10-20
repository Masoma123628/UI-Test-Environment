import org.testng.annotations.BeforeTest;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropBase {
    public static Properties locprop = new Properties();
    public static FileReader loca;


    @BeforeTest
    public void setup() throws IOException {

        loca = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\locators.properties");
        locprop.load(loca);
    }

}
