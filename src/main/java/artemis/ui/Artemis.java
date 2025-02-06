package artemis.ui;

import artemis.command.ArtemisException;
import artemis.command.Command;
import artemis.command.Parser;
import artemis.storage.Storage;
import artemis.task.TaskList;

public class Artemis {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a new instance of Artemis Chatbot.
     *
     * @param filename Filename of chatbot data to be stored.
     */
    public Artemis(String filename) {
        ui = new Ui();
        storage = new Storage(filename);
        taskList = new TaskList(storage.readData());
    }

    /**
     * Runs an instance of the Artemis Chatbot.
     */
    public void run() {
        ui.showWelcome();

        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.getUserInput();

                Command command = Parser.parse(userInput);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();

            } catch (ArtemisException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String userInput) {
        //return "Artemis heard: " + input;
        try {
            Command command = Parser.parse(userInput);
            command.execute(taskList, ui, storage);
            return command.getCommandResponse();
        } catch (ArtemisException e) {
            return(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Artemis("artemis.txt").run();
    }
}