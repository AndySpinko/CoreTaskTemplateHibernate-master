package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS `users` " +
                "(`id` INT NOT NULL AUTO_INCREMENT, " +
                "`name` VARCHAR(45) NULL, `lastName` VARCHAR(45) NULL, " +
                "`age` INT NULL, PRIMARY KEY (`id`))");
        query.executeUpdate();
        transaction.commit();
        System.out.println("Users table successfully created");
        session.close();
        if (transaction != null) {
            System.out.println("ERROR: Users table wasn`t created");
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createSQLQuery("DROP TABLE users");
        query.executeUpdate();
        transaction.commit();
        System.out.println("Users table successfully deleted");
        session.close();
        if (transaction != null) {
            System.out.println("ERROR: Users table wasn`t deleted");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        User user = new User(name, lastName, age);
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        System.out.println("User – " + name + " was added to table successfully");
        session.close();
        if (transaction != null) {
            System.out.println("ERROR: user wasn`t added to table");
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createSQLQuery("delete User where id = :id");
        query.setParameter("id", id);
        transaction.commit();
        System.out.println("user with id " + id + " deleted");
        session.close();
        if (transaction != null) {
            System.out.println("ERROR: user wasn`t deleted");
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> allUsers = new ArrayList<>();
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("from User");
        allUsers = (List<User>) query.list();
        for (User user : allUsers) {
            System.out.println(user);
        }
        session.close();
        if (transaction != null) {
            System.out.println("ERROR: can`t get all users");
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createSQLQuery("delete from users");
        query.executeUpdate();
        transaction.commit();
        System.out.println("Users table cleaned successfully");
        session.close();
        if (transaction != null) {
            System.out.println("ERROR users table wasn`t cleaned");
        }
    }
}
