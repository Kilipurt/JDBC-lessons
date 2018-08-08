package hibernate.lesson1.simpleProductRepository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;

public class ProductRepository {
    private static SessionFactory sessionFactory;

    public void save(Product product) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.save(product);

            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.out.println(e.getMessage());

            if (transaction != null)
                transaction.rollback();
        } catch (PersistenceException e) {
            System.err.println("Product " + product.getId() + " already exist");

            if (transaction != null)
                transaction.rollback();
        } finally {
            shutDown();

            if (session != null)
                session.close();
        }
    }

    public void delete(long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.delete(session.get(Product.class, id));

            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.out.println(e.getMessage());

            if (transaction != null)
                transaction.rollback();
        } catch (IllegalArgumentException e) {
            System.err.println("Product " + id + " was not found");

            if (transaction != null)
                transaction.rollback();
        } finally {
            shutDown();

            if (session != null)
                session.close();
        }
    }

    public void update(Product product) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.update(product);

            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.out.println(e.getMessage());

            if (transaction != null)
                transaction.rollback();
        } catch (OptimisticLockException e) {
            System.err.println("Product " + product.getId() + " was not found");

            if (transaction != null)
                transaction.rollback();
        } finally {
            shutDown();

            if (session != null)
                session.close();
        }
    }

    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }

        return sessionFactory;
    }

    private static void shutDown() {
        if (sessionFactory != null)
            sessionFactory.close();
    }
}
