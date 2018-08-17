package hibernate.lesson2.homework.productDAOandNativeSQL;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class ProductDAO {
    private static SessionFactory sessionFactory;

    private static final String findByIdQuery = "SELECT * FROM PRODUCT WHERE ID = :id";
    private static final String findByNameQuery = "SELECT * FROM PRODUCT WHERE NAME = :name";
    private static final String findByContainedNameQuery = "SELECT * FROM PRODUCT WHERE NAME LIKE :name";
    private static final String findByPriceQuery = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN :minValue AND :maxValue";
    private static final String findByNameSortedAscQuery = "SELECT * FROM PRODUCT ORDER BY NAME ASC";
    private static final String findByNameSortedDescQuery = "SELECT * FROM PRODUCT ORDER BY NAME DESC";
    private static final String findByPriceSortedDescQuery = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN :minValue AND :maxValue ORDER BY PRICE DESC";

    public Product findById(Long id) {
        try (Session session = createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            NativeQuery query = session.createNativeQuery(findByIdQuery);
            query.addEntity(Product.class);
            query.setParameter("id", id);

            if (query.list().size() != 0)
                return (Product) query.list().get(0);

            tr.commit();
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
            Transaction tr = session.getTransaction();
            tr.begin();

            NativeQuery query = session.createNativeQuery(findByNameQuery);
            query.addEntity(Product.class);
            query.setParameter("name", name);

            tr.commit();

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
            Transaction tr = session.getTransaction();
            tr.begin();

            NativeQuery query = session.createNativeQuery(findByContainedNameQuery);
            query.addEntity(Product.class);
            query.setParameter("name", "%" + name + "%");

            tr.commit();

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
            Transaction tr = session.getTransaction();
            tr.begin();

            NativeQuery query = session.createNativeQuery(findByPriceQuery);
            query.addEntity(Product.class);
            query.setParameter("minValue", price - delta);
            query.setParameter("maxValue", price + delta);

            tr.commit();

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
            Transaction tr = session.getTransaction();
            tr.begin();

            NativeQuery query = session.createNativeQuery(findByNameSortedAscQuery);
            query.addEntity(Product.class);

            tr.commit();

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
            Transaction tr = session.getTransaction();
            tr.begin();

            NativeQuery query = session.createNativeQuery(findByNameSortedDescQuery);
            query.addEntity(Product.class);

            tr.commit();

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
            Transaction tr = session.getTransaction();
            tr.begin();

            NativeQuery query = session.createNativeQuery(findByPriceSortedDescQuery);
            query.addEntity(Product.class);
            query.setParameter("minValue", price - delta);
            query.setParameter("maxValue", price + delta);

            tr.commit();

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
