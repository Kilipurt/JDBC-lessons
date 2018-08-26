package hibernate.lesson4.dao;

import hibernate.lesson4.entities.BookingPeriod;
import hibernate.lesson4.entities.Order;
import hibernate.lesson4.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO extends GeneralDAO<Order> {
    private static final String findForCancelBookingQuery = "FROM hibernate.lesson4.entities.Order O " +
            "WHERE O.room.id = :roomId AND O.userOrdered.id = :userId AND O.dateFrom = :dateFrom";

    private static final String selectBookingPeriodsQuery = "SELECT new hibernate.lesson4.entities.BookingPeriod(O.dateFrom, O.dateTo) " +
            "FROM hibernate.lesson4.entities.Order O WHERE O.room.id = :roomId AND O.dateTo >= :currentDate ORDER BY O.dateTo ASC";

    public OrderDAO() {
        setTypeParameterClass(Order.class);
    }

    public List<BookingPeriod> getAllBookingPeriods(long roomId, Date currentDate) {
        try (Session session = HibernateUtil.createSessionFactory().openSession()) {
            Query query = session.createQuery(selectBookingPeriodsQuery);

            query.setParameter("roomId", roomId);
            query.setParameter("currentDate", currentDate);

            return list(query);
        } catch (HibernateException e) {
            System.err.println("Searching is failed");
            System.err.println(e.getMessage());
        }

        return new ArrayList<>();
    }

    public Order findByCancelBooking(long roomId, long userId, Date dateFrom) {
        try (Session session = HibernateUtil.createSessionFactory().openSession()) {
            Query query = session.createQuery(findForCancelBookingQuery);

            query.setParameter("roomId", roomId);
            query.setParameter("userId", userId);
            query.setParameter("dateFrom", dateFrom);

            return (Order) query.uniqueResult();
        } catch (HibernateException e) {
            System.err.println("Searching is failed");
            System.err.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Order save(Order obj) {
        return super.save(obj);
    }

    @Override
    public void delete(long id) {
        super.delete(id);
    }

    @Override
    public void update(Order obj) {
        super.update(obj);
    }

    @Override
    public Order findById(long id) {
        return super.findById(id);
    }

    @SuppressWarnings("unchecked")
    public List<BookingPeriod> list(Query query){
        return query.list();
    }
}
