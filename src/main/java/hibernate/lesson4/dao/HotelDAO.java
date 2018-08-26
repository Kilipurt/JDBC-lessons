package hibernate.lesson4.dao;

import hibernate.lesson4.entities.Hotel;
import hibernate.lesson4.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HotelDAO extends GeneralDAO<Hotel> {
    private static final String findHotelByCityQuery = "FROM hibernate.lesson4.entities.Hotel H WHERE H.city = :city";
    private static final String findHotelByNameQuery = "FROM hibernate.lesson4.entities.Hotel H WHERE H.name = :name";

    public HotelDAO() {
        setTypeParameterClass(Hotel.class);
    }

    public List<Hotel> findHotelByCity(String city) {
        try (Session session = HibernateUtil.createSessionFactory().openSession()) {

            Query query = session.createQuery(findHotelByCityQuery);
            query.setParameter("city", city);

            return list(query);

        } catch (HibernateException e) {
            System.err.println("Saving is failed");
            System.err.println(e.getMessage());
        }

        return null;
    }

    public List<Hotel> findHotelByName(String name) {
        try (Session session = HibernateUtil.createSessionFactory().openSession()) {

            Query query = session.createQuery(findHotelByNameQuery);
            query.setParameter("name", name);

            return list(query);

        } catch (HibernateException e) {
            System.err.println("Saving is failed");
            System.err.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Hotel save(Hotel obj) {
        return super.save(obj);
    }

    @Override
    public void delete(long id) {
        super.delete(id);
    }

    @Override
    public void update(Hotel obj) {
        super.update(obj);
    }

    @Override
    public Hotel findById(long id) {
        return super.findById(id);
    }

    @SuppressWarnings("unchecked")
    public List<Hotel> list(Query query){
        return query.list();
    }
}
