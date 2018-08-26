package hibernate.lesson3.repositories;

import hibernate.lesson3.entities.Room;

public class RoomDAO extends GeneralDAO<Room> {
    public RoomDAO() {
        setTypeParameterClass(Room.class);
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
}
