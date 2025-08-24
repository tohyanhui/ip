import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();
    
    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void markTask(int index) {
        getTask(index).markAsDone();
    }

    public void unmarkTask(int index) {
        getTask(index).unmarkFromDone();
    }

    public int size() {
        return tasks.size();
    }
    
    public String convertToSaveFormat() {
        return String.join(System.lineSeparator(), 
                tasks.stream().map(Task::convertToSaveFormat).toList());
    }
}
