package momo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " 
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + "\nto: " 
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }
    
    @Override
    public String convertToSaveFormat() {
        return "E | " + super.convertToSaveFormat() 
                + String.format(" | %s | %s",
                from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")), 
                to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }
}
