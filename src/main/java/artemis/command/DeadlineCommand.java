package artemis.command;

import artemis.storage.Storage;
import artemis.task.Deadline;
import artemis.task.TaskList;
import artemis.ui.Ui;

public class DeadlineCommand extends Command {
    private Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ArtemisException {
        tasks.addTask(deadline);
        storage.writeData(tasks.getTaskList());
        commandResponse = ui.taskAdded(deadline, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
