package momo.storage;

import momo.exception.MomoException;
import momo.parser.Parser;
import momo.task.Task;
import momo.task.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
    
    public void load(TaskList tasks) throws MomoException {
        Path filePath = Paths.get(DIRECTORY).resolve(FILE_NAME);
        if (!Files.exists(filePath)) {
            return;
        }
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                Task task = Parser.parseToTask(line);
                tasks.addTask(task);
            }
        } catch (IOException e) {
            throw new MomoException("Unable to read saved data in hard disk");
        } catch (MomoException e) {
            tasks.clear();
            throw e;
        }
    }
}
