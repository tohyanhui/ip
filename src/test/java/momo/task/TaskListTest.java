package momo.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    private TaskList tasks;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
    }

    @Test
    public void addTask_increasesSize() {
        tasks.addTask(new Todo("read book", false));
        assertEquals(1, tasks.size());
    }

    @Test
    public void deleteTask_returnsAndRemovesCorrectTask() {
        Todo todo = new Todo("read book", false);
        tasks.addTask(todo);
        Task removed = tasks.deleteTask(0);
        assertEquals(todo, removed);
        assertEquals(0, tasks.size());
    }

    @Test
    public void getTask_returnsCorrectTask() {
        Todo todoHomework = new Todo("do homework", false);
        Todo todoCallJohn = new Todo("call John", false);
        Todo todoBuyGift = new Todo("buy gift", true);
        tasks.addTask(todoHomework);
        tasks.addTask(todoCallJohn);
        tasks.addTask(todoBuyGift);
        assertEquals(todoCallJohn, tasks.getTask(1));
    }

    @Test
    public void markAndUnmarkTask_updatesState() {
        Todo todo = new Todo("exercise", false);
        tasks.addTask(todo);

        tasks.markTask(0);
        assertTrue(tasks.getTask(0).isDone());

        tasks.unmarkTask(0);
        assertFalse(tasks.getTask(0).isDone());
    }

    @Test
    public void convertToSaveFormat_returnsJoinedString() {
        tasks.addTask(new Todo("read manga", false));
        tasks.addTask(new Todo("watch anime", true));
        String expected = String.join(System.lineSeparator(), "T | 0 | read manga", "T | 1 | watch anime");
        assertEquals(expected, tasks.convertToSaveFormat());
    }

    @Test
    public void clear_removesAllTasks() {
        tasks.addTask(new Todo("pay Henry", false));
        tasks.addTask(new Todo("cut hair", true));
        tasks.clear();
        assertEquals(0, tasks.size());
    }
}
