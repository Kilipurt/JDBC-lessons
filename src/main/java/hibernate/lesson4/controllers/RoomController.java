package hibernate.lesson4.controllers;

import hibernate.lesson4.entities.Filter;
import hibernate.lesson4.entities.Room;
import hibernate.lesson4.entities.UserType;
import hibernate.lesson4.exceptions.AuthorizeException;
import hibernate.lesson4.services.RoomService;

import java.util.List;

public class RoomController {
    private RoomService roomService = new RoomService();

    public void addRoom(Room room) throws Exception {
        validateUserRights();
        roomService.addRoom(room);
    }

    public void deleteRoom(long roomId) throws Exception {
        validateUserRights();
        roomService.deleteRoom(roomId);
    }

    public Room findById(long roomId) throws Exception {
        if (Session.getAuthorizedUser() == null)
            throw new AuthorizeException("User is not authorize");

        return roomService.findById(roomId);
    }

    public List<Room> findRooms(Filter filter) throws Exception {
        if (Session.getAuthorizedUser() == null)
            throw new AuthorizeException("User is not authorize");

        return roomService.findRooms(filter);
    }

    private void validateUserRights() throws AuthorizeException {
        if (Session.getAuthorizedUser() == null)
            throw new AuthorizeException("User is not authorized");

        if (Session.getAuthorizedUser().getUserTypeEnum() != UserType.ADMIN)
            throw new AuthorizeException("User with id " + Session.getAuthorizedUser().getId() + " has not enough rights");
    }
}
