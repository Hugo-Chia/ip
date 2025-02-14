package artemis.command;

import artemis.task.Deadline;
import artemis.task.Event;
import artemis.task.Task;
import artemis.task.Todo;

public class Parser {
    /**
     * Creates an instance of the Parser object.
     */
    public Parser() {

    }

    /**
     * Retrieves the user's command.
     *
     * @param input User full input.
     * @return User's command
     */
    public static String parseCommand(String input) {
        return input.split(" ")[0];
    }

    public static Command parse(String input) throws ArtemisException {
        String command = Parser.parseCommand(input);

        if (command.equals(Commands.BYE.name().toLowerCase())) {
            return new ExitCommand();
        } else if (command.equals(Commands.LIST.name().toLowerCase())) {
            return new ListCommand();
        } else if (command.equals(Commands.MARK.name().toLowerCase())) {
            int index;
            try {
                index = Parser.parseIntegerCommand(input);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArtemisException("You did not choose a valid task to mark. Please try again!!! :(\n");
            }

            return new MarkCommand(index);
        } else if (command.equals(Commands.UNMARK.name().toLowerCase())) {
            int index;
            try {
                index = Parser.parseIntegerCommand(input);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArtemisException("You did not choose a valid task to unmark. Please try again!!! :(\n");
            }

            return new UnmarkCommand(index);
        }  else if (command.equals(Commands.DELETE.name().toLowerCase())) {
            int index;
            try {
                index = Parser.parseIntegerCommand(input);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new ArtemisException("You did not choose a valid task to delete. Please try again!!! :(\n");
            }

            return new DeleteCommand(index);
        } else if (command.equals(Commands.FIND.name().toLowerCase())) {
            String keyword;
            try {
                keyword = Parser.parseKeywordCommand(input);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArtemisException("Invalid format for find. Please try again!!! :(\n");
            }

            return new FindCommand(keyword);
        } else if (command.equals(Commands.TODO.name().toLowerCase())) {
            Todo todo;

            try {
                todo = (Todo) Parser.parseTask(Commands.TODO, input);
            }  catch (StringIndexOutOfBoundsException e) {
                throw new ArtemisException("You did not fill up anything for todo. Please try again!!! :(\n");
            }

            return new ToDoCommand(todo);
        } else if (command.equals(Commands.DEADLINE.name().toLowerCase())) {
            Deadline deadline;

            try {
                deadline = (Deadline) Parser.parseTask(Commands.DEADLINE, input);
            }  catch (StringIndexOutOfBoundsException e) {
                throw new ArtemisException("You did not fill up anything for deadline. Please try again!!! :(\n");
            }

            return new DeadlineCommand(deadline);
        } else if (command.equals(Commands.EVENT.name().toLowerCase())) {
            Event event;

            try {
                event = (Event) Parser.parseTask(Commands.EVENT, input);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | ArtemisException e) {
                throw new ArtemisException("You did not fill up anything for event. Please try again!!! :(\n");
            }

            return new EventCommand(event);
        }
        return new UnknownCommand();
    }

    /**
     * Parses the user input integer.
     * Offset by -1 to correct it to zero-indexing.
     * Used by commands: mark, unmark, delete.
     *
     * @param input User full input.
     * @return Zero-indexed integer.
     * @throws ArrayIndexOutOfBoundsException If 2nd element or input is missing.
     */
    public static int parseIntegerCommand(String input) throws ArrayIndexOutOfBoundsException {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Parses keyword, the second element in an array.
     *
     * @param input The user's input.
     * @return The keyword the user entered.
     * @throws ArrayIndexOutOfBoundsException If the second element does not exist.
     */
    public static String parseKeywordCommand(String input) throws ArrayIndexOutOfBoundsException {
        return input.split(" ")[1];
    }

    /**
     * Returns a Task(Todo, Deadline, Event) after parsing user input.
     *
     * @param command The user's command.
     * @param input The user's input.
     * @return Parsed Task(Todo, Deadline, Event) object.
     * @throws StringIndexOutOfBoundsException If user input is shorter than expected length.
     * @throws ArrayIndexOutOfBoundsException If 2nd element or input is missing.
     * @throws ArtemisException If Task creation fails due to wrong input.
     */
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
