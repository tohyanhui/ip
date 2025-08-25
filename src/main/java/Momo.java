public class Momo {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    
    public Momo() {
        storage = new Storage();
        tasks = new TaskList();
        ui = new Ui();
        ui.showInitialising();
        try {
            storage.load(tasks);
        } catch (MomoException e) {
            ui.showLoadingError();
        } finally {
            ui.showInitialised();
        }
    }
    
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String trimmedInput = ui.readCommand();
                Command command = Parser.parseToCommand(trimmedInput);
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