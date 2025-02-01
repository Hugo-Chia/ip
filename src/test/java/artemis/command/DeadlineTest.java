package artemis.command;

import artemis.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    public void testDeadline() throws ArtemisException {
        // Normal usage of Deadline
        assertEquals("[D][ ] return book (by: 01 Jan 2025 05:00 pm)",
                new Deadline("return book", "2025-01-01", "17:00").toString());

        // Incorrect usage of Deadline - Wrong time format
        Exception exceptionWrongTime = assertThrows(ArtemisException.class, () -> {
            new Deadline("return book", "2025-01-01", "5pm");
        });

        assertEquals("Error with Date-Time Format, please try again! \n", exceptionWrongTime.getMessage());

        // Incorrect usage of Deadline - Wrong date format
        Exception exceptionWrongDate = assertThrows(ArtemisException.class, () -> {
            new Deadline("return book", "01-01-2025", "17:00");
        });

        assertEquals("Error with Date-Time Format, please try again! \n", exceptionWrongDate.getMessage());
    }
}
