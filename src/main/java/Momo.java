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

    public static void main(String[] args) {
        printPrettyMessage(MESSAGE_GREET);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            printPrettyMessage(input);
            input = scanner.nextLine();
        }
        scanner.close();

        printPrettyMessage(MESSAGE_BYE);
    }
}