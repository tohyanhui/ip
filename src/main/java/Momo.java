import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Momo {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String LOGO = " __  __\n"
            + "|  \\/  |\n"
            + "| \\  / | ___  _ __ ___   ___\n"
            + "| |\\/| |/ _ \\| '_ ` _ \\ / _ \\\n"
            + "| |  | | (_) | | | | | | (_) |\n"
            + "|_|  |_|\\___/|_| |_| |_|\\___/\n";
    private static final String MESSAGE_GREET = "Hello I'm\n" + LOGO + "\n" + "What can I do for you?";
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    private final ArrayList<Task> tasks = new ArrayList<>();

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
        tasks.add(task);
    }

    private Task deleteTask(int index) {
        return tasks.remove(index);
    }
    
    private Task getTask(int index) {
        return tasks.get(index);
    }
    
    private void markTask(int index) {
        getTask(index).markAsDone();
    }

    private void unmarkTask(int index) {
        getTask(index).unmarkFromDone();
    }
    
    private int computeNumTasks() {
        return tasks.size();
    }

    private String createAddTaskMessage(Task task) {
        return "Got it. I've added this task:\n" +  space(space(task.toString())) 
                + "\nNow you have " + computeNumTasks() + " tasks in the list.";
    }

    private String createDeleteTaskMessage(Task task) {
        return "Noted. I've removed this task:\n" +  space(space(task.toString()))
                + "\nNow you have " + computeNumTasks() + " tasks in the list.";
    }

    public static void main(String[] args) {
        Momo momo = new Momo();
        printPrettyMessage(MESSAGE_GREET);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    int numTasks = momo.computeNumTasks();
                    String listMessage = IntStream.range(0, numTasks)
                            .mapToObj(x -> String.format("%d.%s", x + 1, momo.getTask(x).toString()))
                            .collect(Collectors.joining("\n"));
                    printPrettyMessage("Here are the tasks in your list:\n" + listMessage);
                } else if (input.equals("delete")) {
                    String errorDetail = "The task number to delete is not provided!";
                    String errorFix = "Fix: Try \"delete <task number>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                } else if (input.startsWith("delete ")) {
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        Task deletedTask = momo.deleteTask(index);
                        printPrettyMessage(momo.createDeleteTaskMessage(deletedTask));
                    } catch (NumberFormatException e) {
                        String errorDetail = "The task number provided is not an integer!";
                        String errorFix = "Fix: Try \"mark <integer>\" instead!";
                        throw new MomoException(errorDetail + "\n" + errorFix);
                    }
                } else if (input.equals("mark")) {
                    String errorDetail = "The task number to mark is not provided!";
                    String errorFix = "Fix: Try \"mark <task number>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                } else if (input.startsWith("mark ")) {
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        momo.markTask(index);
                        printPrettyMessage("Nice! I've marked this task as done:\n  " + momo.getTask(index).toString());
                    } catch (NumberFormatException e) {
                        String errorDetail = "The task number provided is not an integer!";
                        String errorFix = "Fix: Try \"mark <integer>\" instead!";
                        throw new MomoException(errorDetail + "\n" + errorFix);
                    }
                } else if (input.equals("unmark")) {
                    String errorDetail = "The task number to unmark is not provided!";
                    String errorFix = "Fix: Try \"unmark <task number>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                } else if (input.startsWith("unmark ")) {
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        momo.unmarkTask(index);
                        printPrettyMessage("OK, I've marked this task as not done yet:\n  " 
                                + momo.getTask(index).toString());
                    } catch (NumberFormatException e) {
                        String errorDetail = "The task number provided is not an integer!";
                        String errorFix = "Fix: Try \"unmark <integer>\" instead!";
                        throw new MomoException(errorDetail + "\n" + errorFix);
                    }
                } else if (input.equals("todo")) {
                    String errorDetail = "The description of the todo is empty!";
                    String errorFix = "Fix: Try \"todo <description>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                } else if (input.startsWith("todo ")) {
                    String description = input.substring(5);
                    Task task = new Todo(description);
                    momo.addTask(task);
                    printPrettyMessage(momo.createAddTaskMessage(task));
                } else if (input.equals("deadline")) {
                    String errorDetail = "The description of the deadline is empty!";
                    String errorFix = "Fix: Try \"deadline <description> /by <date/time>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                } else if (input.startsWith("deadline ")) {
                    String[] parsedInput = input.substring(9).split(" /by ");
                    try {
                        Task task = new Deadline(parsedInput[0], parsedInput[1]);
                        momo.addTask(task);
                        printPrettyMessage(momo.createAddTaskMessage(task));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        String errorDetail = "The deadline is missing \"/by\"!";
                        String errorFix = "Fix: Try \"deadline <description> /by <date/time>\" instead!";
                        throw new MomoException(errorDetail + "\n" + errorFix);
                    }
                } else if (input.equals("event")) {
                    String errorDetail = "The description of the event is empty!";
                    String errorFix = "Fix: Try \"event <description> /from <date/time> /to\n<date/time>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                } else if (input.startsWith("event ")) {
                    String[] parsedInput = input.substring(6).split(" /from ");
                    try {
                        String[] parsedStartEndTime = parsedInput[1].split(" /to ");
                        Task task = new Event(parsedInput[0], parsedStartEndTime[0], parsedStartEndTime[1]);
                        momo.addTask(task);
                        printPrettyMessage(momo.createAddTaskMessage(task));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        String errorDetail = "The event is missing \"/from\" or \"/to\"!";
                        String errorFix = "Fix: Try \"event <description> /from <date/time> " +
                                "/to\n<date/time>\" instead!";
                        throw new MomoException(errorDetail + "\n" + errorFix);
                    }
                } else {
                    String errorDetail = "\"" + input + "\"" + " is not a valid command!";
                    String errorFix = "Fix: Retry with a valid command!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                }
            } catch (MomoException e) {
                printPrettyMessage(e.getMessage());
            }
            input = scanner.nextLine().trim();
        }
        scanner.close();

        printPrettyMessage(MESSAGE_BYE);
    }
}