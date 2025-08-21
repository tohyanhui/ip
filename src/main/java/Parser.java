public class Parser {
    public static Command parse(String trimmedInput) throws MomoException {
        String[] parsedInput = trimmedInput.split(" ", 2);
        String command = parsedInput[0];
        
        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
                if (parsedInput.length < 2) {
                    String errorDetail = "The description of the todo is empty!";
                    String errorFix = "Fix: Try \"todo <description>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                }
                return new AddTodoCommand(parsedInput[1]);
            case "deadline":
                if (parsedInput.length < 2) {
                    String errorDetail = "The description of the deadline is empty!";
                    String errorFix = "Fix: Try \"deadline <description> /by <date/time>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                }
                String[] parsedDeadline = parsedInput[1].split(" /by ", 2);
                if (parsedDeadline.length < 2) {
                    String errorDetail = "The deadline is missing \"/by\"!";
                    String errorFix = "Fix: Try \"deadline <description> /by <date/time>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                }
                return new AddDeadlineCommand(parsedDeadline[0], parsedDeadline[1]);
            case "event":
                if (parsedInput.length < 2) {
                    String errorDetail = "The description of the event is empty!";
                    String errorFix = "Fix: Try \"event <description> /from <date/time> /to\n<date/time>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                }
                String[] parsedEvent = parsedInput[1].split(" /from ", 2);
                if (parsedEvent.length < 2) {
                    String errorDetail = "The event is missing \"/from\"!";
                    String errorFix = "Fix: Try \"event <description> /from <date/time> " +
                            "/to\n<date/time>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                }
                String[] parsedStartEndTime = parsedEvent[1].split(" /to ", 2);
                if (parsedStartEndTime.length < 2) {
                    String errorDetail = "The event is missing \"/to\"!";
                    String errorFix = "Fix: Try \"event <description> /from <date/time> " +
                            "/to\n<date/time>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                }
                return new AddEventCommand(parsedEvent[0], parsedStartEndTime[0], parsedStartEndTime[1]);
            case "delete":
                if (parsedInput.length < 2) {
                    String errorDetail = "The task number to delete is not provided!";
                    String errorFix = "Fix: Try \"delete <task number>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                }
                try {
                    int index = Integer.parseInt(parsedInput[1]) - 1;
                    return new DeleteCommand(index);
                } catch (NumberFormatException e) {
                    String errorDetail = "The task number provided is not an integer!";
                    String errorFix = "Fix: Try \"delete <integer>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                }
            case "mark":
                if (parsedInput.length < 2) {
                    String errorDetail = "The task number to mark is not provided!";
                    String errorFix = "Fix: Try \"mark <task number>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                }
                try {
                    int index = Integer.parseInt(parsedInput[1]) - 1;
                    return new MarkCommand(index);
                } catch (NumberFormatException e) {
                    String errorDetail = "The task number provided is not an integer!";
                    String errorFix = "Fix: Try \"mark <integer>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                }
            case "unmark":
                if (parsedInput.length < 2) {
                    String errorDetail = "The task number to unmark is not provided!";
                    String errorFix = "Fix: Try \"unmark <task number>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                }
                try {
                    int index = Integer.parseInt(parsedInput[1]) - 1;
                    return new UnmarkCommand(index);
                } catch (NumberFormatException e) {
                    String errorDetail = "The task number provided is not an integer!";
                    String errorFix = "Fix: Try \"unmark <integer>\" instead!";
                    throw new MomoException(errorDetail + "\n" + errorFix);
                }
            default:
                String errorDetail = "\"" + trimmedInput + "\"" + " is not a valid command!";
                String errorFix = "Fix: Retry with a valid command!";
                throw new MomoException(errorDetail + "\n" + errorFix);
        }
    }
}
