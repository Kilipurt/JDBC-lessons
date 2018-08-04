package lesson5.simpleProductRepository;

import org.hibernate.Session;

public class ProductRepository {
    public void save(Product product) {
        Session session = new HibernateUtils().createSessionFactory().openSession();
        session.getTransaction().begin();

        session.save(product);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(long id) {
        Session session = new HibernateUtils().createSessionFactory().openSession();
        session.getTransaction().begin();

        session.delete(session.get(Product.class, id));

        session.getTransaction().commit();
        session.close();
    }

    public void update(Product product) {
        Session session = new HibernateUtils().createSessionFactory().openSession();
        session.getTransaction().begin();

        session.update(product);

        session.getTransaction().commit();
        session.close();
    }
}
