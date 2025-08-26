package momo.command;

import momo.exception.MomoException;
import momo.storage.Storage;
import momo.task.TaskList;
import momo.ui.Ui;

public class MarkCommand implements Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MomoException {
        if (index < 0 || index >= tasks.size()) {
            String errorDetail = "The task number provided is invalid!";
            String errorFix = "Fix: Retry \"mark <task number>\" with a valid task number!";
            throw new MomoException(errorDetail + "\n" + errorFix);
        }
        tasks.markTask(index);
        storage.save(tasks);
        ui.showPrettyMessage("Nice! I've marked this task as done:\n  " + tasks.getTask(index).toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
