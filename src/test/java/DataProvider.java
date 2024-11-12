import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataProvider {
    @org.testng.annotations.DataProvider(name = "ProvideData")
    public String[][] exceldataProvider() throws Exception {
        File excelFile = new File("D:\\alluretest\\TestData.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);

        // This is for new version of XLXS and it selected from XSSFWorkbook Inputstream.
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        //Here LoginData is tab name sheet
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        // To get number of row
        int noOfRow = sheet.getPhysicalNumberOfRows();

        //To get datas skipping header row
        int noOfColumn = sheet.getRow(0).getLastCellNum();
        //Here "noOfRow-1" is used for omitted 1 row cause of in first row contain row name
        String[][] data = new String[noOfRow - 1][noOfColumn];

        for (int i = 0; i < noOfRow - 1; i++) {
            for (int j = 0; j < noOfColumn; j++) {
                //DataFormatter is used to collect all types data from xlxs as if data is int, Float, String any types.
                DataFormatter formatter = new DataFormatter();

                //Reading entire data from array
                data[i][j] = formatter.formatCellValue(sheet.getRow(i + 1).getCell(j));
            }

        }
        //Have to close connection other wise memory leakage will be happened
        workbook.close();
        fis.close();

        return data;
    }


    @org.testng.annotations.DataProvider(name = "ProvideJsonData")
    public String[][] jsonDataProvider() {
        String[][] data = null; // Initialize data
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Read JSON data into a List of Maps
            List<Map<String, String>> jsonData = mapper.readValue(new File("D:\\alluretest\\JSONData.json"),
                    new TypeReference<>() {
                    });

            // Initialize the data array with the correct size
            data = new String[jsonData.size()][2]; // 2 columns for name and pass

            for (int i = 0; i < jsonData.size(); i++) {
                Map<String, String> jsonMap = jsonData.get(i);
                data[i][0] = jsonMap.get("name");
                data[i][1] = jsonMap.get("pass");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return data;
    }


}
