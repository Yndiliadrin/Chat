package hu.alkfejl.dao;

import hu.alkfejl.model.Room;

import java.util.List;

public interface RoomDAO {
    List<Room> findAll();
    List<Room> findAllByKategori(String category) ;
    List<Room> findRoomByName(String name);
    List<Room> findRommByNameAndKategori(String name, String category);

    Room save(Room room);

    void delete(Room room);
}
