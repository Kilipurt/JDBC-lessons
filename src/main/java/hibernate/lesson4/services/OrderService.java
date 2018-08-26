package hibernate.lesson4.services;

import hibernate.lesson4.dao.OrderDAO;
import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.entities.Order;
import hibernate.lesson4.entities.Room;
import hibernate.lesson4.entities.User;

import java.util.Date;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private RoomDAO roomDAO = new RoomDAO();
    private OrderValidator orderValidator = new OrderValidator();

    public void bookRoom(long roomId, User user, Date dateFrom, Date dateTo) throws Exception {
        Room room = roomDAO.findById(roomId);

        orderValidator.validateBookingPossibility(room, roomId, dateFrom, dateTo);

        Order order = new Order();
        order.setDateFrom(dateFrom);
        order.setDateTo(dateTo);
        order.setMoneyPaid(room.getPrice() * getNumberOfDays(dateFrom, dateTo));
        order.setRoom(room);
        order.setUserOrdered(user);

        orderDAO.save(order);
    }

    public void cancelReservation(long roomId, long userId, Date dateFrom) throws Exception {
        orderValidator.validateCancelBookPossibility(roomId, userId, dateFrom);

        Order order = orderDAO.findByCancelBooking(roomId, userId, dateFrom);

        if (order == null)
            throw new NullPointerException("Cancel is failed. Order with room " + roomId + " and user " + userId + " was not found");

        orderDAO.delete(order.getId());
    }

    private int getNumberOfDays(Date dateFrom, Date dateTo) {
        return (int) ((dateTo.getTime() - dateFrom.getTime()) / (1000 * 60 * 60 * 24));
    }
}
