public class AddDeadlineCommand implements Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        ui.printAddTaskMessage(task, tasks);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
