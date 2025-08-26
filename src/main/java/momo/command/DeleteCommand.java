package momo.command;

import momo.exception.MomoException;
import momo.storage.Storage;
import momo.task.Task;
import momo.task.TaskList;
import momo.ui.Ui;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MomoException {
        if (index < 0 || index >= tasks.size()) {
            String errorDetail = "The task number provided is invalid!";
            String errorFix = "Fix: Retry \"delete <task number>\" with a valid task number!";
            throw new MomoException(errorDetail + "\n" + errorFix);
        }
        Task deletedTask = tasks.deleteTask(index);
        storage.save(tasks);
        ui.showDeleteTaskMessage(deletedTask, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}