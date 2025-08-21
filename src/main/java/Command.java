public interface Command {
    void execute(TaskList tasks, Ui ui) throws MomoException;
    boolean isExit();
}

