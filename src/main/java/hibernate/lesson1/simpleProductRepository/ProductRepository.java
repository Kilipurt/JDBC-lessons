package hibernate.lesson1.simpleProductRepository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.PersistenceException;

public class ProductRepository {
    private static SessionFactory sessionFactory;

    public void save(Product product) throws Exception {
        validateProduct(product);

        try(Session session = createSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            session.save(product);

            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.out.println(e.getMessage());
        } catch (PersistenceException e) {
            System.err.println("Product " + product.getId() + " already exist");
        } finally {
            shutDown();
        }
    }

    public void delete(long id) {
        try(Session session = createSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            session.delete(session.get(Product.class, id));

            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Product " + id + " was not found");
        } finally {
            shutDown();
        }
    }

    public void update(Product product) throws Exception {
        validateProduct(product);

        try(Session session = createSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            session.update(product);

            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.out.println(e.getMessage());
        } catch (PersistenceException e) {
            System.err.println("Product " + product.getId() + " was not found");
        } finally {
            shutDown();
        }
    }

    private void validateProduct(Product product) throws Exception {
        if (product.getName() == null || product.getName().length() > 20)
            throw new Exception("Wrong enter name");
    }

    private SessionFactory createSessionFactory() {
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
