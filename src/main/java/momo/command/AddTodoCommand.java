package momo.command;

import momo.storage.Storage;
import momo.task.Task;
import momo.task.TaskList;
import momo.task.Todo;
import momo.ui.Ui;

/**
 * Represents a command that adds a {@link Todo} task
 * to the task list.
 */
public class AddTodoCommand implements Command {
    private final String description;

    /**
     * Creates a new {@code AddTodoCommand}.
     *
     * @param description the description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a new {@link Todo} task to the task list.
     * Also saves the updated task list to storage and
     * displays a confirmation message to the user.
     *
     * @param tasks the task list to which the todo is added.
     * @param ui the user interface that displays messages to the user.
     * @param storage the storage handler that saves the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);
        tasks.addTask(task);
        storage.save(tasks);
        ui.showAddTaskMessage(task, tasks);
    }

    /**
     * Returns whether this command will exit the program.
     *
     * @return {@code false}, as adding a todo does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
