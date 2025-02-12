package artemis.ui;

import artemis.task.Task;
import artemis.task.TaskList;

import java.util.Scanner;

public class Ui {
    Scanner scanner;

    /**
     * Creates an instance of the Ui object.
     */
    public Ui () {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome comments.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Artemis\n");
        System.out.println("What can I do for you?\n");
    }

    /**
     * Prints the goodbye comment.
     */
    public String showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!\n");
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Prints a list of task in the user's task list.
     *
     * @param taskList List of user's task - TaskList.
     */
    public String listTask(TaskList taskList) {
        String commandResponse;

        System.out.println("Here are the tasks in your list:\n");
        commandResponse = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            System.out.println(i + 1 + "." + task.toString());
            commandResponse = commandResponse + (i + 1) + "." + task.toString() + "\n";
        }

        return commandResponse;
    }

    /**
     * Prints matching tasks.
     *
     * @param taskList A filtered tasklist.
     */
    public String listMatchingTask(TaskList taskList) {
        String commandResponse;

        System.out.println("Here are the matching tasks in your list:\n");
        commandResponse = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            System.out.println(i + 1 + "." + task.toString());
            commandResponse = commandResponse + (i + 1) + "." + task.toString() + "\n";
        }

        return commandResponse;
    }

    /**
     * Prints confirmation of task addition and number of tasks left.
     *
     * @param task User's Task.
     * @param numOfTasks Number of Tasks left.
     */
    public String taskAdded(Task task, int numOfTasks) {
        System.out.println("Got it. I've added this task:\n" + task.toString());
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");

        String commandResponse;
        commandResponse = "Got it. I've added this task:\n" + task.toString() + "\n";
        commandResponse = commandResponse + "Now you have " + numOfTasks + " tasks in the list.";

        return commandResponse;
    }

    /**
     * Prints confirmation of task removal and number of tasks left.
     *
     * @param task User's Task.
     * @param numOfTasks Number of Tasks left.
     */
    public String taskRemoved(Task task, int numOfTasks) {
        System.out.println("Noted. I've removed this task:\n" + task.toString());
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");

        String commandResponse;
        commandResponse = "Noted. I've removed this task:\n" + task.toString() + "\n";
        commandResponse = commandResponse + "Now you have " + numOfTasks + " tasks in the list.";

        return commandResponse;
    }

    public String showUnknownCommand() {
        System.out.println("Sorry, I don't understand what you mean. Please try again!!! :(");
        return "Sorry, I don't understand what you mean. Please try again!!! :(";
    }

    /**
     * Retrieves user input.
     *
     * @return User input.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }
}
