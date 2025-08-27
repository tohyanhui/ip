package momo.command;

import momo.storage.Storage;
import momo.task.Event;
import momo.task.Task;
import momo.task.TaskList;
import momo.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents a command that adds an {@link Event} task
 * to the task list.
 */
public class AddEventCommand implements Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates a new {@code AddEventCommand}.
     *
     * @param description the description of the event task.
     * @param from the start date and time of the event.
     * @param to the end date and time of the event.
     */
    public AddEventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds a new {@link Event} task to the task list.
     * Also saves the updated task list to storage and
     * displays a confirmation message to the user.
     *
     * @param tasks the task list to which the event is added.
     * @param ui the user interface that displays messages to the user.
     * @param storage the storage handler that saves the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        storage.save(tasks);
        ui.showAddTaskMessage(task, tasks);
    }

    /**
     * Returns whether this command will exit the program.
     *
     * @return {@code false}, as adding an event does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
