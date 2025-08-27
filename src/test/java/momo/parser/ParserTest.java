package momo.parser;

import momo.command.AddDeadlineCommand;
import momo.command.AddEventCommand;
import momo.command.AddTodoCommand;
import momo.command.Command;
import momo.command.DeleteCommand;
import momo.command.ExitCommand;
import momo.command.FindCommand;
import momo.command.ListCommand;
import momo.command.MarkCommand;
import momo.command.UnmarkCommand;
import momo.exception.MomoException;
import momo.task.Deadline;
import momo.task.Event;
import momo.task.Task;
import momo.task.Todo;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parseToCommand_bye_success() throws MomoException {
        Command command = Parser.parseToCommand("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    public void parseToCommand_list_success() throws MomoException {
        Command command = Parser.parseToCommand("list");
        assertInstanceOf(ListCommand.class, command);
    }
    
    @Test
    public void parseToCommand_todo_success() throws MomoException {
        Command command = Parser.parseToCommand("todo read book");
        assertInstanceOf(AddTodoCommand.class, command);
    }

    @Test
    public void parseToCommand_todoMissingDescription_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("todo"));
        assertTrue(e.getMessage().contains("The description of the todo is empty"));
    }

    @Test
    public void parseToCommand_find_success() throws MomoException {
        Command command = Parser.parseToCommand("find book");
        assertInstanceOf(FindCommand.class, command);
    }

    @Test
    public void parseToCommand_findMissingKeyword_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("find"));
        assertTrue(e.getMessage().contains("The keyword to find is missing"));
    }

    @Test
    public void parseToCommand_deadline_success() throws MomoException {
        Command command = Parser.parseToCommand("deadline return book /by 2025-12-02 1800");
        assertInstanceOf(AddDeadlineCommand.class, command);
    }

    @Test
    public void parseToCommand_deadlineMissingDescription_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("deadline"));
        assertTrue(e.getMessage().contains("The description of the deadline is empty"));
    }

    @Test
    public void parseToCommand_deadlineMissingBy_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("deadline return book"));
        assertTrue(e.getMessage().contains("The deadline is missing \"/by\""));
    }

    @Test
    public void parseToCommand_deadlineInvalidDateTimeFormat_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("deadline return book /by Monday"));
        assertTrue(e.getMessage().contains("The format of date and time entered is invalid"));
    }

    @Test
    public void parseToCommand_event_success() throws MomoException {
        String trimmedInput = "event project meeting /from 2025-12-02 1200 /to 2025-12-02 1400";
        Command command = Parser.parseToCommand(trimmedInput);
        assertInstanceOf(AddEventCommand.class, command);
    }

    @Test
    public void parseToCommand_eventMissingDescription_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("event"));
        assertTrue(e.getMessage().contains("The description of the event is empty"));
    }
    
    @Test
    public void parseToCommand_eventMissingFrom_exceptionThrown() {
        String trimmedInput = "event project meeting /to 2025-12-02 1400";
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand(trimmedInput));
        assertTrue(e.getMessage().contains("The event is missing \"/from\""));
    }

    @Test
    public void parseToCommand_eventMissingTo_exceptionThrown() {
        String trimmedInput = "event project meeting /from 2025-12-02 1200";
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand(trimmedInput));
        assertTrue(e.getMessage().contains("The event is missing \"/to\""));
    }

    @Test
    public void parseToCommand_eventInvalidDateTimeFormat_exceptionThrown() {
        String trimmedInput = "event project meeting /from Monday 12pm /to 2pm";
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand(trimmedInput));
        assertTrue(e.getMessage().contains("The format of date and time entered is invalid"));
    }

    @Test
    public void parseToCommand_delete_success() throws MomoException {
        String trimmedInput = "delete 2";
        Command command = Parser.parseToCommand(trimmedInput);
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    public void parseToCommand_deleteMissingTaskNumber_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("delete"));
        assertTrue(e.getMessage().contains("The task number to delete is not provided"));
    }

    @Test
    public void parseToCommand_deleteTaskNumberNotInteger_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("delete two"));
        assertTrue(e.getMessage().contains("The task number provided is not an integer"));
    }

    @Test
    public void parseToCommand_mark_success() throws MomoException {
        String trimmedInput = "mark 2";
        Command command = Parser.parseToCommand(trimmedInput);
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void parseToCommand_markMissingTaskNumber_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("mark"));
        assertTrue(e.getMessage().contains("The task number to mark is not provided"));
    }

    @Test
    public void parseToCommand_markTaskNumberNotInteger_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("mark two"));
        assertTrue(e.getMessage().contains("The task number provided is not an integer"));
    }
    
    @Test
    public void parseToCommand_unmark_success() throws MomoException {
        String trimmedInput = "unmark 2";
        Command command = Parser.parseToCommand(trimmedInput);
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    public void parseToCommand_unmarkMissingTaskNumber_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("unmark"));
        assertTrue(e.getMessage().contains("The task number to unmark is not provided"));
    }

    @Test
    public void parseToCommand_unmarkTaskNumberNotInteger_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("unmark two"));
        assertTrue(e.getMessage().contains("The task number provided is not an integer"));
    }

    @Test
    public void parseToCommand_invalidCommand_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("blah"));
        assertTrue(e.getMessage().contains("is not a valid command"));
    }

    @Test
    public void parseToCommand_emptyInput_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand(""));
        assertTrue(e.getMessage().contains("is not a valid command"));
    }
    
    @Test
    public void parseToTask_todo_success() throws MomoException {
        Task task = Parser.parseToTask("T | 0 | read book");
        assertInstanceOf(Todo.class, task);
        assertEquals("read book", task.getDescription());
        assertFalse(task.isDone());
    }

    @Test
    public void parseToTask_deadline_success() throws MomoException {
        Task task = Parser.parseToTask("D | 1 | return book | 2025-12-02 1800");
        assertInstanceOf(Deadline.class, task);
        assertEquals("return book", task.getDescription());
        assertTrue(task.isDone());
        assertEquals(LocalDateTime.of(2025, 12, 2, 18, 0), 
                ((Deadline) task).getBy());
    }

    @Test
    public void parseToTask_event_success() throws MomoException {
        Task task = Parser.parseToTask("E | 0 | meeting | 2025-12-02 1200 | 2025-12-02 1400");
        assertInstanceOf(Event.class, task);
        assertEquals("meeting", task.getDescription());
        assertFalse(task.isDone());
        assertEquals(LocalDateTime.of(2025, 12, 2, 12, 0),
                ((Event) task).getFrom());
        assertEquals(LocalDateTime.of(2025, 12, 2, 14, 0),
                ((Event) task).getTo());
    }

    @Test
    void parseToTask_invalidTaskType_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToTask("L | 0 | meeting"));
        assertTrue(e.getMessage().contains("Invalid task type found"));
    }

    @Test
    void parseToTask_missingData_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToTask("E | 0 | meeting"));
        assertTrue(e.getMessage().contains("Missing data from tasks"));
    }

    @Test
    void parseToTask_invalidDateTimeFormat_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, 
                () -> Parser.parseToTask("E | 0 | meeting | Monday 2pm | 4pm"));
        assertTrue(e.getMessage().contains("Format of date and time is invalid"));
    }
    
    @Test
    public void parseToTask_emptyInput_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToTask(""));
        assertTrue(e.getMessage().contains("Missing data from tasks"));
    }
}
