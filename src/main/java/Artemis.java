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

        while (true) {
            try {
                String userInput = ui.getUserInput();
                String command = Parser.parseCommand(userInput);

                if (command.equals(Commands.BYE.name().toLowerCase())) {
                    break;
                } else if (command.equals("list")) {
                    ui.listTask(taskList);
                } else if (command.equals(Commands.MARK.name().toLowerCase())) {
                    int index;
                    try {
                        index = Parser.parseIntegerCommand(userInput);
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
                        index = Parser.parseIntegerCommand(userInput);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new ArtemisException("You did not choose a valid task to unmark." +
                                "Please try again!!! :(\n");
                    }

                    if (index < 0 || index >= taskList.getSize()) {
                        throw new ArtemisException("Invalid index. Please try again!!! :(\n");
                    } else {
                        Task task = taskList.getTask(index);
                        task.markAsNotDone();

                        storage.writeData(taskList.getTaskList());

                        System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());
                    }
                } else if (command.equals(Commands.TODO.name().toLowerCase())) {
                    Todo todo;

                    try {
                        todo = (Todo) Parser.parseTask(Commands.TODO, userInput);
                    }  catch (StringIndexOutOfBoundsException e) {
                        throw new ArtemisException("You did not fill up anything for todo. Please try again!!! :(\n");
                    }

                    taskList.addTask(todo);
                    storage.writeData(taskList.getTaskList());
                    ui.taskAdded(todo, taskList.getSize());
                } else if (command.equals(Commands.DEADLINE.name().toLowerCase())) {
                    Deadline deadline;

                    try {
                        deadline = (Deadline) Parser.parseTask(Commands.DEADLINE, userInput);
                    } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | ArtemisException e) {
                        throw new ArtemisException("Invalid format for deadline. Please try again!!! :(\n");
                    }

                    taskList.addTask(deadline);
                    storage.writeData(taskList.getTaskList());
                    ui.taskAdded(deadline, taskList.getSize());
                } else if (userInput.startsWith(Commands.EVENT.name().toLowerCase())) {
                    Event event;

                    try {
                        event = (Event) Parser.parseTask(Commands.EVENT, userInput);
                    } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | ArtemisException e) {
                        throw new ArtemisException("Invalid format for event. Please try again!!! :(\n");
                    }

                    taskList.addTask(event);
                    storage.writeData(taskList.getTaskList());
                    ui.taskAdded(event, taskList.getSize());
                } else if (userInput.startsWith(Commands.DELETE.name().toLowerCase())) {
                    int index;
                    try {
                        index = Parser.parseIntegerCommand(userInput);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new ArtemisException("You did not choose a valid task to mark. Please try again!!! :(\n");
                    }

                    if (index < 0 || index >= taskList.getSize()) {
                        throw new ArtemisException("Invalid index. Please try again!!! :(\n");
                    } else {
                        ui.taskRemoved(taskList.getTask(index), taskList.getSize() - 1);
                        taskList.removeTask(index);
                        storage.writeData(taskList.getTaskList());
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