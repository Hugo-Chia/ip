package artemis.command;

import artemis.storage.Storage;
import artemis.task.TaskList;
import artemis.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        commandResponse = ui.listTask(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
