import com.fasterxml.jackson.databind.util.JSONPObject;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;


public class JsonTimestampFormatter {
    public static void main(String[] args){
        String jsonFilePath = "D:/alluretest/allure-results/3d9c783e-2708-4949-a1f2-4a4bd9cc568c-result.json"; // Update this path

        try {
            // Read the JSON file
            String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

            // Parse the JSON content
            JSONObject jsonObject = new JSONObject(content);

           // Extract the start timestamp
            long startMillis = jsonObject.getLong("start");
            long stopMillis = jsonObject.getLong("stop");


            // Convert milliseconds to Date
            Date startDate = new Date(startMillis);
            Date stopDate = new Date(stopMillis);

            // Define the desired date format
            SimpleDateFormat startdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            SimpleDateFormat stopdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateStart = startdateFormat.format(startDate);
            String dateStop = stopdateFormat.format(stopDate);
            // Output the formatted date
            System.out.println("Formatted Start Time: " + dateStart+"Formatted Stop Time: " + dateStop);

          // String[][] reportName = new String[dateStart][dateStop];

        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
    }
    }

