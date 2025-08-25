package momo.command;

import momo.storage.Storage;
import momo.task.TaskList;
import momo.ui.Ui;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
