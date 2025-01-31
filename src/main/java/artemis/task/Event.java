package artemis.task;

import artemis.command.ArtemisException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String from;
    protected String to;

    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public Event(String description, String date, String startTime, String endTime) throws ArtemisException {
        super(description);
        //this.from = from;
        //this.to = to;

        try {
            this.date = LocalDate.parse(date);
            this.startTime = LocalTime.parse(startTime);
            this.endTime = LocalTime.parse(endTime);
        } catch (DateTimeParseException e) {
            throw new ArtemisException("Error with Date-Time Format, please try again! \n");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " " + startTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
                + " to: " + endTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date.toString();
    }

    public String getStartTime() {
        return startTime.toString();
    }

    public String getEndTime() {
        return endTime.toString();
    }
}
