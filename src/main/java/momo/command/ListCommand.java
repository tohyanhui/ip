package momo.command;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import momo.storage.Storage;
import momo.task.TaskList;
import momo.ui.Ui;

/**
 * Represents a command that lists all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Displays all tasks in the task list to the user.
     * Tasks are numbered starting from 1.
     *
     * @param tasks the task list whose tasks are displayed.
     * @param ui the user interface that displays messages to the user.
     * @param storage the storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String listMessage = IntStream.range(0, tasks.size())
                .mapToObj(x -> String.format("%d.%s", x + 1, tasks.getTask(x).toString()))
                .collect(Collectors.joining("\n"));
        ui.showPrettyMessage("Here are the tasks in your list:\n" + listMessage);
    }

    /**
     * Returns whether this command will exit the program.
     *
     * @return {@code false}, as listing tasks does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
