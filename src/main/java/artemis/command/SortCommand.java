package artemis.command;

import artemis.storage.Storage;
import artemis.task.TaskList;
import artemis.ui.Ui;

public class SortCommand extends Command {
    private String keyword;

    public SortCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ArtemisException {
        commandResponse = ui.listSortedTask(tasks.getSortedTask(keyword));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
