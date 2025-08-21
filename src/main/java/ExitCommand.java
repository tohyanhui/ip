public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showBye();
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
