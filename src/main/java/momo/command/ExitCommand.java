package momo.command;

import momo.storage.Storage;
import momo.task.TaskList;
import momo.ui.Ui;

/**
 * Represents a command that exits the application.
 */
public class ExitCommand implements Command {

    /**
     * Displays the goodbye message to the user.
     *
     * @param tasks the task list (not used in this command).
     * @param ui the user interface that displays messages to the user.
     * @param storage the storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Returns whether this command will exit the program.
     *
     * @return {@code true}, as this command exits the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
