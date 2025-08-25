package momo.command;

import momo.exception.MomoException;
import momo.storage.Storage;
import momo.task.TaskList;
import momo.ui.Ui;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws MomoException;
    boolean isExit();
}

