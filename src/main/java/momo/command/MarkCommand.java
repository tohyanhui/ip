package momo.command;

import momo.exception.MomoException;
import momo.storage.Storage;
import momo.task.TaskList;
import momo.ui.Ui;

/**
 * Represents a command that marks a task in the task list as done.
 */
public class MarkCommand implements Command {
    private final int index;

    /**
     * Creates a new {@code MarkCommand}.
     *
     * @param index the index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the specified index in the task list as done.
     * Also saves the updated task list to storage and
     * displays a confirmation message to the user.
     *
     * @param tasks the task list containing the task to mark.
     * @param ui the user interface that displays messages to the user.
     * @param storage the storage handler that saves the updated task list.
     * @throws MomoException if the specified index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MomoException {
        if (index < 0 || index >= tasks.size()) {
            String errorDetail = "The task number provided is invalid!";
            String errorFix = "Fix: Retry \"mark <task number>\" with a valid task number!";
            throw new MomoException(errorDetail + "\n" + errorFix);
        }
        tasks.markTask(index);
        storage.save(tasks);
        ui.printPrettyMessage("Nice! I've marked this task as done:\n  " + tasks.getTask(index).toString());
    }

    /**
     * Returns whether this command will exit the program.
     *
     * @return {@code false}, as marking a task does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
