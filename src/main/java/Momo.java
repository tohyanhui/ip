public class Momo {
    private final Storage storage = new Storage();
    private final TaskList tasks = new TaskList();
    private final Ui ui = new Ui();
    
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String trimmedInput = ui.readCommand();
                Command command = Parser.parse(trimmedInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (MomoException e) {
                ui.printPrettyMessage(e.getMessage());
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        Momo momo = new Momo();
        momo.run();
    }
}