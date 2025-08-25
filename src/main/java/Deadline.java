import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " 
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }

    @Override
    public String convertToSaveFormat() {
        return "D | " + super.convertToSaveFormat() 
                + String.format(" | %s", by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }
}
