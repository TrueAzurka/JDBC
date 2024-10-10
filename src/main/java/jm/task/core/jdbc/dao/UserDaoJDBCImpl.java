package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection = null;

    static {
        try {
            connection = Util.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users" +
                    "(id bigserial PRIMARY KEY, name VARCHAR(255), lastname VARCHAR(255), age smallint)");
        } catch (SQLException e) {
            connection.rollback();
        }
    }


    public void dropUsersTable() throws SQLException {
        String dropTABLE_SQL = "DROP TABLE IF EXISTS users";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(dropTABLE_SQL);
            System.out.println("Table dropped");
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User saved");
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User removed");

        } catch (SQLException e) {
            connection.rollback();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            connection.rollback();
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "DELETE FROM users";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
            System.out.println("Table cleaned");
        } catch (SQLException e) {
            connection.rollback();
        }
    }
}
