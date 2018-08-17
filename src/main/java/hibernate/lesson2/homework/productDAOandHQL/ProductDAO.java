package hibernate.lesson2.homework.productDAOandHQL;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO {
    private static SessionFactory sessionFactory;

    private static final String findByIdQuery = "FROM hibernate.lesson2.homework.productDAOandHQL.Product P WHERE P.id = :id";
    private static final String findByNameQuery = "FROM hibernate.lesson2.homework.productDAOandHQL.Product P WHERE P.name = :name";
    private static final String findByContainedNameQuery = "FROM hibernate.lesson2.homework.productDAOandHQL.Product P WHERE P.name LIKE :name";
    private static final String findByPriceQuery = "FROM hibernate.lesson2.homework.productDAOandHQL.Product P WHERE P.price BETWEEN :minValue AND :maxValue";
    private static final String findByNameSortedAscQuery = "FROM hibernate.lesson2.homework.productDAOandHQL.Product P ORDER BY P.name ASC";
    private static final String findByNameSortedDescQuery = "FROM hibernate.lesson2.homework.productDAOandHQL.Product P ORDER BY P.name DESC";
    private static final String findByPriceSortedDescQuery = "FROM hibernate.lesson2.homework.productDAOandHQL.Product P WHERE P.price BETWEEN :minValue AND :maxValue ORDER BY P.price DESC";

    public Product findById(Long id) {
        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(findByIdQuery);
            query.setParameter("id", id);

            if (query.list().size() != 0)
                return (Product) query.list().get(0);

        } catch (HibernateException e) {
            System.err.println("Something went wrong");
            System.out.println(e.getMessage());
        } finally {
            shutDown();
        }

        return null;
    }

    public List<Product> findByName(String name) {
        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(findByNameQuery);
            query.setParameter("name", name);

            return query.list();
        } catch (HibernateException e) {
            System.err.println("Something went wrong");
            System.out.println(e.getMessage());
        } finally {
            shutDown();
        }

        return null;
    }

    public List<Product> findByContainedName(String name) {
        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(findByContainedNameQuery);
            query.setParameter("name", "%" + name + "%");

            return query.list();
        } catch (HibernateException e) {
            System.err.println("Something went wrong");
            System.out.println(e.getMessage());
        } finally {
            shutDown();
        }

        return null;
    }

    public List<Product> findByPrice(int price, int delta) {
        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(findByPriceQuery);
            query.setParameter("minValue", price - delta);
            query.setParameter("maxValue", price + delta);

            return query.list();
        } catch (HibernateException e) {
            System.err.println("Something went wrong");
            System.out.println(e.getMessage());
        } finally {
            shutDown();
        }

        return null;
    }

    public List<Product> findByNameSortedAsc() {
        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(findByNameSortedAscQuery);

            return query.list();
        } catch (HibernateException e) {
            System.err.println("Something went wrong");
            System.out.println(e.getMessage());
        } finally {
            shutDown();
        }

        return null;
    }

    public List<Product> findByNameSortedDesc() {
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery(findByNameSortedDescQuery);

            return query.list();
        } catch (HibernateException e) {
            System.err.println("Something went wrong");
            System.out.println(e.getMessage());
        } finally {
            shutDown();
        }

        return null;
    }

    public List<Product> findByPriceSortedDesc(int price, int delta) {
        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(findByPriceSortedDescQuery);
            query.setParameter("minValue", price - delta);
            query.setParameter("maxValue", price + delta);

            return query.list();
        } catch (HibernateException e) {
            System.err.println("Something went wrong");
            System.out.println(e.getMessage());
        } finally {
            shutDown();
        }

        return null;
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