import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    private final Task[] tasks = new Task[100];
    private int numTasks = 0;


    // Indents every line of the text
    private static String indent(String text) {
        return "\t" + text.replace("\n", "\n\t");
    }

    // Adds a space to every line of the text
    private static String space(String text) {
        return " " + text.replace("\n", "\n ");
    }

    private static void printPrettyMessage(String message) {
        System.out.println(indent(HORIZONTAL_LINE));
        System.out.println(indent(space(message)));
        System.out.println(indent(HORIZONTAL_LINE));
        System.out.println();
    }

    private void addTask(Task task) {
        tasks[numTasks] = task;
        numTasks++;
    }

    private void markTask(int index) {
        tasks[index].markAsDone();
    }

    private void unmarkTask(int index) {
        tasks[index].unmarkFromDone();
    }

    private String createAddTaskMessage(Task task) {
        return "Got it. I've added this task:\n" +  space(space(task.toString())) + "\nNow you have " + this.numTasks + " tasks in the list.";
    }

    public static void main(String[] args) {
        Momo momo = new Momo();
        printPrettyMessage(MESSAGE_GREET);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                int numTasks = momo.numTasks;
                Task[] tasks = momo.tasks;
                String listMessage = IntStream.range(0, numTasks)
                        .mapToObj(x -> String.format("%d.%s", x + 1, tasks[x].toString()))
                        .collect(Collectors.joining("\n"));
                printPrettyMessage("Here are the tasks in your list:\n" + listMessage);
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                momo.markTask(index);
                printPrettyMessage("Nice! I've marked this task as done:\n  " + momo.tasks[index].toString());
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                momo.unmarkTask(index);
                printPrettyMessage("OK, I've marked this task as not done yet:\n  " + momo.tasks[index].toString());
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                Task task = new Todo(description);
                momo.addTask(task);
                printPrettyMessage(momo.createAddTaskMessage(task));
            } else if (input.startsWith("deadline ")) {
                String[] parsedInput = input.substring(9).split(" /by ");
                Task task = new Deadline(parsedInput[0], parsedInput[1]);
                momo.addTask(task);
                printPrettyMessage(momo.createAddTaskMessage(task));
            } else if (input.startsWith("event ")) {
                String[] parsedInput = input.substring(6).split(" /from ");
                String[] parsedStartEndTime = parsedInput[1].split(" /to ");
                Task task = new Event(parsedInput[0], parsedStartEndTime[0], parsedStartEndTime[1]);
                momo.addTask(task);
                printPrettyMessage(momo.createAddTaskMessage(task));
            }
            input = scanner.nextLine();
        }
        scanner.close();

        printPrettyMessage(MESSAGE_BYE);
    }
}