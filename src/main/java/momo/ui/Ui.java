package momo.ui;

import java.util.Scanner;

import momo.task.Task;
import momo.task.TaskList;

/**
 * Handles all user interactions in the Momo application.
 * Provides methods to display messages, read input, and format output.
 */
public class Ui {
    /** Horizontal line used as a visual separator in messages. */
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    /** ASCII art logo of Momo displayed at startup. */
    private static final String LOGO = " __  __\n"
            + "|  \\/  |\n"
            + "| \\  / | ___  _ __ ___   ___\n"
            + "| |\\/| |/ _ \\| '_ ` _ \\ / _ \\\n"
            + "| |  | | (_) | | | | | | (_) |\n"
            + "|_|  |_|\\___/|_| |_| |_|\\___/\n";

    /** Message displayed when Momo is initializing. */
    private static final String MESSAGE_INITIALISING = "Initialising Momo...";

    /** Message displayed after Momo has been initialized. */
    private static final String MESSAGE_INITIALISED = "Momo initialised!";

    /** Welcome message displayed at startup. */
    private static final String MESSAGE_WELCOME = "Hello I'm\n" + LOGO + "\n" + "What can I do for you?";

    /** Farewell message displayed when exiting the application. */
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Indents each line of a string with a tab character.
     *
     * @param text the input text
     * @return the indented text
     */
    private static String indent(String text) {
        return "\t" + text.replace("\n", "\n\t");
    }

    /**
     * Adds a leading space to each line of a string.
     *
     * @param text the input text
     * @return the text with added spaces
     */
    private static String space(String text) {
        return " " + text.replace("\n", "\n ");
    }

    /**
     * Prints a horizontal separator line.
     */
    public void showLine() {
        System.out.println(indent(HORIZONTAL_LINE));
    }

    /**
     * Prints a formatted message with horizontal lines and indentation.
     *
     * @param message the message to display
     */
    public void showPrettyMessage(String message) {
        showLine();
        System.out.println(indent(space(message)));
        showLine();
        System.out.println();
    }

    /**
     * Prints the farewell message when exiting the application.
     */
    public void showBye() {
        showPrettyMessage(MESSAGE_BYE);
    }

    /**
     * Prints the welcome message when starting the application.
     */
    public void showWelcome() {
        showPrettyMessage(MESSAGE_WELCOME);
    }

    /**
     * Displays a message indicating that the application is initializing.
     */
    public void showInitialising() {
        System.out.println(MESSAGE_INITIALISING);
    }

    /**
     * Displays a message indicating that the application has been initialized.
     */
    public void showInitialised() {
        System.out.println(MESSAGE_INITIALISED);
    }

    /**
     * Displays a message when retrieving saved tasks from disk.
     */
    public void showRetrievingData() {
        System.out.println("Retrieving saved data from hard disk if any...");
    }

    /**
     * Displays a message if loading saved data from disk fails.
     */
    public void showLoadingError() {
        System.out.println("Error loading data from hard disk...\nDefaulting to fresh state...");
    }

    /**
     * Reads a line of user input from the console.
     *
     * @return the trimmed user input
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Closes the underlying scanner.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task the task that was added
     * @param tasks the current task list
     */
    public void showAddTaskMessage(Task task, TaskList tasks) {
        showPrettyMessage("Got it. I've added this task:\n" + space(space(task.toString()))
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task the task that was deleted
     * @param tasks the current task list
     */
    public void showDeleteTaskMessage(Task task, TaskList tasks) {
        showPrettyMessage("Noted. I've removed this task:\n" + space(space(task.toString()))
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
