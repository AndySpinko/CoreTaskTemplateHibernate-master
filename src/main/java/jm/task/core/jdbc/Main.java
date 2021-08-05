package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Matt", "Bellamy", (byte) 41);
        userService.saveUser("Tom", "Chaplin", (byte) 35);
        userService.saveUser("Jim", "Morrison", (byte) 27);
        userService.saveUser("John", "Daw", (byte) 65);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
