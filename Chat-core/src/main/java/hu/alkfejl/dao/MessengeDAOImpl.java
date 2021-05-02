package hu.alkfejl.dao;

import hu.alkfejl.model.Messeng;
import hu.alkfejl.model.WebappMSG;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MessengeDAOImpl implements MessengDAO{

    private static final String SELECT_MSGS = "SELECT m.*, u.username FROM messeng m INNER JOIN user u ON m.sender = u.ID WHERE to_what =? AND receiver =?";
    private static final String INSERT_MESSENG = "INSERT INTO messeng(sender, receiver, type, textMessage, img, to_what) VALUES(?,?,?,?,?,?)";
    private static final String SELECT_USER_MSGS = "SELECT m.*, u.username FROM messeng m INNER JOIN user u ON m.sender = u.ID WHERE m.to_what =? AND (sender=? AND receiver=?) OR (sender=? AND receiver=?) ORDER BY m.ID";

    private Properties props = new Properties();
    private static String connectionURL;
    private Connection con;
    private PreparedStatement stmt;

    public MessengeDAOImpl() {
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
    public List<WebappMSG> findAllMessengBySenderAndReceiver(int sender, int receiver, int toWhat) {
        System.out.println(sender);
        System.out.println(receiver);
        System.out.println(toWhat);
        List<WebappMSG> result = new ArrayList<>();
        try {
            this.setConnectionAndStatement(SELECT_USER_MSGS);
            stmt.setString(1, String.valueOf(toWhat));
            stmt.setString(2, String.valueOf(receiver));
            stmt.setString(3, String.valueOf(sender));
            stmt.setString(4, String.valueOf(sender));
            stmt.setString(5, String.valueOf(receiver));
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                System.out.println("nyeh");
                WebappMSG msg = new WebappMSG();
                Messeng tmpMsg = new Messeng();
                tmpMsg.setID(res.getInt("ID"));
                tmpMsg.setSender(res.getInt("sender"));
                tmpMsg.setReceiver(res.getInt("receiver"));
                tmpMsg.setType(res.getString("type"));
                tmpMsg.setMesseng(res.getString("textMessage"));
                tmpMsg.setImg(res.getBytes("img"));
                tmpMsg.setTo_what(res.getInt("to_what"));
                msg.setMesseng(tmpMsg);
                msg.setSender(res.getString("username"));

                result.add(msg);
            }
            System.out.println(result.size());
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
    public List<WebappMSG> findAllMSGByReceiverAndToWhat(String receiver, int toWhat) {
        List<WebappMSG> result = new ArrayList<>();
        try {
            this.setConnectionAndStatement(SELECT_MSGS);
            stmt.setString(1, String.valueOf(toWhat));
            stmt.setString(2, receiver);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                WebappMSG msg = new WebappMSG();
                Messeng tmpMsg = new Messeng();
                tmpMsg.setID(res.getInt("ID"));
                tmpMsg.setSender(res.getInt("sender"));
                tmpMsg.setReceiver(res.getInt("receiver"));
                tmpMsg.setType(res.getString("type"));
                tmpMsg.setMesseng(res.getString("textMessage"));
                tmpMsg.setImg(res.getBytes("img"));
                tmpMsg.setTo_what(res.getInt("to_what"));
                msg.setMesseng(tmpMsg);
                msg.setSender(res.getString("username"));

                result.add(msg);
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
    public void insertMSG(Messeng messeng) {
        try {
            this.setConnectionAndStatement(INSERT_MESSENG);

            this.stmt.setInt(1, messeng.getSender());
            this.stmt.setInt(2, messeng.getReceiver());
            this.stmt.setString(3, messeng.getType());
            this.stmt.setString(4, messeng.getMesseng());
            this.stmt.setBytes(5, messeng.getImg());
            this.stmt.setInt(6, messeng.getTo_what());

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
}
