import java.util.Scanner;

public class Momo {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String LOGO = " __  __                       \n"
            + "|  \\/  |                      \n"
            + "| \\  / | ___  _ __ ___   ___  \n"
            + "| |\\/| |/ _ \\| '_ ` _ \\ / _ \\ \n"
            + "| |  | | (_) | | | | | | (_) |\n"
            + "|_|  |_|\\___/|_| |_| |_|\\___/ \n";
    private static final String MESSAGE_GREET = "Hello I'm\n" + LOGO + "\n" + "What can I do for you?";
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    private final String[] tasks = new String[100];
    private int numTasks = 0;


    // Indents every line of the text
    private static String indent(String text) {
        return "\t" + text.replace("\n", "\n\t");
    }

    private static void printPrettyMessage(String message) {
        System.out.println(indent(HORIZONTAL_LINE));
        System.out.println(indent(message));
        System.out.println(indent(HORIZONTAL_LINE));
        System.out.println();
    }

    private void addTask(String task) {
        tasks[numTasks] = task;
        numTasks++;
    }

    public static void main(String[] args) {
        Momo momo = new Momo();
        printPrettyMessage(MESSAGE_GREET);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (!input.equals("list")) { // Add task
                momo.addTask(input);
                printPrettyMessage("added: " + input);
            } else {
                printPrettyMessage(input);
            }
            input = scanner.nextLine();
        }
        scanner.close();

        printPrettyMessage(MESSAGE_BYE);
    }
}