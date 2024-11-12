import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class TimeFromAPI {

    public static void main(String[] args) {
        // URL of the World Time API (Change timezone as needed)
        String url = "http://worldtimeapi.org/api/timezone/Etc/UTC"; // UTC time

        try {
            // Create URL object
            URL obj = new URL(url);

            // Open HTTP connection
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Set the request method to GET
            con.setRequestMethod("GET");

            // Set the request headers (optional)
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Get the HTTP response code
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Check if the response code is HTTP_OK (200)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                // Read the response line by line
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the response JSON
                JSONObject jsonResponse = new JSONObject(response.toString());

                // Extract the datetime field from the JSON response
                String datetime = jsonResponse.getString("datetime");

                // Print the current time
                System.out.println("Current Time (UTC): " + datetime);
            } else {
                // Print out the error stream for further details
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String errorLine;
                StringBuilder errorMessage = new StringBuilder();
                while ((errorLine = errorReader.readLine()) != null) {
                    errorMessage.append(errorLine);
                }
                errorReader.close();
                System.out.println("GET request failed. Response code: " + responseCode);
                System.out.println("Error message: " + errorMessage.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
