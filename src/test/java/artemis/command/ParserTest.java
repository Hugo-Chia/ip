package artemis.command;

import artemis.task.Deadline;
import artemis.task.Event;
import artemis.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest() {
        assertEquals(4, 4);
    }

    @Test
    public void testParseCommand() {
        assertEquals("todo", Parser.parseCommand("todo read book"));

        assertEquals("mark", Parser.parseCommand("mark 10"));
    }

    @Test
    public void testParseIntegerCommand() {
        // Normal parsing for commands that rely on integers (mark, unmark, delete)
        assertEquals(9, Parser.parseIntegerCommand("mark 10"));

        assertEquals(-1, Parser.parseIntegerCommand("mark 0"));

        assertEquals(-11, Parser.parseIntegerCommand("mark -10"));

        // No 2nd element in array when doing split
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            Parser.parseIntegerCommand("mark");
        });
    }

    @Test
    public void testParseTask() {
        // Parsing to ensure ToDo task is correct
        try {
            assertEquals(new Todo("read book").toString(),
                    Parser.parseTask(Commands.TODO, "todo read book").toString());
        } catch (ArtemisException e) {
            throw new RuntimeException(e);
        }

        // Parsing to ensure Deadline task is correct
        try {
            assertEquals(new Deadline("return book", "2025-01-01", "17:00").toString(),
                    Parser.parseTask(Commands.DEADLINE, "deadline return book /by 2025-01-01 17:00").toString());
        } catch (ArtemisException e) {
            throw new RuntimeException(e);
        }

        // Parsing to ensure Event task is correct
        try {
            assertEquals(
                    new Event("project meeting", "2025-02-02", "16:00", "18:00").toString(),
                    Parser.parseTask(Commands.EVENT, "event project meeting /from 2025-02-02 16:00 /to 18:00").toString());
        } catch (ArtemisException e) {
            throw new RuntimeException(e);
        }
    }
}
