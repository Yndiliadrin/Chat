package hu.alkfejl;

import hu.alkfejl.dao.*;
import hu.alkfejl.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) {
        App.stage = stage;
        App.loadFXML("/fxml/main_window.fxml");

        stage.show();
    }

    public static FXMLLoader loadFXML(String fxml){

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        Scene scene = null;
        try {
            Parent root = fxmlLoader.load();
            scene = new Scene(root);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        stage.setScene(scene);
        stage.setResizable(false);
        return fxmlLoader;

    }

    public static void main(String[] args) {
        launch();
    }

}
