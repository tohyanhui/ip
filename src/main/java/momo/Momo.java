package momo;

import momo.command.Command;
import momo.exception.MomoException;
import momo.parser.Parser;
import momo.storage.Storage;
import momo.task.TaskList;
import momo.ui.Ui;

/**
 * Main class for the Momo application.
 * Handles the initialization of components, loading of saved tasks,
 * and the main input-processing loop.
 */
public class Momo {
    /** Handles saving and loading tasks to/from the local file system. */
    private final Storage storage;

    /** Stores the current list of tasks. */
    private final TaskList tasks;

    /** Handles all user interactions and displays messages. */
    private final Ui ui;

    /**
     * Initializes the Momo application.
     * Loads tasks from storage if available and displays initialization messages.
     */
    public Momo() {
        storage = new Storage();
        tasks = new TaskList();
        ui = new Ui();
        ui.showInitialising();
        try {
            storage.load(tasks);
            ui.showRetrievingData();
        } catch (MomoException e) {
            ui.showLoadingError();
        } finally {
            ui.showInitialised();
        }
    }

    /**
     * Runs the main loop of the application.
     * Continuously reads user commands, parses them, and executes them
     * until an exit command is received.
     */
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
                ui.showPrettyMessage(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * Entry point of the Momo application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Momo momo = new Momo();
        momo.run();
    }
}