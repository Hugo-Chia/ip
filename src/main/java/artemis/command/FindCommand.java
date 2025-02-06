package artemis.command;

import artemis.storage.Storage;
import artemis.task.TaskList;
import artemis.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ArtemisException {
        commandResponse = ui.listMatchingTask(tasks.getMatchingTask(keyword));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
