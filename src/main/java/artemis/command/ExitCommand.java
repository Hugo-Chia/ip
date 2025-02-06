package artemis.command;

import artemis.storage.Storage;
import artemis.task.TaskList;
import artemis.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
