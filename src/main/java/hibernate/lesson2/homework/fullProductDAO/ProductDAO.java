package hibernate.lesson2.homework.fullProductDAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.PersistenceException;
import java.util.List;

public class ProductDAO {
    private static SessionFactory sessionFactory;

    public Product save(Product product) throws Exception {
        validateProduct(product);

        try (Session session = createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.save(product);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.out.println(e.getMessage());
        } finally {
            shutDown();
        }

        return product;
    }

    public Product delete(Product product) {
        try (Session session = createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.delete(product);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Deleting is failed");
            System.out.println(e.getMessage());
        } catch (PersistenceException e) {
            System.err.println("Product\n" + product.toString() + "\nwas not found");
        } finally {
            shutDown();
        }

        return product;
    }

    public Product update(Product product) throws Exception {
        validateProduct(product);

        try (Session session = createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.update(product);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.out.println(e.getMessage());
        } catch (PersistenceException e) {
            System.err.println("Product " + product.getId() + " was not found");
        } finally {
            shutDown();
        }

        return product;
    }

    public void saveAll(List<Product> products) throws Exception {
        for (Product product : products) {
            validateProduct(product);
        }

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            for (Product product : products) {
                session.save(product);
                session.flush();
            }

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.out.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        } finally {
            shutDown();
        }
    }

    public void updateAll(List<Product> products) throws Exception {
        for (Product product : products) {
            validateProduct(product);
        }

        long id = 0;
        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            for (Product product : products) {
                id = product.getId();
                session.update(product);
                session.flush();
            }

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.out.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        } catch (PersistenceException e) {
            System.err.println("Product " + id + " was not found.\n" + e.getMessage());

            if (tr != null)
                tr.rollback();
        } finally {
            shutDown();
        }
    }

    public void deleteAll(List<Product> products) {
        Product pr = new Product();
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            for (Product product : products) {
                pr = product;
                session.delete(product);
            }

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.out.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        } catch (PersistenceException e) {
            System.err.println("Product\n" + pr.toString() + "\nwas not found");

            if (tr != null)
                tr.rollback();
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

    private void shutDown() {
        if (sessionFactory != null)
            sessionFactory.close();
    }
}
