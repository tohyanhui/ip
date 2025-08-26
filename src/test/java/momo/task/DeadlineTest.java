package momo.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toString_done() {
        String description ="return book";
        LocalDateTime by = LocalDateTime.of(2025, 12, 2, 12, 0);
        boolean isDone = true;
        Deadline deadline = new Deadline(description, by, isDone);
        assertEquals("[D][X] return book (by: Dec 2 2025, 12:00pm)", deadline.toString());
    }

    @Test
    public void toString_notDone() {
        String description ="return book";
        LocalDateTime by = LocalDateTime.of(2025, 12, 2, 12, 0);
        boolean isDone = false;
        Deadline deadline = new Deadline(description, by, isDone);
        assertEquals("[D][ ] return book (by: Dec 2 2025, 12:00pm)", deadline.toString());
    }

    @Test
    public void convertToSaveFormat_done() {
        String description ="return book";
        LocalDateTime by = LocalDateTime.of(2025, 12, 2, 12, 0);
        boolean isDone = true;
        Deadline deadline = new Deadline(description, by, isDone);
        assertEquals("D | 1 | return book | 2025-12-02 1200", deadline.convertToSaveFormat());
    }

    @Test
    public void convertToSaveFormat_notDone() {
        String description ="return book";
        LocalDateTime by = LocalDateTime.of(2025, 12, 2, 12, 0);
        boolean isDone = false;
        Deadline deadline = new Deadline(description, by, isDone);
        assertEquals("D | 0 | return book | 2025-12-02 1200", deadline.convertToSaveFormat());
    }
}
