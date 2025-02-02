package artemis.command;

import artemis.task.Deadline;
import artemis.task.Event;
import artemis.task.Task;
import artemis.task.Todo;

public class Parser {
    public Parser() {

    }

    public static String parseCommand(String input) {
        return input.split(" ")[0];
    }

    public static int parseIntegerCommand(String input) throws ArrayIndexOutOfBoundsException {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    public static Task parseTask(Commands command, String input) throws StringIndexOutOfBoundsException,
            ArrayIndexOutOfBoundsException, ArtemisException {
        if (command.equals(Commands.TODO)) {
            return new Todo(input.substring(5));
        } else if (command.equals(Commands.DEADLINE)) {
            String description = input.substring(9, input.indexOf("/by") - 1);
            String by = input.substring(input.indexOf("/by") + 4);
            String date = by.split(" ")[0];
            String time = by.split(" ")[1];

            return new Deadline(description, date, time);
        } else if (command.equals(Commands.EVENT)) {
            String description = input.substring(6, input.indexOf("/from") - 1);
            String by = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
            String date = by.split(" ")[0];
            String startTime = by.split(" ")[1];
            String endTime = input.substring(input.indexOf("/to") + 4);

            return new Event(description, date, startTime, endTime);
        }
        return null;
    }
}
