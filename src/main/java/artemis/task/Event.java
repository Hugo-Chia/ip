package artemis.task;

import artemis.command.ArtemisDateTimeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    /**
     * Creates an instance of the Event object.
     */
    public Event(String description, String date, String startTime, String endTime) throws ArtemisDateTimeException {
        super(description);

        try {
            this.date = LocalDate.parse(date);
            this.startTime = LocalTime.parse(startTime);
            this.endTime = LocalTime.parse(endTime);
        } catch (DateTimeParseException e) {
            throw new ArtemisDateTimeException();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " " + startTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
                + " to: " + endTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }

    /**
     * Returns date of the event.
     *
     * @return Date of event.
     */
    public String getDate() {
        return date.toString();
    }

    /**
     * Returns start time of the event.
     *
     * @return Start time of the event.
     */
    public String getStartTime() {
        return startTime.toString();
    }

    /**
     * Returns end time of the event.
     *
     * @return End time of the event.
     */
    public String getEndTime() {
        return endTime.toString();
    }
}
