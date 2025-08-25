import java.time.LocalDateTime;

public class AddDeadlineCommand implements Command {
    private final String description;
    private final LocalDateTime by;

    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        storage.save(tasks);
        ui.printAddTaskMessage(task, tasks);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
