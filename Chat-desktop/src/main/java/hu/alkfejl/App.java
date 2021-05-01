package hu.alkfejl;

import hu.alkfejl.dao.*;
import hu.alkfejl.model.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.SQLException;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        MessengDAO dao = new MessengeDAOImpl();
        dao.findAllMessengBySenderAndReceiver(1,4,0).forEach(System.out::println);

        var scene = new Scene(new StackPane(new Label("asdasd")), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
