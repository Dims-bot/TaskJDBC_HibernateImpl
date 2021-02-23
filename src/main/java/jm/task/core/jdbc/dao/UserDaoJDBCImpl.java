package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    Util util = new Util();


    public void createUsersTable() {
        String sqlNewTable = "CREATE TABLE `db113`.`users1` (\n" +
                "  `Id` BIGINT(19) NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT(3) NOT NULL,\n" +
                "  PRIMARY KEY (`Id`))\n" +
                "ENGINE = InnoDB\n" +
                "DEFAULT CHARACTER SET = utf8;";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement();)
        {
            statement.executeUpdate(sqlNewTable);
            System.out.println("The table is created.");
        } catch (SQLSyntaxErrorException throwables) {
            System.out.println("Such a table already exists!");;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String sqlDropTable = "drop table db113.users1;";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement();)
        {
            statement.executeUpdate(sqlDropTable);
            System.out.println("The table is dropped.");
        } catch (SQLSyntaxErrorException throwables) {
            System.out.println("There is no such table!");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlSaveUser = "INSERT INTO DB113.USERS1 (NAME, LASTNAME, AGE) VALUES(?, ?, ?)";
        try(Connection connection = util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSaveUser);
        )
        {
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String sqlSaveUser = "DELETE FROM DB113.USERS1 WHERE ID=(?)";
        try(Connection connection = util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSaveUser);
        )
        {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sqlGetAllUsers = "SELECT ID, NAME, LASTNAME, AGE FROM DB113.USERS1;";

        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(sqlGetAllUsers);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
            }
            System.out.println(Arrays.toString(userList.toArray()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sqlDropTable = "TRUNCATE TABLE db113.users1;";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement();)
        {
            statement.executeUpdate(sqlDropTable);

        }  catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
