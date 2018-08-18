package hibernate.lesson3.repositories;

import hibernate.lesson3.entities.Room;

public class RoomDAO extends GeneralDAO<Room> {
    public RoomDAO() {
        setTypeParameterClass(Room.class);
    }
}
