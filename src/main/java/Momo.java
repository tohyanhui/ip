import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Momo {
    private final TaskList tasks = new TaskList();
    private final Ui ui = new Ui();
    
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                if (input.equals("list")) {
                    new ListCommand().execute(tasks, ui);
                } else if (input.equals("bye")) {
                    new ExitCommand().execute(tasks, ui);
                    isExit = true;
                } else if (input.equals("delete")) {
                    String errorDetail = "The task number to delete is not provided!";
                    String errorFix = "Fix: Try \"delete <task number>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                } else if (input.startsWith("delete ")) {
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        new DeleteCommand(index).execute(tasks, ui);
                    } catch (NumberFormatException e) {
                        String errorDetail = "The task number provided is not an integer!";
                        String errorFix = "Fix: Try \"delete <integer>\" instead!";
                        throw new MomoException(errorDetail + "\n" + errorFix);
                    }
                } else if (input.equals("mark")) {
                    String errorDetail = "The task number to mark is not provided!";
                    String errorFix = "Fix: Try \"mark <task number>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                } else if (input.startsWith("mark ")) {
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        new MarkCommand(index).execute(tasks, ui);
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
                        new UnmarkCommand(index).execute(tasks, ui);
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
                    new AddTodoCommand(description).execute(tasks, ui);
                } else if (input.equals("deadline")) {
                    String errorDetail = "The description of the deadline is empty!";
                    String errorFix = "Fix: Try \"deadline <description> /by <date/time>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                } else if (input.startsWith("deadline ")) {
                    String[] parsedInput = input.substring(9).split(" /by ");
                    try {
                        new AddDeadlineCommand(parsedInput[0], parsedInput[1]).execute(tasks, ui);
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
                        new AddEventCommand(parsedInput[0], parsedStartEndTime[0], parsedStartEndTime[1]).execute(tasks, ui);
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