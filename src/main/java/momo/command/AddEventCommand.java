package momo.command;

import momo.storage.Storage;
import momo.task.Event;
import momo.task.Task;
import momo.task.TaskList;
import momo.ui.Ui;

import java.time.LocalDateTime;

public class AddEventCommand implements Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;
    
    public AddEventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        storage.save(tasks);
        ui.printAddTaskMessage(task, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
