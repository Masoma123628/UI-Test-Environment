import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BackupFolder {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
    String dateString = dateFormat.format(new Date());
    File reportFolder = new File("D:/alluretest/allure-results");
    File backupFolder = new File(dateString);
    static BackupFolder folder = new BackupFolder();

    public void takeBackup() throws IOException {
        if (!backupFolder.exists()) {
            boolean created = backupFolder.mkdir();// Create folder
            if (created) {
                folder.coppingFolder();
            }

        } else if (backupFolder.exists()) {
            folder.coppingFolder();
        }
    }

    public void coppingFolder() {
        // Copy the reportFolder into the backupFolder
        try {
            // Create a target directory within the backup folder
            Path targetFolder = backupFolder.toPath().resolve(reportFolder.getName());
            Files.createDirectories(targetFolder);
            // Walk through the reportFolder and copy its contents
            Files.walk(reportFolder.toPath())
                    .forEach(sourcePath -> {
                        Path targetPath = targetFolder.resolve(reportFolder.toPath().relativize(sourcePath));
                        try {
                            if (Files.isDirectory(sourcePath)) {
                                Files.createDirectories(targetPath);
                            } else {
                                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                            }
                        } catch (IOException e) {
                            System.err.println("Error copying file: " + e.getMessage());
                        }
                    });

            System.out.println("Backup completed successfully.");

        } catch (IOException e) {
            System.err.println("Failed to create backup: " + e.getMessage());
        }
    }

  /*  // Clear report folder if exists
    public void checkAllureReport() {
        if (reportFolder.exists()) {
            deleteAllureReport(reportFolder);
        } else if (!reportFolder.exists()) {
            //Go to PropTesta class

        }
    }

    private void deleteAllureReport(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteAllureReport(file);
                } else {
                    file.delete();
                }

            }
        }
        directory.delete();
    }*/

    public static void main(String[] args) throws IOException {
        folder.takeBackup();
    }
}
