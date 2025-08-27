package momo.command;

import momo.exception.MomoException;
import momo.storage.Storage;
import momo.task.Task;
import momo.task.TaskList;
import momo.ui.Ui;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindCommand implements Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MomoException {
        List<Task> foundTasks= tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .toList();
        
        String foundTasksMessage = IntStream.range(0, foundTasks.size())
                .mapToObj(x -> String.format("%d.%s", x + 1, foundTasks.get(x).toString()))
                .collect(Collectors.joining("\n"));
    
        ui.showPrettyMessage("Here are the matching tasks in your list:\n" + foundTasksMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
