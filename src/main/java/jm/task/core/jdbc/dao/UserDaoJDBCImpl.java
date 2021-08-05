package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `users` (`id` INT NOT NULL AUTO_INCREMENT, " +
                "`name` VARCHAR(45) NULL, `lastName` VARCHAR(45) NULL, " +
                "`age` INT NULL, PRIMARY KEY (`id`));";
        try (Statement statement = util.getConnection().createStatement()) {
            statement.execute(sql);
            System.out.println("Users table successfully created");
        } catch (SQLException ex) {
            System.out.println("ERROR: Users table wasn`t created");
            ex.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE users";
        try (Statement statement = util.getConnection().createStatement()) {
            statement.execute(sql);
            System.out.println("Users table successfully deleted");
        } catch (SQLException ex) {
            System.out.println("ERROR: Users table wasn`t deleted");
            ex.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users VALUES (id,'" + name + "', '" + lastName + "', " + age + ");";
        try (Statement statement = util.getConnection().createStatement();) {
            statement.executeUpdate(sql);
            System.out.println("User – " + name + " was added to table successfully");
        } catch (SQLException throwables) {
            System.out.println("ERROR: user wasn`t added to table");
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = " + id + ";";
        try (Statement statement = util.getConnection().createStatement()) {
            statement.execute(sql);
            System.out.println("User with id " + id + " was deleted successfully");
        } catch (SQLException ex) {
            System.out.println("ERROR: user wasn`t deleted");
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        ResultSet resultSet;
        List<User> usersList = new ArrayList<>();
        try (Statement statement = util.getConnection().createStatement()) {
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getNString("lastName"));
                user.setAge(resultSet.getByte("age"));
                usersList.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: can`t get all users");
            ex.printStackTrace();
        }
        for (User user : usersList) {
            System.out.println(user);
        }
        return usersList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Statement statement = util.getConnection().createStatement()) {
            statement.execute(sql);
            System.out.println("Users table cleaned successfully");
        } catch (SQLException ex) {
            System.out.println("ERROR users table wasn`t cleaned");
            ex.printStackTrace();
        }
    }
}
