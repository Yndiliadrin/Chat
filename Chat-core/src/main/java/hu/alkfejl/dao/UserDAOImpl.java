package hu.alkfejl.dao;

import hu.alkfejl.model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDAOImpl implements UserDAO{

    private static final String SELECT_ALL_USER = "Select * FROM user";
    private static final String SELECT_USER_BY_NAME_AND_PASS = "SELECT * FROM user WHERE username=? AND password=?";
    private static final String SELECT_ALL_USER_BY_NAME = "Select * FROM user WHERE username=?";
    private static final String SELECT_ALL_USER_BY_INTEREST = "SELECT * FROM user WHERE interest=?";
    private static final String SELECT_ALL_USER_BY_AGE = "SELECT * FROM user WHERE age=?";
    private static final String SELECT_ALL_USER_BY_GENDER = "SELECT * FROM user WHERE gender=?";
    private static final String SELECT_ALL_USER_BY_GENDER_AND_AGE = "SELECT * FROM user WHERE gender=? AND age=?";
    private static final String SELECT_ALL_USER_BY_INTEREST_AND_AGE = "SELECT * FROM user WHERE interest=? AND age=?";
    private static final String SELECT_ALL_USER_BY_INTEREST_AND_GENDER = "SELECT * FROM user WHERE interest=? AND gender=?";
    private static final String SELECT_ALL_USER_BY_INTEREST_GENDER_AND_AGE = "SELECT * FROM user WHERE interest=? AND gender=? AND age=?";
    private static final String INSERT_USER = "INSERT INTO user (username, password, age, gender, interest, role) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_USER = "UPDATE user username=?, password=?, age=?, gender=?, interest=?,role=? WHERE ID=?";
    private static final String DELETE_USER = "DELETE FROM user WHERE ID=?";

    private Properties props = new Properties();
    private static String connectionURL;
    private Connection con;
    private PreparedStatement stmt;

    public UserDAOImpl() {
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

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try {
            this.setConnectionAndStatement(SELECT_ALL_USER);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                User user = new User();
                user.setID(res.getInt("ID"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setAge(res.getInt("age"));
                user.setGender(res.getString("gender"));
                user.setInterest(res.getString("interest"));
                user.setRole(res.getInt("role"));

                result.add(user);
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }catch (Exception e) {
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
    public User findUser(String username, String pass){
        User result = null;
        try {
            this.setConnectionAndStatement(SELECT_USER_BY_NAME_AND_PASS);
            stmt.setString(1, username);
            stmt.setString(2, pass);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                User user = new User();
                user.setID(res.getInt("ID"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setAge(res.getInt("age"));
                user.setGender(res.getString("gender"));
                user.setInterest(res.getString("interest"));
                user.setRole(res.getInt("role"));
                result = user;
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }catch (Exception e) {
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
    public List<User> findUserByName(String username) {
        List<User> result = new ArrayList<>();
        try {
            this.setConnectionAndStatement(SELECT_ALL_USER_BY_NAME);
            stmt.setString(1, username);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                User user = new User();
                user.setID(res.getInt("ID"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setAge(res.getInt("age"));
                user.setGender(res.getString("gender"));
                user.setInterest(res.getString("interest"));
                user.setRole(res.getInt("role"));

                result.add(user);
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }catch (Exception e) {
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
    public List<User> findAllByInterest(String inter) {
        List<User> result = new ArrayList<>();
        try {
            this.setConnectionAndStatement(SELECT_ALL_USER_BY_INTEREST);
            stmt.setString(1,inter);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                User user = new User();
                user.setID(res.getInt("ID"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setAge(res.getInt("age"));
                user.setGender(res.getString("gender"));
                user.setInterest(res.getString("interest"));
                user.setRole(res.getInt("role"));

                result.add(user);
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }catch (Exception e) {
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
    public List<User> findAllByInterestAndGender(String inter, String gender) {
        List<User> result = new ArrayList<>();
        try {
            this.setConnectionAndStatement(SELECT_ALL_USER_BY_INTEREST_AND_GENDER);
            stmt.setString(1, inter);
            stmt.setString(2, gender);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                User user = new User();
                user.setID(res.getInt("ID"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setAge(res.getInt("age"));
                user.setGender(res.getString("gender"));
                user.setInterest(res.getString("interest"));
                user.setRole(res.getInt("role"));

                result.add(user);
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
    public List<User> findAllByInterestAndAge(String inter, int age) {
        List<User> result = new ArrayList<>();
        try {
            this.setConnectionAndStatement(SELECT_ALL_USER_BY_INTEREST_AND_AGE);
            stmt.setString(1, inter);
            stmt.setInt(2, age);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                User user = new User();
                user.setID(res.getInt("ID"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setAge(res.getInt("age"));
                user.setGender(res.getString("gender"));
                user.setInterest(res.getString("interest"));
                user.setRole(res.getInt("role"));

                result.add(user);
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }catch (Exception e) {
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
    public List<User> findAllByGender(String gender) {
        List<User> result = new ArrayList<>();
        try {
            this.setConnectionAndStatement(SELECT_ALL_USER_BY_GENDER);
            stmt.setString(1,gender);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                User user = new User();
                user.setID(res.getInt("ID"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setAge(res.getInt("age"));
                user.setGender(res.getString("gender"));
                user.setInterest(res.getString("interest"));
                user.setRole(res.getInt("role"));

                result.add(user);
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }catch (Exception e) {
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
    public List<User> findAllByGenderAndAge(String gender, int age) {
        List<User> result = new ArrayList<>();
        try {
            this.setConnectionAndStatement(SELECT_ALL_USER_BY_GENDER_AND_AGE);
            stmt.setString(1, gender);
            stmt.setInt(2, age);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                User user = new User();
                user.setID(res.getInt("ID"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setAge(res.getInt("age"));
                user.setGender(res.getString("gender"));
                user.setInterest(res.getString("interest"));
                user.setRole(res.getInt("role"));

                result.add(user);
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }catch (Exception e) {
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
    public List<User> findAllByAge(int age) {
        List<User> result = new ArrayList<>();
        try {
            this.setConnectionAndStatement(SELECT_ALL_USER_BY_AGE);
            stmt.setInt(1,age);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                User user = new User();
                user.setID(res.getInt("ID"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setAge(res.getInt("age"));
                user.setGender(res.getString("gender"));
                user.setInterest(res.getString("interest"));
                user.setRole(res.getInt("role"));

                result.add(user);
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }catch (Exception e) {
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
    public List<User> findAllByInterestAndGenderAndAge(String interest, String gender, int age) {
        List<User> result = new ArrayList<>();
        try {
            this.setConnectionAndStatement(SELECT_ALL_USER_BY_INTEREST_GENDER_AND_AGE);
            stmt.setString(1, interest);
            stmt.setString(2, gender);
            stmt.setInt(3, age);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                User user = new User();
                user.setID(res.getInt("ID"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setAge(res.getInt("age"));
                user.setGender(res.getString("gender"));
                user.setInterest(res.getString("interest"));
                user.setRole(res.getInt("role"));

                result.add(user);
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }catch (Exception e) {
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
    public User save(User user) {
        try(Connection c = DriverManager.getConnection(props.getProperty("db.url"));
            PreparedStatement stmt = user.getID() <= 0 ? c.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_USER);
        ){

            if (user.getID() > 0) {
                stmt.setInt(7, user.getID());
            }

            stmt.setString(1,user.getUsername());
            stmt.setString(2,user.getPassword());
            stmt.setInt(3,user.getAge());
            stmt.setString(4,user.getGender());
            stmt.setString(5,user.getInterest());
            stmt.setInt(6,0);

            if (stmt.executeUpdate() != 1) {
                return null;
            }

            if(user.getID() <= 0) {
                ResultSet genKey = stmt.getGeneratedKeys();
                if (genKey.next()) {
                    user.setID(genKey.getInt(1));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return  null;
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

        return user;
    }

    @Override
    public void delete(User user) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(DELETE_USER)
        ) {
            stmt.setInt(1, user.getID());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
