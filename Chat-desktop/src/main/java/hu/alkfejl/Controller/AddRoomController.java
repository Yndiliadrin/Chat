package hu.alkfejl.Controller;


import hu.alkfejl.App;
import hu.alkfejl.dao.RoomDAO;
import hu.alkfejl.dao.RoomDAOImpl;
import hu.alkfejl.model.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class AddRoomController {

    private Room room;
    private RoomDAO roomDAO = new RoomDAOImpl();

    @FXML
    private TextField name;
    @FXML
    private TextField rules;
    @FXML
    private ComboBox kategori;

    private ObservableList<String> kategories = FXCollections.observableArrayList(
            "other","gameing","diy","movies","books");

    public void setRoom(Room room) {
        this.kategori.setItems(kategories);
        this.kategori.setValue("other");
        this.room = room;

        name.textProperty().bindBidirectional(room.nameProperty());
        rules.textProperty().bindBidirectional(room.rulesProperty());
        kategori.valueProperty().bindBidirectional(room.kategoriProperty());
    }

    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/main_window.fxml");
    }

    @FXML
    public void onSave() {
        roomDAO.save(this.room);
        App.loadFXML("/fxml/main_window.fxml");
    }
}
