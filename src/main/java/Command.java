public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws MomoException;
    boolean isExit();
}

