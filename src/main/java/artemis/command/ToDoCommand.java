package artemis.command;

import artemis.storage.Storage;
import artemis.task.TaskList;
import artemis.task.Todo;
import artemis.ui.Ui;

public class ToDoCommand extends Command {
    private Todo todo;

    public ToDoCommand(Todo todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ArtemisException {
        tasks.addTask(todo);
        storage.writeData(tasks.getTaskList());
        ui.taskAdded(todo, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
