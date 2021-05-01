package Controller;

import hu.alkfejl.dao.UserDAOImpl;
import hu.alkfejl.model.User;

import java.util.List;

public class UserControllerImpl implements UserController{

    private DBController dbController;
    private UserDAOImpl userDAO= new UserDAOImpl();

    public UserControllerImpl() {
        this.dbController = new DBController();
    }

    @Override
    public List<User> getAll() {
        return this.userDAO.findAll();
    }

    @Override
    public User login(String uname, String pass) {
        return dbController.login(uname, pass);
    }

    @Override
    public void regiszt(User user) {
        dbController.regiszt(user);
    }

    @Override
    public boolean isExists(String name) {
        return dbController.selectUserByName(name);
    }
}
