package artemis.command;

import artemis.storage.Storage;
import artemis.task.Task;
import artemis.task.TaskList;
import artemis.ui.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ArtemisException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new ArtemisException("Invalid index. Please try again!!! :(\n");
        } else {
            Task task = tasks.getTask(index);
            task.markAsNotDone();

            storage.writeData(tasks.getTaskList());

            System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
