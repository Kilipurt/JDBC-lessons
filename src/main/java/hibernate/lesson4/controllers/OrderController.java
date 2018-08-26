package hibernate.lesson4.controllers;

import hibernate.lesson4.exceptions.AuthorizeException;
import hibernate.lesson4.services.OrderService;

import java.util.Date;

public class OrderController {
    private OrderService orderService = new OrderService();

    public void bookRoom(long roomId, Date dateFrom, Date dateTo) throws Exception {
        if (Session.getAuthorizedUser() == null)
            throw new AuthorizeException("User is not authorized");

        orderService.bookRoom(roomId, Session.getAuthorizedUser(), dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, Date dateFrom) throws Exception {
        if (Session.getAuthorizedUser() == null)
            throw new AuthorizeException("User is not authorized");

        orderService.cancelReservation(roomId, Session.getAuthorizedUser().getId(), dateFrom);
    }
}
