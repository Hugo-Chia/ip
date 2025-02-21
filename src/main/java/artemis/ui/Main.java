package artemis.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Artemis using FXML.
 */
public class Main extends Application {

    private Artemis artemis = new Artemis("artemis.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("ArtemisPro");
            stage.setMinWidth(400);
            stage.setMinHeight(600);
            fxmlLoader.<MainWindow>getController().setArtemis(artemis);  // inject the Artemis instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
