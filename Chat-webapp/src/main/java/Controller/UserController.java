package Controller;

import hu.alkfejl.model.User;

import java.util.List;

public interface UserController {
    public List<User> getAll();

    public User login(String uname, String pass);

    public void regiszt(User user);

    public boolean isExists(String name);
}
