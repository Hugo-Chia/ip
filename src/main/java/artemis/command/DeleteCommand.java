package artemis.command;

import artemis.storage.Storage;
import artemis.task.Task;
import artemis.task.TaskList;
import artemis.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ArtemisException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new ArtemisException("Invalid index. Please try again!!! :(\n");
        } else {
            commandResponse = ui.taskRemoved(tasks.getTask(index), tasks.getSize() - 1);
            tasks.removeTask(index);
            storage.writeData(tasks.getTaskList());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
