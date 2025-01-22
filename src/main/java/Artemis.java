import java.util.ArrayList;
import java.util.Scanner;

public class Artemis {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Artemis\n");

        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();

        String userInput;
        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            } else {
                list.add(userInput);
                System.out.println("added: " + userInput);
            }
        }

        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
