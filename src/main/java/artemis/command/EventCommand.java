package artemis.command;

import artemis.storage.Storage;
import artemis.task.Event;
import artemis.task.TaskList;
import artemis.ui.Ui;

public class EventCommand extends Command {
    private Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ArtemisException {
        tasks.addTask(event);
        storage.writeData(tasks.getTaskList());
        commandResponse = ui.taskAdded(event, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
