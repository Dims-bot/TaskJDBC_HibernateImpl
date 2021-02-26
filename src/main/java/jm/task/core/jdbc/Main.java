package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Ilon", "Musk", (byte) 45);
        userDaoHibernate.saveUser("Ivan", "Ivanov", (byte) 18);
        userDaoHibernate.saveUser("Maria", "Sharapova", (byte) 38);
        userDaoHibernate.saveUser("Barack", "Obama", (byte) 55);
        userDaoHibernate.getAllUsers();
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();


    }
}
