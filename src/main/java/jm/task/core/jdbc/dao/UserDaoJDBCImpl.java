package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection = null;

    static {
        try {
            connection = Util.getConnection();
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении.");
        }
    }
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users" +
                    "(id bigserial PRIMARY KEY, name VARCHAR(255), lastname VARCHAR(255), age smallint)");
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.out.println("Ошибка создания таблицы.");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
            System.out.println("Таблица дропнута");
        } catch (SQLException e) {
            System.out.println("Ошибка дропа таблицы.");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Users (name, lastname, age) values (?,?,?)")) {
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь " + name + " добавлен в таблицу");//добавить поля
        } catch (SQLException e) {
            System.out.println("Ошибка сохранения пользователя.");
        }
    }

    public void removeUserById(long id) {

        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from Users where id = ?")){
            preparedStatement.setLong (1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с id" + id + " удален из таблицы");

        } catch (SQLException e) {
            System.out.println("Ошибка удаления пользователя.");
        }

    }

    public List<User> getAllUsers() {
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet  = statement.executeQuery("SELECT * FROM Users");

            ArrayList<User> users = new ArrayList<>();

            while (resultSet.next()){
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
            return users;
        }
        catch (SQLException e){
            System.out.println("Ошибка получения пользователей.");
            return null;
        }
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE Users");
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.out.println("Ошибка очистки таблицы.");
        }
    }
}
