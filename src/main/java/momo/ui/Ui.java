package momo.ui;

import momo.task.Task;
import momo.task.TaskList;

/**
 * Handles all user interactions in the Momo application.
 * Provides methods that return messages as strings.
 */
public class Ui {
    /** ASCII art logo of Momo displayed at startup. */
    private static final String LOGO = " __  __\n"
            + "|  \\/  |\n"
            + "| \\  / | ___  _ __ ___   ___\n"
            + "| |\\/| |/ _ \\| '_ ` _ \\ / _ \\\n"
            + "| |  | | (_) | | | | | | (_) |\n"
            + "|_|  |_|\\___/|_| |_| |_|\\___/\n";

    /** Message displayed when Momo is initializing. */
    private static final String MESSAGE_INITIALIZING = "Initializing Momo...";

    /** Message displayed after Momo has been initialized. */
    private static final String MESSAGE_INITIALIZED = "Momo initialized!";

    /** Welcome message displayed at startup. */
    private static final String MESSAGE_WELCOME = "Hello I'm\n" + LOGO + "\n" + "What can I do for you?";

    /** Farewell message displayed when exiting the application. */
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";

    /**
     * Adds a leading space to each line of a string.
     *
     * @param text the input text.
     * @return the text with added spaces.
     */
    private static String space(String text) {
        return " " + text.replace("\n", "\n ");
    }

    /**
     * Returns the farewell message when exiting the application.
     *
     * @return the farewell message as a string.
     */
    public String getByeMessage() {
        return MESSAGE_BYE;
    }

    /**
     * Returns the welcome message when starting the application.
     *
     * @return the welcome message as a string.
     */
    public String getWelcomeMessage() {
        return MESSAGE_WELCOME;
    }

    /**
     * Returns a message indicating that the application is initializing.
     *
     * @return the initializing message as a string.
     */
    public String getInitializingMessage() {
        return MESSAGE_INITIALIZING;
    }

    /**
     * Returns a message indicating that the application has been initialized.
     *
     * @return the initialized message as a string.
     */
    public String getInitializedMessage() {
        return MESSAGE_INITIALIZED;
    }

    /**
     * Returns a message indicating that saved tasks are being retrieved from disk.
     *
     * @return the retrieving data message as a string.
     */
    public String getRetrievingDataMessage() {
        return "Retrieving saved data from hard disk if any...";
    }

    /**
     * Returns a message indicating that loading saved data from disk has failed.
     *
     * @return the loading error message as a string.
     */
    public String getLoadingErrorMessage() {
        return "Error loading data from hard disk...\nDefaulting to fresh state...";
    }

    /**
     * Processes and returns the user's input.
     *
     * @param input the raw user input string.
     * @return the trimmed user input string.
     */
    public String readCommand(String input) {
        return input.trim();
    }

    /**
     * Returns a message confirming that a task has been added.
     *
     * @param task the task that was added.
     * @param tasks the current task list.
     * @return the message confirming task addition as a string.
     */
    public String getAddTaskMessage(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n" + space(space(task.toString()))
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Returns a message confirming that a task has been deleted.
     *
     * @param task the task that was deleted.
     * @param tasks the current task list.
     * @return the message confirming task deletion as a string.
     */
    public String getDeleteTaskMessage(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n" + space(space(task.toString()))
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
