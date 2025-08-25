import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static LocalDateTime parseToLocalDateTime(String localDateTime) {
        return LocalDateTime.parse(localDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
    
    public static Command parseToCommand(String trimmedInput) throws MomoException {
        String[] components = trimmedInput.split(" ", 2);
        String command = components[0];

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            if (components.length < 2) {
                String errorDetail = "The description of the todo is empty!";
                String errorFix = "Fix: Try \"todo <description>\" instead!";
                throw new MomoException(errorDetail + "\n" + errorFix);
            }
            return new AddTodoCommand(components[1]);
        case "deadline":
            if (components.length < 2) {
                String errorDetail = "The description of the deadline is empty!";
                String errorFix = "Fix: Try \"deadline <description> /by <date/time>\" instead!";
                throw new MomoException(errorDetail + "\n" + errorFix);
            }
            String[] parsedDeadline = components[1].split(" /by ", 2);
            if (parsedDeadline.length < 2) {
                String errorDetail = "The deadline is missing \"/by\"!";
                String errorFix = "Fix: Try \"deadline <description> /by <date/time>\" instead!";
                throw new MomoException(errorDetail + "\n" + errorFix);
            }
            try {
                String description = parsedDeadline[0];
                LocalDateTime by = parseToLocalDateTime(parsedDeadline[1]);
                return new AddDeadlineCommand(description, by);
            } catch (DateTimeParseException e) {
                String errorDetail = "The format of date and time entered is invalid!";
                String errorFix = "Fix: Try \"deadline <description> /by <yyyy-MM-dd HHmm>\"\ninstead!";
                String example = "Example: deadline return book /by 2025-04-07 1805";
                throw new MomoException(errorDetail + "\n" + errorFix + "\n" + example);
            }
        case "event":
            if (components.length < 2) {
                String errorDetail = "The description of the event is empty!";
                String errorFix = "Fix: Try \"event <description> /from <date/time> /to\n<date/time>\" instead!";
                throw new MomoException(errorDetail + "\n" + errorFix);
            }
            String[] parsedEvent = components[1].split(" /from ", 2);
            if (parsedEvent.length < 2) {
                String errorDetail = "The event is missing \"/from\"!";
                String errorFix = "Fix: Try \"event <description> /from <date/time> /to\n<date/time>\" instead!";
                throw new MomoException(errorDetail + "\n" + errorFix);
            }
            String[] parsedStartEndTime = parsedEvent[1].split(" /to ", 2);
            if (parsedStartEndTime.length < 2) {
                String errorDetail = "The event is missing \"/to\"!";
                String errorFix = "Fix: Try \"event <description> /from <date/time> /to\n<date/time>\" instead!";
                throw new MomoException(errorDetail + "\n" + errorFix);
            }
            try {
                String description = parsedEvent[0];
                LocalDateTime from = parseToLocalDateTime(parsedStartEndTime[0]);
                LocalDateTime to = parseToLocalDateTime(parsedStartEndTime[1]);
                return new AddEventCommand(description, from, to);
            } catch (DateTimeParseException e) {
                String errorDetail = "The format of date and time entered is invalid!";
                String errorFix = "Fix: Try \"event <description> /from <yyyy-MM-dd HHmm>" 
                        + "\n/to <yyyy-MM-dd HHmm>\" instead!";
                String example = "Example: event project meeting /from 2025-04-07 1230\n/to 2025-04-07 1330";
                throw new MomoException(errorDetail + "\n" + errorFix + "\n" + example);
            }
        case "delete":
            if (components.length < 2) {
                String errorDetail = "The task number to delete is not provided!";
                String errorFix = "Fix: Try \"delete <task number>\" instead!";
                throw new MomoException(errorDetail + "\n" + errorFix);
            }
            try {
                int index = Integer.parseInt(components[1]) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                String errorDetail = "The task number provided is not an integer!";
                String errorFix = "Fix: Try \"delete <integer>\" instead!";
                throw new MomoException(errorDetail + "\n" + errorFix);
            }
        case "mark":
            if (components.length < 2) {
                String errorDetail = "The task number to mark is not provided!";
                String errorFix = "Fix: Try \"mark <task number>\" instead!";
                throw new MomoException(errorDetail + "\n" + errorFix);
            }
            try {
                int index = Integer.parseInt(components[1]) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                String errorDetail = "The task number provided is not an integer!";
                String errorFix = "Fix: Try \"mark <integer>\" instead!";
                throw new MomoException(errorDetail + "\n" + errorFix);
            }
        case "unmark":
            if (components.length < 2) {
                String errorDetail = "The task number to unmark is not provided!";
                String errorFix = "Fix: Try \"unmark <task number>\" instead!";
                throw new MomoException(errorDetail + "\n" + errorFix);
            }
            try {
                int index = Integer.parseInt(components[1]) - 1;
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
    
    public static Task parseToTask(String line) throws MomoException {
        try {
            String[] components = line.split(" \\| ");
            String taskType = components[0];
            boolean isDone = components[1].equals("1");
            String description = components[2];

            switch (taskType) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                String by = components[3];
                return new Deadline(description, parseToLocalDateTime(by), isDone);
            case "E":
                String from = components[3];
                String to = components[4];
                return new Event(description, parseToLocalDateTime(from), parseToLocalDateTime(to), isDone);
            default:
                throw new MomoException("Invalid task type found!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MomoException("Missing data from tasks!");
        } catch (DateTimeParseException e) {
            throw new MomoException("Format of date and time is invalid!");
        }
    }
}
