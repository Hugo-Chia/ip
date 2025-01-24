import java.util.ArrayList;
import java.util.Scanner;

public class Artemis {
    public static void main(String[] args) throws ArtemisException {
        System.out.println("Hello! I'm Artemis\n");

        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();

        String userInput;
        while (true) {
            try {
                userInput = scanner.nextLine();

                if (userInput.equals("bye")) {
                    break;
                } else if (userInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:\n");
                    for (int i = 0; i < list.size(); i++) {
                        Task task = list.get(i);
                        System.out.println(i + 1 + "." + task.toString());
                    }
                } else if (userInput.startsWith("mark")) {
                    int index = Integer.parseInt(userInput.substring(5)) - 1;
                    if (index < list.size()) {
                        Task task = list.get(index);
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
                    }
                } else if (userInput.startsWith("unmark")) {
                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    if (index < list.size()) {
                        Task task = list.get(index);
                        task.markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());
                    }
                } else if (userInput.startsWith("todo")) {
                    if (userInput.length() == 4) {
                        throw new ArtemisException("You did not fill up anything for todo. Please try again!!! :(\n");
                    }

                    String description = userInput.substring(5);

                    Todo todo = new Todo(description);
                    list.add(todo);

                    System.out.println("Got it. I've added this task:\n" + todo.toString());
                    System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
                } else if (userInput.startsWith("deadline")) {
                    String description = userInput.substring(9, userInput.indexOf("/by") - 1);
                    String by = userInput.substring(userInput.indexOf("/by") + 4);

                    Deadline deadline = new Deadline(description, by);
                    list.add(deadline);

                    System.out.println("Got it. I've added this task:\n" + deadline.toString());
                    System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
                } else if (userInput.startsWith("event")) {
                    String description = userInput.substring(6, userInput.indexOf("/from") - 1);
                    String from = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1);
                    String to = userInput.substring(userInput.indexOf("/to") + 4);

                    Event event = new Event(description, from, to);
                    list.add(event);

                    System.out.println("Got it. I've added this task:\n" + event.toString());
                    System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
                } else {
                    throw new ArtemisException("Sorry, I don't understand what you mean. Please try again!!! :(\n");
                }
            } catch (ArtemisException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
