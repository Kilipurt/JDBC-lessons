package hibernate.lesson4.services;

import hibernate.lesson4.dao.OrderDAO;
import hibernate.lesson4.entities.BookingPeriod;
import hibernate.lesson4.entities.Room;
import hibernate.lesson4.exceptions.BadRequestException;

import java.util.Date;
import java.util.List;

public class OrderValidator {
    private OrderDAO orderDAO = new OrderDAO();

    public void validateBookingPossibility(Room room, long roomId, Date dateFrom, Date dateTo) throws Exception {
        if (room == null)
            throw new NullPointerException("Booking is failed. Room " + roomId + " was not found");

        if (dateFrom == null || dateTo == null)
            throw new NullPointerException("Room " + roomId + " was not booked. Wrong enter dates");

        if (dateFrom.compareTo(new Date()) < 0)
            throw new BadRequestException("Booking is failed. Booking date from " + dateFrom + " is out of date");

        if (dateFrom.compareTo(dateTo) >= 0)
            throw new BadRequestException("Booking is failed. Booking date from " + dateFrom + " is after date to " + dateTo);

        validateBookingPeriod(roomId, dateFrom, dateTo);
    }

    public void validateCancelBookPossibility(long roomId, long userId, Date dateFrom) throws Exception {
        if (roomId <= 0)
            throw new BadRequestException("Canceling is failed. Room " + roomId + " does not exist");

        if (userId <= 0)
            throw new BadRequestException("Canceling is failed. User " + userId + " does not exist");

        if (dateFrom == null)
            throw new NullPointerException("Canceling is failed. Wrong enter booking date from");

        if (dateFrom.compareTo(new Date()) <= 0)
            throw new BadRequestException("Canceling is failed. Booking date from " + dateFrom + " is less than current date");
    }

    private void validateBookingPeriod(long roomId, Date dateFrom, Date dateTo) throws BadRequestException {
        Date currentDate = new Date();
        List<BookingPeriod> periods = orderDAO.getAllBookingPeriods(roomId, currentDate);

        if (periods.size() == 0)
            return;

        if (periods.get(0).getDateFrom().compareTo(currentDate) > 0
                && periods.get(0).getDateFrom().compareTo(dateTo) > 0)
            return;

        for (int i = 0; i < periods.size() - 1; i++) {
            if (periods.get(i).getDateTo().compareTo(dateFrom) < 0
                    && periods.get(i + 1).getDateFrom().compareTo(dateTo) > 0) {
                return;
            }
        }

        if (periods.get(periods.size() - 1).getDateTo().compareTo(dateFrom) < 0)
            return;

        throw new BadRequestException("Room " + roomId + " is not available from " + dateFrom + " to " + dateTo);
    }
}