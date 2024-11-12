import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TestWrite {
    public static void main(String[] args) {
       /* try {
            File excelFile = new File("D:\\ET.xlsx");
            FileInputStream fis = new FileInputStream(excelFile);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            // Create the sheet if it doesn't exist
            if (sheet == null) {
                sheet = workbook.createSheet("Sheet1");
            }

            // Data to write
            String[][] dataToWrite = {
                    {"MMMMMMMMMMMMM", "0987"},
                    {"NNNNNNNNNNNN", "67899"}
            };

            // Write data to the sheet
            for (int i = 0; i < dataToWrite.length; i++) {
                Row row = sheet.createRow(sheet.getPhysicalNumberOfRows()); // Create a new row
                for (int j = 0; j < dataToWrite[i].length; j++) {
                    Cell cell = row.createCell(j); // Create a new cell
                    cell.setCellValue(dataToWrite[i][j]); // Set the cell value
                }
            }

            // Write changes back to the file
            FileOutputStream fos = new FileOutputStream(excelFile);
            workbook.write(fos);

            // Close resources
            fos.close();
            workbook.close();
            fis.close();


        } catch (Exception e) {
            e.printStackTrace();
        }*/





        try {
            File excelFile = new File("D:\\ET.xlsx");
            FileInputStream fis = new FileInputStream(excelFile);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            String[][] dataToWrite = {
                    {"MMMMMMMMMMMMM", "0987"},
                    {"NNNNNNNNNNNN", "67899"}
            };

            // To get number of row
            int noOfRow = sheet.getPhysicalNumberOfRows();

            //To get datas skipping header row
            int noOfColumn = sheet.getRow(0).getLastCellNum();

//            String[][] data = new String[noOfRow - 1][noOfColumn];
//
//            for (int i = 0; i < noOfRow - 1; i++) {
//                for (int j = 0; j < noOfColumn; j++) {
//

            for (int i = 0; i < dataToWrite.length; i++) {
                //Row row = sheet.createRow(sheet.getPhysicalNumberOfRows()); // Create a new row
                for (int j = 0; j < dataToWrite[i].length; j++) {
                    //Cell cell = row.createCell(j); // Create a new cell
                   // cell.setCellValue(dataToWrite[i][j]); // Set the cell value
                    dataToWrite[i][j] = dataToWrite[i][j];
                }
            }

            // Write changes back to the file
            FileOutputStream fos = new FileOutputStream(excelFile);
            workbook.write(fos);

            // Close resources
            fos.close();
            workbook.close();
            fis.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
