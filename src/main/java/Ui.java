import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String LOGO = " __  __\n"
            + "|  \\/  |\n"
            + "| \\  / | ___  _ __ ___   ___\n"
            + "| |\\/| |/ _ \\| '_ ` _ \\ / _ \\\n"
            + "| |  | | (_) | | | | | | (_) |\n"
            + "|_|  |_|\\___/|_| |_| |_|\\___/\n";
    private static final String MESSAGE_WELCOME = "Hello I'm\n" + LOGO + "\n" + "What can I do for you?";
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    private final Scanner scanner = new Scanner(System.in);


    // Indents every line of the text
    private static String indent(String text) {
        return "\t" + text.replace("\n", "\n\t");
    }

    // Adds a space to every line of the text
    private static String space(String text) {
        return " " + text.replace("\n", "\n ");
    }
    
    public void showLine() {
        System.out.println(indent(HORIZONTAL_LINE));
    }

    public void printPrettyMessage(String message) {
        showLine();
        System.out.println(indent(space(message)));
        showLine();
        System.out.println();
    }

    public void showBye() {
        printPrettyMessage(MESSAGE_BYE);
    }
    
    public void showWelcome() {
        printPrettyMessage(MESSAGE_WELCOME);
    }
    
    public String readCommand() {
        return scanner.nextLine().trim();
    }
    
    public void close() {
        scanner.close();
    }

    public String createAddTaskMessage(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n" +  space(space(task.toString()))
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    public String createDeleteTaskMessage(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n" +  space(space(task.toString()))
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
