package artemis.ui;

import artemis.task.Task;
import artemis.task.TaskList;

import java.util.Scanner;

import static artemis.command.Response.goodByeMessage;
import static artemis.command.Response.welcomeMessage;
import static artemis.command.Response.unknownCommand;

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
        System.out.println(welcomeMessage);
    }

    /**
     * Prints the goodbye comment.
     */
    public String showGoodbye() {
        System.out.println(goodByeMessage);
        return goodByeMessage;
    }

    /**
     * Prints a list of task in the user's task list.
     *
     * @param taskList List of user's task - TaskList.
     */
    public String listTask(TaskList taskList) {
        String commandResponse;

        commandResponse = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            commandResponse = commandResponse + (i + 1) + "." + task.toString() + "\n";
        }

        System.out.println(commandResponse);

        return commandResponse;
    }

    /**
     * Prints matching tasks.
     *
     * @param taskList A filtered tasklist.
     */
    public String listMatchingTask(TaskList taskList) {
        String commandResponse;

        commandResponse = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            commandResponse = commandResponse + (i + 1) + "." + task.toString() + "\n";
        }

        System.out.println(commandResponse);

        return commandResponse;
    }

    /**
     * Prints confirmation of task addition and number of tasks left.
     *
     * @param task User's Task.
     * @param numOfTasks Number of Tasks left.
     */
    public String taskAdded(Task task, int numOfTasks) {
        String commandResponse;
        commandResponse = "Got it. I've added this task:\n" + task.toString() + "\n";
        commandResponse = commandResponse + "Now you have " + numOfTasks + " tasks in the list.";

        System.out.println(commandResponse);

        return commandResponse;
    }

    /**
     * Prints confirmation of task removal and number of tasks left.
     *
     * @param task User's Task.
     * @param numOfTasks Number of Tasks left.
     */
    public String taskRemoved(Task task, int numOfTasks) {
        String commandResponse;
        commandResponse = "Noted. I've removed this task:\n" + task.toString() + "\n";
        commandResponse = commandResponse + "Now you have " + numOfTasks + " tasks in the list.";

        System.out.println(commandResponse);

        return commandResponse;
    }

    public String showUnknownCommand() {
        System.out.println(unknownCommand);
        return unknownCommand;
    }

    public String markTask(Task task) {
        String commandResponse = "Nice! I've marked this task as done:\n" + task.toString();
        System.out.println(commandResponse);
        return commandResponse;
    }

    public String unmarkTask(Task task) {
        String commandResponse = "OK, I've marked this task as not done yet:\n" + task.toString();
        System.out.println(commandResponse);
        return commandResponse;
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
