public class AddEventCommand implements Command {
    private final String description;
    private final String from;
    private final String to;
    
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        ui.printAddTaskMessage(task, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
