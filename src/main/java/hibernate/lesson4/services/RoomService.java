package hibernate.lesson4.services;

import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.entities.Filter;
import hibernate.lesson4.entities.Room;
import hibernate.lesson4.exceptions.BadRequestException;

import java.util.List;

public class RoomService {
    private RoomDAO roomDAO = new RoomDAO();

    public void addRoom(Room room) throws Exception {
        validateRoom(room);

        roomDAO.save(room);
    }

    public void deleteRoom(long roomId) throws BadRequestException {
        if (roomId <= 0)
            throw new BadRequestException("Deleting room is failed. Wrong enter id " + roomId);

        roomDAO.delete(roomId);
    }

    public Room findById(long roomId) throws BadRequestException {
        if (roomId <= 0)
            throw new BadRequestException("Searching is failed. Room " + roomId + " does not exist");

        return roomDAO.findById(roomId);
    }

    public List<Room> findRooms(Filter filter) throws NullPointerException {
        if (filter == null)
            throw new NullPointerException("Searching is failed. Filter is empty");

        return roomDAO.findAllByFilter(filter);
    }

    private void validateRoom(Room room) throws Exception {
        if (room.getNumberOfGuests() <= 0)
            throw new BadRequestException("Wrong enter number of guests");

        if (room.getPrice() < 0)
            throw new BadRequestException("Wrong enter price");

        if (room.getHotel() == null)
            throw new NullPointerException("Wrong enter hotel");
    }
}
