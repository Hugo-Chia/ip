public class Artemis {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Artemis(String pathname) {
        ui = new Ui();
        storage = new Storage(pathname);
        taskList = new TaskList(storage.readData());
    }

    public void run() {
        ui.showWelcome();

        String userInput;
        while (true) {
            try {
                userInput = ui.getUserInput();

                String command = Parser.parseCommand(userInput);

                if (command.equals(Commands.BYE.name().toLowerCase())) {
                    break;
                } else if (command.equals("list")) {
                    ui.listTask(taskList);
                } else if (command.equals(Commands.MARK.name().toLowerCase())) {
                    int index;
                    try {
                        index = Parser.parseMarkUnmarkCommand(userInput);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new ArtemisException("You did not choose a valid task to mark. Please try again!!! :(\n");
                    }

                    if (index < 0 || index >= taskList.getSize()) {
                        throw new ArtemisException("Invalid index. Please try again!!! :(\n");
                    } else {
                        Task task = taskList.getTask(index);
                        task.markAsDone();

                        storage.writeData(taskList.getTaskList());

                        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
                    }
                } else if (command.equals(Commands.UNMARK.name().toLowerCase())) {
                    int index;
                    try {
                        index = Parser.parseMarkUnmarkCommand(userInput);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new ArtemisException("You did not choose a valid task to unmark. Please try again!!! :(\n");
                    }

                    if (index < 0 || index >= taskList.getSize()) {
                        throw new ArtemisException("Invalid index. Please try again!!! :(\n");
                    } else {
                        Task task = taskList.getTask(index);
                        task.markAsNotDone();

                        storage.writeData(taskList.getTaskList());

                        System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());
                    }
                } else if (userInput.startsWith(Commands.TODO.name().toLowerCase())) {
                    if (userInput.length() == 4) {
                        throw new ArtemisException("You did not fill up anything for todo. Please try again!!! :(\n");
                    }

                    String description = userInput.substring(5);

                    Todo todo = new Todo(description);
                    taskList.addTask(todo);
                    storage.writeData(taskList.getTaskList());
                    ui.taskAdded(todo, taskList.getSize());
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
                    taskList.addTask(deadline);
                    storage.writeData(taskList.getTaskList());
                    ui.taskAdded(deadline, taskList.getSize());
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

                        by = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1);
                        date = by.split(" ")[0];
                        startTime = by.split(" ")[1];
                        endTime = userInput.substring(userInput.indexOf("/to") + 4);
                    } catch (IndexOutOfBoundsException e) {
                        throw new ArtemisException("Invalid format for event.. Please try again!!! :(\n");
                    }

                    Event event = new Event(description, date, startTime, endTime);
                    taskList.addTask(event);
                    storage.writeData(taskList.getTaskList());
                    ui.taskAdded(event, taskList.getSize());
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

                    if (index < 0 || index >= taskList.getSize()) {
                        throw new ArtemisException("Invalid index. Please try again!!! :(\n");
                    } else {
                        taskList.removeTask(index);

                        storage.writeData(taskList.getTaskList());

                        System.out.println("Noted. I've removed this task:\n" + taskList.getTask(index).toString());
                        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
                    }
                } else {
                    throw new ArtemisException("Sorry, I don't understand what you mean. Please try again!!! :(\n");
                }
            } catch (ArtemisException e) {
                System.out.println(e.getMessage());
            }
        }

        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Artemis("artemis.txt").run();
    }
}