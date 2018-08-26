package hibernate.lesson4.dao;

import hibernate.lesson4.entities.User;
import hibernate.lesson4.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDAO extends GeneralDAO<User> {
    private static final String findUserByNameAndPasswordQuery = "FROM hibernate.lesson4.entities.User U " +
            "WHERE U.userName = :name AND U.password = :password";

    public UserDAO() {
        setTypeParameterClass(User.class);
    }

    public User findUserByNameAndPassword(String userName, String password) {
        try (Session session = HibernateUtil.createSessionFactory().openSession()) {

            Query query = session.createQuery(findUserByNameAndPasswordQuery);
            query.setParameter("name", userName);
            query.setParameter("password", password);

            return (User) query.uniqueResult();

        } catch (HibernateException e) {
            System.err.println("Saving is failed");
            System.err.println(e.getMessage());
        }

        return null;
    }

    @Override
    public User save(User obj) {
        return super.save(obj);
    }

    @Override
    public void delete(long id) {
        super.delete(id);
    }

    @Override
    public void update(User obj) {
        super.update(obj);
    }

    @Override
    public User findById(long id) {
        return super.findById(id);
    }
}
