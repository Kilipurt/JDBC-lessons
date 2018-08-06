package hibernate.lesson1.simpleProductRepository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private SessionFactory sessionFactory;

    public SessionFactory createSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
