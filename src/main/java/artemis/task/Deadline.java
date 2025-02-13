package artemis.task;

import artemis.command.ArtemisDateTimeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Creates an instance of the Deadline object.
     */
    public Deadline(String description, String date, String time) throws ArtemisDateTimeException {
        super(description);

        try {
            this.date = LocalDate.parse(date);
            this.time = LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new ArtemisDateTimeException();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " " + time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }

    /**
     * Returns date of the deadline.
     *
     * @return Date of deadline.
     */
    public String getDate() {
        return date.toString();
    }

    /**
     * Returns time of the deadline.
     *
     * @return Time of the deadline.
     */
    public String getTime() {
        return time.toString();
    }
}
