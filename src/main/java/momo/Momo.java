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

    private String commandType;

    /**
     * Initializes the Momo application.
     * Loads tasks from storage if available and displays initialization messages.
     */
    public Momo() {
        storage = new Storage();
        tasks = new TaskList();
        ui = new Ui();
        ui.getInitializingMessage();
        try {
            storage.load(tasks);
        } catch (MomoException e) {
            ui.getLoadingErrorMessage();
        } finally {
            ui.getInitializedMessage();
        }
    }

    /**
     * Determines whether the given user input corresponds to an exit command.
     *
     * @param input the raw user input string.
     * @return {@code true} if the input corresponds to a command that exits the application,
     *         {@code false} otherwise.
     */
    public boolean isExit(String input) {
        try {
            String trimmedInput = ui.readCommand(input);
            Command command = Parser.parseToCommand(trimmedInput);
            return command.isExit();
        } catch (MomoException e) {
            return false;
        }
    }

    /**
     * Returns the welcome message when starting the application.
     *
     * @return the welcome message as a string.
     */
    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            String trimmedInput = ui.readCommand(input);
            Command command = Parser.parseToCommand(trimmedInput);
            String response = command.execute(tasks, ui, storage);
            commandType = command.getClass().getSimpleName();
            return response;
        } catch (MomoException e) {
            commandType = "Error";
            return e.getMessage();
        }
    }

    public String getCommandType() {
        return commandType;
    }
}