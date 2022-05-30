package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.connectStuff().createStatement()) {
            statement.executeUpdate("create table if not exists user(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    "name char(30) not null, lastName char(30) not null, age smallint not null, primary key (id))");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.connectStuff().createStatement()) {
            statement.execute("drop table User");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = Util.connectStuff().createStatement()) {
            statement.executeUpdate("INSERT INTO user (name,lastName,age) values ('" + name + "', '"
                    + lastName + "','" + age + "')");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = Util.connectStuff().createStatement()) {
            statement.executeUpdate("DELETE FROM user WHERE id = " + id);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = Util.connectStuff().createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                users.add(user);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;

    }

    public void cleanUsersTable() {
        try (Statement statement = Util.connectStuff().createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE user");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
