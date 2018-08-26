package hibernate.lesson4.dao;

import hibernate.lesson4.entities.Filter;
import hibernate.lesson4.entities.Room;
import hibernate.lesson4.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RoomDAO extends GeneralDAO<Room> {
    private static final String findAllRoomsByFilterQuery = "FROM hibernate.lesson4.entities.Room R " +
            "WHERE R.hotel.country LIKE :country AND R.hotel.city LIKE :city AND R.hotel.name LIKE :name " +
            "AND (R.breakfastIncluded = :breakfastIncludedOne OR R.breakfastIncluded = :breakfastIncludedTwo) " +
            "AND (R.petsAllowed = :petsAllowedOne OR R.petsAllowed = :petsAllowedTwo) " +
            "AND R.numberOfGuests >= :numberOfGuests AND R.price <= :maxPrice";

    public RoomDAO() {
        setTypeParameterClass(Room.class);
    }


    public List<Room> findAllByFilter(Filter filter) {
        try (Session session = HibernateUtil.createSessionFactory().openSession()) {

            Query query = session.createQuery(findAllRoomsByFilterQuery);

            setStringsParameters(query, filter);
            setNumbersParameters(query, filter);
            setBooleanParameters(query, filter);

            return list(query);
        } catch (HibernateException e) {
            System.err.println("Searching is failed");
            System.err.println(e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public Room save(Room obj) {
        return super.save(obj);
    }

    @Override
    public void delete(long id) {
        super.delete(id);
    }

    @Override
    public void update(Room obj) {
        super.update(obj);
    }

    @Override
    public Room findById(long id) {
        return super.findById(id);
    }

    private void setStringsParameters(Query query, Filter filter) {
        if (filter.getCountry() == null) {
            query.setParameter("country", "%%");
        } else {
            query.setParameter("country", filter.getCountry());
        }

        if (filter.getCity() == null) {
            query.setParameter("city", "%%");
        } else {
            query.setParameter("city", filter.getCity());
        }

        if (filter.getHotelName() == null) {
            query.setParameter("name", "%%");
        } else {
            query.setParameter("name", filter.getHotelName());
        }
    }

    private void setNumbersParameters(Query query, Filter filter) {
        if (filter.getNumberOfGuests() == null) {
            query.setParameter("numberOfGuests", 0);
        } else {
            query.setParameter("numberOfGuests", filter.getNumberOfGuests());
        }

        if (filter.getMaxPrice() == null) {
            query.setParameter("maxPrice", (double) Integer.MAX_VALUE);
        } else {
            query.setParameter("maxPrice", filter.getMaxPrice());
        }
    }

    private void setBooleanParameters(Query query, Filter filter) {
        if (filter.isBreakfastIncluded() == null) {
            query.setParameter("breakfastIncludedOne", 0);
            query.setParameter("breakfastIncludedTwo", 1);
        } else {
            query.setParameter("breakfastIncludedOne", filter.getBreakfastIncluded());
            query.setParameter("breakfastIncludedTwo", filter.getBreakfastIncluded());
        }

        if (filter.isPetsAllowed() == null) {
            query.setParameter("petsAllowedOne", 0);
            query.setParameter("petsAllowedTwo", 1);
        } else {
            query.setParameter("petsAllowedOne", filter.getPetsAllowed());
            query.setParameter("petsAllowedTwo", filter.getPetsAllowed());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Room> list(Query query){
        return query.list();
    }
}
