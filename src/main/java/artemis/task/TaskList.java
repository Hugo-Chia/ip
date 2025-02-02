package artemis.task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList = new ArrayList<Task>();

    /**
     * Creates a new instance of TaskList.
     */
    public TaskList() {}

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Retrieves list of matching task.
     *
     * @param keyword Keyword to search for in the task description
     * @return TaskList containing matching Tasks
     */
    public TaskList getMatchingTask(String keyword) {
        TaskList matchingTaskList = new TaskList();

        for (Task task : taskList) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTaskList.addTask(task);
            }
        }

        return matchingTaskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
