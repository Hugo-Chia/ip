package artemis.task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList = new ArrayList<Task>();

    /**
     * Creates a new instance of TaskList.
     */
    public TaskList() {}

    /**
     * Creates an instance of the TaskList object.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds task to list.
     *
     * @param task Task entered by user.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes task from list.
     *
     * @param index Removes task based on index entered by user.
     */
    public void removeTask(int index) {
        taskList.remove(index);
    }

    /**
     * Returns size of taskList.
     *
     * @return Size of taskList.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Retrieves task from taskList.
     *
     * @param index Retrieves task based on index entered by user.
     * @return A Task object based on user-specified index
     */
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

    /**
     * Retrieves the whole task list.
     *
     * @return A list of task - taskList.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
