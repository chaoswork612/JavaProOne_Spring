package service;

import dao.UserDao;
import model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(User user) {
        return userDao.createUser(user);
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void clearUsers() {
        userDao.clearUsers();
    }
}
