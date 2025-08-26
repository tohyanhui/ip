package momo.command;

import momo.storage.Storage;
import momo.task.Task;
import momo.task.TaskList;
import momo.task.Todo;
import momo.ui.Ui;

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
        ui.showAddTaskMessage(task, tasks);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
