package Controller;

import hu.alkfejl.model.Room;
import hu.alkfejl.model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBController{

    private static final String INSERT_USER = "INSERT INTO user (username, password, age, gender, interest, role) VALUES (?,?,?,?,?,?)";
    private static final String FIND_USER_BY_NAME = "SELECT * FROM user WHERE username=?";
    private static final String LOGIN_USER_QUERY = "SELECT * FROM user WHERE username=? AND password=?";
    private static final String SELECT_ALL_USER = "SELECT t.* FROM user t LIMIT 501";
    private Properties props = new Properties();
    private static String connectionURL;
    private Connection con;
    private PreparedStatement stmt;



    public DBController() {
        this.con = null;
        this.stmt = null;
        try {
            props.load(getClass().getResourceAsStream("/application.properties"));
            connectionURL = props.getProperty("db.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setConnectionAndStatement(String sql) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection(this.connectionURL);
        stmt = con.prepareStatement(sql);
    }

    private void insertUser(User user){
        try {
            this.setConnectionAndStatement(INSERT_USER);

            stmt.setString(1,user.getUsername());
            stmt.setString(2,user.getPassword());
            stmt.setInt(3,user.getAge());
            stmt.setString(4,user.getGender());
            stmt.setString(5,user.getInterest());
            stmt.setInt(6,0);

            if (stmt.executeUpdate() != 1) {
                System.out.println("valami félre ment");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (con!= null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean findUser(String name) {
        try {
            this.setConnectionAndStatement(FIND_USER_BY_NAME);

            stmt.setString(1, name);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                return false;
            }
            return true;
        } catch (SQLException throwables) {
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

        return false;
    }

    private User selectUser(String uname, String pass) {
        try {
            this.setConnectionAndStatement(LOGIN_USER_QUERY);

            stmt.setString(1, uname);
            stmt.setString(2, pass);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                User user = new User();
                user.setID(res.getInt("ID"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setAge(res.getInt("age"));
                user.setGender(res.getString("gender"));
                user.setInterest(res.getString("interest"));
                user.setRole(res.getInt("role"));

                return user;
            }
            return null;
        } catch (SQLException throwables) {
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

        return null;
    }

    public void regiszt(User user) {
        this.insertUser(user);
    }

    public boolean selectUserByName(String name) {
        return this.findUser(name);
     }

     public User login(String uname, String pass) {
        return this.selectUser(uname, pass);
     }

     //Szobák
    public List<Room> findAllRooms(String sql) {
        return this.findAllRoomsPrivate(sql);
    }

    private List<Room> findAllRoomsPrivate(String sql) {
        List<Room> result = new ArrayList<>();
        try {
            this.setConnectionAndStatement(sql);

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

    public List<User> findAllUsers() {
        return this.findAllUsersPrivate();
    }

    private List<User> findAllUsersPrivate() {
        List<User> result = new ArrayList<>();
        try {
//            this.setConnectionAndStatement(SELECT_ALL_USER);
            Connection c = DriverManager.getConnection(connectionURL);
            Statement stat = c.createStatement();
            ResultSet res = stat.executeQuery(SELECT_ALL_USER);
            if (res.next()) {
                User user = new User();
                user.setID(res.getInt("ID"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setAge(res.getInt("age"));
                user.setGender(res.getString("gender"));
                user.setInterest(res.getString("interest"));
                user.setRole(res.getInt("role"));

                result.add(user);
                System.out.println(user);
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
}
