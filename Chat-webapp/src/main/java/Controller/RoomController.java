package Controller;

import hu.alkfejl.model.Room;

import java.util.List;

public interface RoomController {
    List<Room> getAll(String sql);
}
