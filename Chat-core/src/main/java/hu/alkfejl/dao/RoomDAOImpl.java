package hu.alkfejl.dao;

import hu.alkfejl.model.Room;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RoomDAOImpl implements RoomDAO{

    private Properties props = new Properties();
    private static String connectionURL;
    private Connection con;
    private PreparedStatement stmt;

    private static final String SELECT_ROOMS_BY_NAME_AND_KATEGORI = "SELECT * FROM room WHERE kategori=? AND name=?";
    private static final String SELECT_ROOMS_BY_KATEGORI = "SELECT * FROM room WHERE kategori=?";
    private static final String SELECT_ROOMS_BY_NAME = "SELECT * FROM room WHERE name=?";
    private static final String SELECT_ALL_ROOM = "SELECT * FROM room";

    public RoomDAOImpl() {
        try {
            props.load(getClass().getResourceAsStream("/application.properties"));
            connectionURL = props.getProperty("db.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RoomDAOImpl(String sql) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(this.connectionURL);
            stmt = con.prepareStatement(sql);
        } catch (Exception e) {
            System.err.println("Hell yeah");
        }
    }

    private void setConnectionAndStatement(String sql) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection(this.connectionURL);
        stmt = con.prepareStatement(sql);
    }

    //FIND BEGIN

    public List<Room> findAllRoomsPrivate() {
        List<Room> result = new ArrayList<>();
        try {
            this.setConnectionAndStatement(SELECT_ALL_ROOM);

            ResultSet res = stmt.executeQuery();
            while (res.next()){
                Room room = new Room();
                room.setID(res.getInt("ID"));
                room.setName(res.getString("name"));
                room.setRules(res.getString("rules"));
                room.setKategori(res.getString("kategori"));

                result.add(room);
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con!= null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public List<Room> findAllBySearch() {
        List<Room> result = new ArrayList<>();
        try {

            ResultSet res = stmt.executeQuery();
            while (res.next()){
                Room room = new Room();
                room.setID(res.getInt("ID"));
                room.setName(res.getString("name"));
                room.setRules(res.getString("rules"));
                room.setKategori(res.getString("kategori"));

                result.add(room);
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con!= null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public List<Room> findAll() {
        return this.findAllRoomsPrivate();
    }


    @Override
    public List<Room> findAllByKategori(String category) {
        List<Room> res = new ArrayList<>();
        try {
            this.setConnectionAndStatement(SELECT_ROOMS_BY_KATEGORI);
            this.stmt.setString(1, category);
            res = this.findAllBySearch();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con!= null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    @Override
    public List<Room> findRoomByName(String name) {
        List<Room> res = new ArrayList<>();
        try {
            this.setConnectionAndStatement(SELECT_ROOMS_BY_NAME);
            this.stmt.setString(1, name);
            res = this.findAllBySearch();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con!= null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    @Override
    public List<Room> findRommByNameAndKategori(String name, String category) {
        List<Room> res = new ArrayList<>();
        try {
            this.setConnectionAndStatement(SELECT_ROOMS_BY_NAME_AND_KATEGORI);
            this.stmt.setString(1, name);
            this.stmt.setString(2, category);
            res = this.findAllBySearch();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con!= null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    //FIND END

    @Override
    public Room save(Room room) {
        return null;
    }

    @Override
    public void delete(Room room) {

    }
}
