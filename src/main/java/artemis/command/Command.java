package artemis.command;

import artemis.storage.Storage;
import artemis.task.TaskList;
import artemis.ui.Ui;

public abstract class Command {
    protected String commandResponse;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ArtemisException;

    public abstract boolean isExit();

    public String getCommandResponse() {
        return commandResponse;
    }
}
