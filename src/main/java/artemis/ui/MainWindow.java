package artemis.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import static artemis.command.Response.welcomeMessage;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Artemis artemis;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image artemisImage = new Image(this.getClass().getResourceAsStream("/images/Artemis.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().addAll(
                DialogBox.getArtemisDialog(welcomeMessage, artemisImage)
        );
    }

    /** Injects the Artemis instance */
    public void setArtemis(Artemis artemis) {
        this.artemis = artemis;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Artemis's reply and then appends
     * them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = artemis.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getArtemisDialog(response, artemisImage)
        );
        userInput.clear();
    }
}
