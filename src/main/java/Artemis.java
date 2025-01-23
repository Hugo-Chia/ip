import java.util.ArrayList;
import java.util.Scanner;

public class Artemis {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Artemis\n");

        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();

        String userInput;
        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ".[" + list.get(i).getStatusIcon() + "] " + list.get(i).getDescription());
                }
            } else if (userInput.substring(0, 4).equals("mark")) {
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                if (index < list.size()) {
                    list.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + "[" + list.get(index).getStatusIcon() + "] " + list.get(index).getDescription());
                }
            } else if (userInput.substring(0, 6).equals("unmark")) {
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                if (index < list.size()) {
                    list.get(index).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + "[" + list.get(index).getStatusIcon() + "] " + list.get(index).getDescription());
                }
            } else {
                list.add(new Task(userInput));
                System.out.println("added: " + userInput);
            }
        }

        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
