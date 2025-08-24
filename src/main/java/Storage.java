import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private static final String DIRECTORY = "data";
    private static final String FILE_NAME = "momo.txt";

    public void save(TaskList tasks) {
        Path directoryPath = Paths.get(DIRECTORY);
        Path filePath = directoryPath.resolve(FILE_NAME);
        try {
            Files.createDirectories(directoryPath); // does nothing if directory already exists
            Files.writeString(filePath, tasks.convertToSaveFormat());
        } catch (IOException e) {
            System.out.println("Error saving tasks in hard disk: " + e.getMessage());
        }
    }
}
