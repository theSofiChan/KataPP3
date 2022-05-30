package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vasya", "Pupkin", (byte) 30);
        userService.saveUser("Masha", "Valeeva", (byte) 20);
        userService.saveUser("Katya", "Pavlova", (byte) 40);
        List<User> users = userService.getAllUsers();
        for(User user:users){
            System.out.println(user);
        }
        userService.dropUsersTable();



//        UserDao userDao = new UserDaoJDBCImpl();
//        userDao.createUsersTable();
//        userDao.saveUser("Name1", "LastName1", (byte) 20);
//        userDao.saveUser("Name2", "LastName2", (byte) 25);
//        userDao.saveUser("Name3", "LastName3", (byte) 31);
//        userDao.saveUser("Name4", "LastName4", (byte) 38);
//        System.out.println(userDao.getAllUsers());
//        userDao.removeUserById(1);
//        System.out.println(userDao.getAllUsers());
//        userDao.getAllUsers();
//        System.out.println(userDao.getAllUsers());
//        userDao.cleanUsersTable();
//        System.out.println(userDao.getAllUsers());
//        userDao.dropUsersTable();
//        System.out.println(userDao.getAllUsers());


    }
}
