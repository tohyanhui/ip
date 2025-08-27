package momo.command;

import momo.exception.MomoException;
import momo.storage.Storage;
import momo.task.TaskList;
import momo.ui.Ui;

/**
 * Represents a command that marks a task in the task list as not done.
 */
public class UnmarkCommand implements Command {
    private final int index;

    /**
     * Creates a new {@code UnmarkCommand}.
     *
     * @param index the index of the task to be marked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the specified index in the task list as not done.
     * Also saves the updated task list to storage and
     * displays a confirmation message to the user.
     *
     * @param tasks the task list containing the task to unmark.
     * @param ui the user interface that displays messages to the user.
     * @param storage the storage handler that saves the updated task list.
     * @throws MomoException if the specified index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MomoException {
        if (index < 0 || index >= tasks.size()) {
            String errorDetail = "The task number provided is invalid!";
            String errorFix = "Fix: Retry \"unmark <task number>\" with a valid task number!";
            throw new MomoException(errorDetail + "\n" + errorFix);
        }
        tasks.unmarkTask(index);
        storage.save(tasks);
        ui.showPrettyMessage("OK, I've marked this task as not done yet:\n  " + tasks.getTask(index).toString());
    }

    /**
     * Returns whether this command will exit the program.
     *
     * @return {@code false}, as unmarking a task does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
