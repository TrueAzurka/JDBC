package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoJDBCImpl jdbc = new UserDaoJDBCImpl();

    @Override
    public void createUsersTable() throws SQLException {
        jdbc.createUsersTable();
        System.out.println("Таблица создана");
    }

    public void dropUsersTable() throws SQLException {
        jdbc.dropUsersTable();
        System.out.println("Таблица удалена");
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        jdbc.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        jdbc.removeUserById(id);
        System.out.println("Пользователь уделен");
    }

    public List<User> getAllUsers() throws SQLException {
        //return null;
        return jdbc.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        jdbc.cleanUsersTable();
        System.out.println("Таблица очищена");
    }
}
