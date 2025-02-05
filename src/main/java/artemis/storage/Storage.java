package artemis.storage;

import artemis.command.ArtemisException;
import artemis.task.Deadline;
import artemis.task.Event;
import artemis.task.Task;
import artemis.task.Todo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    String filepath;

    /**
     * Creates an instance of the Ui object.
     *
     * @param filename Filename of chatbot data to be stored.
     */
    public Storage(String filename) {
        this.filepath = "data/" + filename;

        //Check if file exists
        boolean dataExists = new File(filepath).isFile();

        //Data file does not exist, create folder and file
        if (!dataExists) {
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

//            if (directoryCreated) {
//                System.out.println("Directory created successfully at: " + directoryPath);
//            } else {
//                System.out.println("Failed to create directory. It may already exist at: " + directoryPath);
//            }

            //File implementation reference from
            //https://www.geeksforgeeks.org/java-program-to-create-a-file-in-a-specified-directory/

            File file = new File(filepath);

            try {
                // File.createNewFile() Method Used
                boolean isFileCreated = file.createNewFile();
//                if (isFileCreated) {
//                    System.out.println("File created successfully.");
//                } else {
//                    System.out.println("File already exists or an error occurred.");
//                }
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * Writes data to disk.
     *
     * @param taskList An arraylist of the user's task.
     */
    public void writeData(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter(this.filepath);

            for (Task task : taskList) {
                int status;
                if (task.getStatusIcon().equals(" ")) {
                    status = 0;
                } else {
                    status = 1;
                }

                if (task instanceof Todo) {
                    fileWriter.write("T;" + status + ";" + task.getDescription() + "\n");
                } else if (task instanceof Deadline) {
                    fileWriter.write("D;" + status + ";" + task.getDescription() + ";"
                            + ((Deadline) task).getDate() + ";" + ((Deadline) task).getTime() + "\n");
                } else if (task instanceof Event) {
                    fileWriter.write("E;" + status + ";" + task.getDescription() + ";"
                            + ((Event) task).getDate() + ";" + ((Event) task).getStartTime() + ";"
                            + ((Event) task).getEndTime() + "\n");
                }
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads data from disk.
     *
     * @return An ArrayList of Task retrieved from disk.
     */
    public ArrayList<Task> readData() {
        ArrayList<Task> taskList = new ArrayList<>();

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(this.filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()) {
                String task = bufferedReader.readLine();

                String[] taskArray = task.split(";");

                if (taskArray[0].equals("T")) {
                    Todo todo = new Todo(taskArray[2]);

                    if (taskArray[1].equals("0")) {
                        todo.markAsNotDone();
                    } else {
                        todo.markAsDone();
                    }

                    taskList.add(todo);
                } else if (taskArray[0].equals("D")) {
                    Deadline deadline = new Deadline(taskArray[2], taskArray[3], taskArray[4]);

                    if (taskArray[1].equals("0")) {
                        deadline.markAsNotDone();
                    } else {
                        deadline.markAsDone();
                    }

                    taskList.add(deadline);
                } else if (taskArray[0].equals("E")) {
                    Event event = new Event(taskArray[2], taskArray[3], taskArray[4], taskArray[5]);

                    if (taskArray[1].equals("0")) {
                        event.markAsNotDone();
                    } else {
                        event.markAsDone();
                    }

                    taskList.add(event);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            System.out.println(e.getMessage());
        } catch (ArtemisException e) {
            throw new RuntimeException(e);
        }

        return taskList;
    }
}
