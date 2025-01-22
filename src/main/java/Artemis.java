import java.util.Scanner;

public class Artemis {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Artemis\n");

        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);

        String userInput;
        do {
            userInput = scanner.nextLine();
            System.out.println(userInput);
        } while (!userInput.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
