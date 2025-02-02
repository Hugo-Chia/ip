package artemis.ui;

import artemis.task.Task;
import artemis.task.TaskList;

import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui () {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Artemis\n");
        System.out.println("What can I do for you?\n");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public void listTask(TaskList taskList) {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            System.out.println(i + 1 + "." + task.toString());
        }
    }

    /**
     * Prints matching tasks.
     *
     * @param taskList A filtered tasklist.
     */
    public void listMatchingTask(TaskList taskList) {
        System.out.println("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            System.out.println(i + 1 + "." + task.toString());
        }
    }

    public void taskAdded(Task task, int numOfTasks) {
        System.out.println("Got it. I've added this task:\n" + task.toString());
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    public void taskRemoved(Task task, int numOfTasks) {
        System.out.println("Noted. I've removed this task:\n" + task.toString());
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    public String getUserInput() {
        return scanner.nextLine();
    }
}
