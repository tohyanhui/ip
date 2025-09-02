package momo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


public class EventTest {
    @Test
    public void toString_done() {
        String description = "project meeting";
        LocalDateTime from = LocalDateTime.of(2025, 12, 2, 12, 0);
        LocalDateTime to = LocalDateTime.of(2025, 12, 2, 14, 0);
        boolean isDone = true;
        Event event = new Event(description, from, to, isDone);
        assertEquals("[E][X] project meeting (from: Dec 2 2025, 12:00pm\nto: Dec 2 2025, 2:00pm)",
                event.toString());
    }

    @Test
    public void toString_notDone() {
        String description = "project meeting";
        LocalDateTime from = LocalDateTime.of(2025, 12, 2, 12, 0);
        LocalDateTime to = LocalDateTime.of(2025, 12, 2, 14, 0);
        boolean isDone = false;
        Event event = new Event(description, from, to, isDone);
        assertEquals("[E][ ] project meeting (from: Dec 2 2025, 12:00pm\nto: Dec 2 2025, 2:00pm)",
                event.toString());
    }

    @Test
    public void convertToSaveFormat_done() {
        String description = "project meeting";
        LocalDateTime from = LocalDateTime.of(2025, 12, 2, 12, 0);
        LocalDateTime to = LocalDateTime.of(2025, 12, 2, 14, 0);
        boolean isDone = true;
        Event event = new Event(description, from, to, isDone);
        assertEquals("E | 1 | project meeting | 2025-12-02 1200 | 2025-12-02 1400",
                event.convertToSaveFormat());
    }

    @Test
    public void convertToSaveFormat_notDone() {
        String description = "project meeting";
        LocalDateTime from = LocalDateTime.of(2025, 12, 2, 12, 0);
        LocalDateTime to = LocalDateTime.of(2025, 12, 2, 14, 0);
        boolean isDone = false;
        Event event = new Event(description, from, to, isDone);
        assertEquals("E | 0 | project meeting | 2025-12-02 1200 | 2025-12-02 1400",
                event.convertToSaveFormat());
    }
}
