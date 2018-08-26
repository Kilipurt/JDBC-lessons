package hibernate.lesson3.repositories;

import hibernate.lesson3.entities.Hotel;

public class HotelDAO extends GeneralDAO<Hotel> {
    public HotelDAO() {
        setTypeParameterClass(Hotel.class);
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
}
