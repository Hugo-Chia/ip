package artemis.task;

public class Todo extends Task {
    /**
     * Creates an instance of the Todo object.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
