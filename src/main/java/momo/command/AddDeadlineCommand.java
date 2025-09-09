package momo.command;

import java.time.LocalDateTime;

import momo.storage.Storage;
import momo.task.Deadline;
import momo.task.Task;
import momo.task.TaskList;
import momo.ui.Ui;

/**
 * Represents a command that adds a {@link Deadline} task
 * to the task list.
 */
public class AddDeadlineCommand implements Command {
    private final String description;
    private final LocalDateTime by;

    /**
     * Creates a new {@code AddDeadlineCommand}.
     *
     * @param description the description of the deadline task.
     * @param by the due date and time of the deadline task.
     */
    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Adds a new {@link Deadline} task to the task list.
     * Also saves the updated task list to storage and
     * returns a confirmation message.
     *
     * @param tasks the task list to which the deadline is added.
     * @param ui the user interface used to generate messages.
     * @param storage the storage handler that saves the updated task list.
     * @return the confirmation message after successfully adding the deadline
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        storage.save(tasks);
        return ui.getAddTaskMessage(task, tasks);
    }

    /**
     * Returns whether this command will exit the program.
     *
     * @return {@code false}, as adding a deadline does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
