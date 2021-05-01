package Controller;

import hu.alkfejl.model.Room;

import java.util.List;

public class RoomControllerImpl implements RoomController{
    private DBController dbController;

    public RoomControllerImpl() {
        this.dbController = new DBController();
    }

    @Override
    public List<Room> getAll(String sql) {
        return dbController.findAllRooms(sql);
    }
}
