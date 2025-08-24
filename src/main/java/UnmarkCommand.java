public class UnmarkCommand implements Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MomoException {
        if (index < 0 || index >= tasks.size()) {
            String errorDetail = "The task number provided is invalid!";
            String errorFix = "Fix: Retry \"unmark <task number>\" with a valid task number!";
            throw new MomoException(errorDetail + "\n" + errorFix);
        }
        tasks.unmarkTask(index);
        storage.save(tasks);
        ui.printPrettyMessage("OK, I've marked this task as not done yet:\n  " + tasks.getTask(index).toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
