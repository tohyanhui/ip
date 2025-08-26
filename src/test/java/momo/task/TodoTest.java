package momo.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toString_done() {
        String description ="read book";
        boolean isDone = true;
        Todo todo = new Todo(description, isDone);
        assertEquals("[T][X] read book", todo.toString());
    }

    @Test
    public void toString_notDone() {
        String description ="read book";
        boolean isDone = false;
        Todo todo = new Todo(description, isDone);
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void convertToSaveFormat_done() {
        String description ="read book";
        boolean isDone = true;
        Todo todo = new Todo(description, isDone);
        assertEquals("T | 1 | read book", todo.convertToSaveFormat());
    }

    @Test
    public void convertToSaveFormat_notDone() {
        String description ="read book";
        boolean isDone = false;
        Todo todo = new Todo(description, isDone);
        assertEquals("T | 0 | read book", todo.convertToSaveFormat());
    }
}
