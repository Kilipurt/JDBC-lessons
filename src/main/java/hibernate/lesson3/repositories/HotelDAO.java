package hibernate.lesson3.repositories;

import hibernate.lesson3.entities.Hotel;

public class HotelDAO extends GeneralDAO<Hotel> {
    public HotelDAO() {
        setTypeParameterClass(Hotel.class);
    }
}
