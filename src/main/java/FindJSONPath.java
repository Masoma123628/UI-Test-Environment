import java.io.File;

public class FindJSONPath {
public static void main(String[] args) {
    FindJSONPath findJSONPath = new FindJSONPath();
    findJSONPath.findFolder();
}

    public  void findFolder(){
        File reportDir = new File("D:/alluretest/allure-results");
        if(reportDir.exists()){
            findPath(reportDir);
        }
    }

    private  void findPath(File directory){
        File [] files = directory.listFiles();
        if(files != null){
            for(File file : files){
                if(file.isFile()){
                    System.out.println("File Paths:   "+file.getAbsolutePath());
                }

            }
        }

    }
}
