package momo.command;

import momo.storage.Storage;
import momo.task.TaskList;
import momo.ui.Ui;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String listMessage = IntStream.range(0, tasks.size())
                .mapToObj(x -> String.format("%d.%s", x + 1, tasks.getTask(x).toString()))
                .collect(Collectors.joining("\n"));
        ui.printPrettyMessage("Here are the tasks in your list:\n" + listMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
