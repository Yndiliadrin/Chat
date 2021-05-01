package hu.alkfejl.dao;

import hu.alkfejl.model.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll();
    User findUser(String username, String pass);
    List<User> findUserByName(String username);
    List<User> findAllByInterest(String inter);
    List<User> findAllByInterestAndGender(String inter, String gender);
    List<User> findAllByInterestAndAge(String inter, int age);
    List<User> findAllByGender(String gender);
    List<User> findAllByGenderAndAge(String gender, int age);
    List<User> findAllByAge(int age);
    List<User> findAllByInterestAndGenderAndAge(String interest, String gender, int age);


    User save(User user);

    void delete(User user);
}
