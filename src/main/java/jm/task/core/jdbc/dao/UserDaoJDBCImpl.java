package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String creationString = "create table if not exists user(id INTEGER NOT NULL AUTO_INCREMENT, " +
            "name char(30) not null, lastName char(30) not null, age smallint not null, primary key (id))";
    private static final String droppingString = "drop table  IF EXISTS user";
    private static final String savingString = "INSERT INTO user (name,lastName,age) values(?,?,?)";
    private static final String deletingString = "DELETE FROM user WHERE id = ?";
    private static final String selectingString = "select * from user";
    private static final String truncatingString = "TRUNCATE TABLE user";
    private static final Connection CONNECTION = Util.connectStuff();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(creationString);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute(droppingString);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = CONNECTION.
                    prepareStatement(savingString);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = CONNECTION.
                    prepareStatement(deletingString);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectingString);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                Long id = resultSet.getLong("id");
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;

    }

    public void cleanUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(truncatingString);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
