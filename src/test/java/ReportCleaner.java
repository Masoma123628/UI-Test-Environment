import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


//It is called in PropBase class
public class ReportCleaner {

    public  void deleteAllureReport(){
        File reportDir = new File("D:/alluretest/allure-results");
        if(reportDir.exists()){
            deleteDirectory(reportDir);
        } else if (!reportDir.exists()) {
            //Go to PropTesta class

        }
    }

    private  void deleteDirectory(File directory){
        File [] files = directory.listFiles();
        if(files != null){
            for(File file : files){
                if(file.isDirectory()){
                    deleteDirectory(file);
                } else {
                    file.delete();
                }

            }
        }
        directory.delete();
    }

}
