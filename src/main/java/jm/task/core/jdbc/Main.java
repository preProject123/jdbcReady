package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

        userService.createUsersTable();

        User user1 = new User("Вася", "Пупкин", (byte) 28);
        User user2 = new User("Арсений", "Аношко", (byte) 54);
        User user3 = new User("Джейсон", "Стетхем", (byte) 12);
        User user4 = new User("Геннадий", "Букин", (byte) 14);

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());


        List <User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}