import java.io.*;
import java.util.ArrayList;

public class Storage {
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public void writeData(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter(this.filepath);

            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                //System.out.println(i + 1 + "." + task.toString());
                //fileWriter.write(task.toString()+"\n");

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
                            + ((Event) task).getFrom() + ";" + ((Event) task).getTo() + "\n");
                }
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> readData() {
        ArrayList<Task> taskList = new ArrayList<>();

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(this.filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()) {
                String task = bufferedReader.readLine();

                String taskArray[] = task.split(";");

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
                    Event event = new Event(taskArray[2], taskArray[3], taskArray[4]);

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
