package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        Transaction transaction = null;

        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String sqlNewTable = "CREATE TABLE IF NOT EXISTS users1 (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "lastName VARCHAR(60) not NULL, " +
                    "age TINYINT(3) NOT NULL)";

            Query<User> query = session.createSQLQuery(sqlNewTable).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;

        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            //String sqlDropUsersTable = "DROP TABLE IF EXISTS users1";

//            Query query = session.createSQLQuery(sqlDropUsersTable).addEntity(User.class);
//            query.executeUpdate();
//            transaction.commit();

            Query<User> query = session.createSQLQuery("DROP TABLE IF EXISTS users1");
            query.executeUpdate();
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;

        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(new User(name, lastName, age));
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;

        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        } catch (EntityNotFoundException e) {
            System.out.println("The user with this id is not in the database!");
        }
    }

    @Override
    public List<User> getAllUsers() {

        //Transaction transaction = null;
        List<User> list = null;

        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession();) {
            //transaction = session.beginTransaction();

            Query<User> query = session.createQuery("FROM User ");
            list = query.list();

            System.out.println(Arrays.toString(list.toArray()));
            //transaction.commit();

//        } catch (HibernateException e) {
//            if (transaction != null) {
//                transaction.rollback();
//                e.printStackTrace();
//            }
       }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;

        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();

            Query<User> query = session.createQuery("DELETE from User");
            query.executeUpdate();
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }

    }
}
