public class AddTodoCommand implements Command {
    private final String description;
    
    public AddTodoCommand(String description) {
        this.description = description;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);
        tasks.addTask(task);
        storage.save(tasks);
        ui.printAddTaskMessage(task, tasks);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
