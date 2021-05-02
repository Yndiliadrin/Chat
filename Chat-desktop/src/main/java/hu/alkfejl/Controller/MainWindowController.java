package hu.alkfejl.Controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.RoomDAO;
import hu.alkfejl.dao.RoomDAOImpl;
import hu.alkfejl.dao.UserDAO;
import hu.alkfejl.dao.UserDAOImpl;
import hu.alkfejl.model.Room;
import hu.alkfejl.model.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    UserDAO userDAO = new UserDAOImpl();
    RoomDAO roomDAO = new RoomDAOImpl();

    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableView<Room> roomTableView;

    @FXML
    private TableColumn<User, String> usernameCollum;

    @FXML
    private TableColumn<Room, String> nameCollum;
    @FXML
    private TableColumn<Room, String> rulesCollum;
    @FXML
    private TableColumn<Room, String> kategoriCollum;

    @FXML
    private TableColumn<User, Void> userActionCollum;
    @FXML
    private TableColumn<Room, Void> roomActionCollum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();

        nameCollum.setCellValueFactory(new PropertyValueFactory<>("name"));
        rulesCollum.setCellValueFactory(new PropertyValueFactory<>("rules"));
        kategoriCollum.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        usernameCollum.setCellValueFactory(new PropertyValueFactory<>("username"));

        roomActionCollum.setCellFactory(param -> new TableCell<>(){
            private final Button deleteBtn = new Button("Delete");
            {
                deleteBtn.setOnAction(event -> {
                    Room room = getTableRow().getItem();
                    deleteRoom(room);
                    refreshTable();
                });
            }

            @Override
            protected void updateItem(Void s, boolean b) {
                super.updateItem(s, b);
                if (b) {
                    setGraphic(null);
                } else {
                    HBox container = new HBox();
                    container.getChildren().addAll(deleteBtn);
                    setGraphic(container);
                }
            }
        });

        userActionCollum.setCellFactory(param -> new TableCell<>(){
            private final Button deleteBtn = new Button("Delete");
            {
                deleteBtn.setOnAction(event -> {
                    User user = getTableRow().getItem();
                    deleteUser(user);
                    refreshTable();
                });
            }

            @Override
            protected void updateItem(Void s, boolean b) {
                super.updateItem(s, b);
                if (b) {
                    setGraphic(null);
                } else {
                    HBox container = new HBox();
                    container.getChildren().addAll(deleteBtn);
                    setGraphic(container);
                }
            }
        });
    }

    private void deleteUser(User user) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Biztos törölni szertné a felhasználót: " + user.getUsername(), ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if (buttonType.equals(ButtonType.YES)) {
                userDAO.delete(user);
            }
        });
    }

    private void deleteRoom(Room room) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Biztos törölni szertné a csoportot: " + room.getName(), ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if (buttonType.equals(ButtonType.YES)) {
                roomDAO.delete(room);
            }
        });
    }

    private void refreshTable() {
        userTableView.getItems().setAll(userDAO.findAll());
        userTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        roomTableView.getItems().setAll(roomDAO.findAll());
        roomTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    public void onExit() {
        Platform.exit();
    }

    @FXML
    public void addRoom() {
        FXMLLoader fxmlLoader = App.loadFXML(("/fxml/add_room.fxml"));
        AddRoomController controller = fxmlLoader.getController();
        controller.setRoom(new Room());
    }
}
