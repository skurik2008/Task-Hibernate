package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction trans = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            trans = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users (id int PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastname VARCHAR(50), age TINYINT UNSIGNED)";
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            trans.commit();
        } catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
            e.getStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction trans = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            trans = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            session.createSQLQuery(sql).executeUpdate();
            trans.commit();
        } catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
            e.getStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction trans = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            trans = session.beginTransaction();
            session.save(new User(name, lastName, age));
            trans.commit();
        } catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
            e.getStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction trans = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            trans = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
            e.getStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            list = session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction trans = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            trans = session.beginTransaction();
            String hql = "DELETE FROM User";
            session.createQuery(hql).executeUpdate();
            trans.commit();
        } catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
            e.getStackTrace();
        }
    }
}
