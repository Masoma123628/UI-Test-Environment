import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJSONWrite {
    public void writeJsonData(String[][] data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Create a List of Maps to hold the data
            List<Map<String, String>> jsonData = new ArrayList<>();

            for (String[] row : data) {
                Map<String, String> jsonMap = new HashMap<>();
                jsonMap.put("name", row[0]);
                jsonMap.put("pass", row[1]);
                jsonData.add(jsonMap);
            }

            // Write the List of Maps to a JSON file
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("D:\\alluretest\\JSONData.json"), jsonData);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
