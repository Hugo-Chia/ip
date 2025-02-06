package artemis.command;

import artemis.storage.Storage;
import artemis.task.TaskList;
import artemis.ui.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        commandResponse = ui.showUnknownCommand();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
