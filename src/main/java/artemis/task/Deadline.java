package artemis.task;

import artemis.command.ArtemisException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, String date, String time) throws ArtemisException {
        super(description);
        //this.by = by;

        try {
            this.date = LocalDate.parse(date);
            this.time = LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new ArtemisException("Error with Date-Time Format, please try again! \n");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " " + time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }

    public String getBy() {
        return by;
    }

    public String getDate() {
        return date.toString();
    }

    public String getTime() {
        return time.toString();
    }
}
