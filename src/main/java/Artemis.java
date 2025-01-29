import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Artemis {
    public static void main(String[] args) throws ArtemisException {
        System.out.println("Hello! I'm Artemis\n");

        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();

        String pathname = "artemis.txt";
        Storage storage = new Storage(pathname);

        //Check if file exists
        boolean dataExists = new File(pathname).isFile();

        if (!dataExists) {
            //Data file does not exist, create folder and file

            //Directory implementation reference from https://www.geeksforgeeks.org/how-to-create-a-directory-in-java/

            // Specify the Directory Name
            String directoryName = "data";

            // Address of Current Directory
            String currentDirectory = System.getProperty("user.dir");

            // Specify the path of the directory to be created
            String directoryPath = currentDirectory + File.separator + directoryName;

            // Create a File object representing the directory
            File directory = new File(directoryPath);

            // Attempt to create the directory
            boolean directoryCreated = directory.mkdir();

            if (directoryCreated) {
                //System.out.println("Directory created successfully at: " + directoryPath);
            } else {
                //System.out.println("Failed to create directory. It may already exist at: " + directoryPath);
            }

            //File implementation reference from https://www.geeksforgeeks.org/java-program-to-create-a-file-in-a-specified-directory/

            //String fileName = "artemis.txt";
            File file = new File(pathname);

            try {
                // File.createNewFile() Method Used
                boolean isFileCreated = file.createNewFile();
                if (isFileCreated) {
                    //System.out.println("File created successfully.");
                } else {
                    //System.out.println("File already exists or an error occurred.");
                }
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println(e.getMessage());
            }

        } else {
            //Else load list of task from file into taskList

            taskList = storage.readData();
        }


        String userInput;
        while (true) {
            try {
                userInput = scanner.nextLine();

                if (userInput.equals(Commands.BYE.name().toLowerCase())) {
                    break;
                } else if (userInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:\n");
                    for (int i = 0; i < taskList.size(); i++) {
                        Task task = taskList.get(i);
                        System.out.println(i + 1 + "." + task.toString());
                    }
                } else if (userInput.startsWith(Commands.MARK.name().toLowerCase())) {
                    if (userInput.length() == 4) {
                        throw new ArtemisException("You did not choose a task to mark. Please try again!!! :(\n");
                    }

                    int index;
                    try {
                         index = Integer.parseInt(userInput.substring(5)) - 1;
                    } catch (NumberFormatException e) {
                        throw new ArtemisException("Invalid index. Please try again!!! :(\n");
                    }

                    if (index < 0 || index >= taskList.size()) {
                        throw new ArtemisException("Invalid index. Please try again!!! :(\n");
                    } else {
                        Task task = taskList.get(index);
                        task.markAsDone();

                        storage.writeData(taskList);

                        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
                    }
                } else if (userInput.startsWith(Commands.UNMARK.name().toLowerCase())) {
                    if (userInput.length() == 6) {
                        throw new ArtemisException("You did not choose a task to unmark. Please try again!!! :(\n");
                    }

                    int index;
                    try {
                        index = Integer.parseInt(userInput.substring(7)) - 1;
                    } catch (NumberFormatException e) {
                        throw new ArtemisException("Invalid index. Please try again!!! :(\n");
                    }

                    if (index < 0 || index >= taskList.size()) {
                        throw new ArtemisException("Invalid index. Please try again!!! :(\n");
                    } else {
                        Task task = taskList.get(index);
                        task.markAsNotDone();

                        storage.writeData(taskList);

                        System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());
                    }
                } else if (userInput.startsWith(Commands.TODO.name().toLowerCase())) {
                    if (userInput.length() == 4) {
                        throw new ArtemisException("You did not fill up anything for todo. Please try again!!! :(\n");
                    }

                    String description = userInput.substring(5);

                    Todo todo = new Todo(description);
                    taskList.add(todo);

                    storage.writeData(taskList);

                    System.out.println("Got it. I've added this task:\n" + todo.toString());
                    System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
                } else if (userInput.startsWith(Commands.DEADLINE.name().toLowerCase())) {
                    if (userInput.length() == 8) {
                        throw new ArtemisException("You did not fill up anything for deadline. Please try again!!! :(\n");
                    }

                    String description;
                    String by;
                    String date;
                    String time;

                    try {
                        description = userInput.substring(9, userInput.indexOf("/by") - 1);
                        by = userInput.substring(userInput.indexOf("/by") + 4);
                        date = by.split(" ")[0];
                        time = by.split(" ")[1];
                    } catch (IndexOutOfBoundsException e) {
                        throw new ArtemisException("Invalid format for deadline.. Please try again!!! :(\n");
                    }

                    Deadline deadline = new Deadline(description, date, time);
                    taskList.add(deadline);

                    storage.writeData(taskList);

                    System.out.println("Got it. I've added this task:\n" + deadline.toString());
                    System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
                } else if (userInput.startsWith(Commands.EVENT.name().toLowerCase())) {
                    if (userInput.length() == 5) {
                        throw new ArtemisException("You did not fill up anything for event. Please try again!!! :(\n");
                    }

                    String description;
                    String by;
                    String date;
                    String startTime;
                    String endTime;

                    try {
                        description = userInput.substring(6, userInput.indexOf("/from") - 1);
                        //String from = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1);
                        //String to = userInput.substring(userInput.indexOf("/to") + 4);

                        by = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1);
                        date = by.split(" ")[0];
                        startTime = by.split(" ")[1];
                        endTime = userInput.substring(userInput.indexOf("/to") + 4);
                    } catch (IndexOutOfBoundsException e) {
                        throw new ArtemisException("Invalid format for event.. Please try again!!! :(\n");
                    }

                    Event event = new Event(description, date, startTime, endTime);
                    taskList.add(event);

                    storage.writeData(taskList);

                    System.out.println("Got it. I've added this task:\n" + event.toString());
                    System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
                } else if (userInput.startsWith(Commands.DELETE.name().toLowerCase())) {
                    if (userInput.length() == 6) {
                        throw new ArtemisException("You did not choose a task to delete. Please try again!!! :(\n");
                    }

                    int index;
                    try {
                        index = Integer.parseInt(userInput.substring(7)) - 1;
                    } catch (NumberFormatException e) {
                        throw new ArtemisException("Invalid index. Please try again!!! :(\n");
                    }

                    if (index < 0 || index >= taskList.size()) {
                        throw new ArtemisException("Invalid index. Please try again!!! :(\n");
                    } else {
                        taskList.remove(index);
                        Task.reduceTaskCount();

                        storage.writeData(taskList);

                        System.out.println("Noted. I've removed this task:\n" + taskList.get(index).toString());
                        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
                    }
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
