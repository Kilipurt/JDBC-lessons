package hibernate.lesson4.controllers;

import hibernate.lesson4.entities.Hotel;
import hibernate.lesson4.entities.UserType;
import hibernate.lesson4.exceptions.AuthorizeException;
import hibernate.lesson4.services.HotelService;

import java.util.List;

public class HotelController {
    private HotelService hotelService = new HotelService();

    public void addHotel(Hotel hotel) throws Exception {
        validateUserRights();
        hotelService.addHotel(hotel);
    }

    public void deleteHotel(long hotelId) throws Exception {
        validateUserRights();
        hotelService.deleteHotel(hotelId);
    }

    public List<Hotel> findHotelByCity(String city) throws Exception {
        if (Session.getAuthorizedUser() == null)
            throw new AuthorizeException("User is not authorized");

        return hotelService.findHotelByCity(city);
    }

    public List<Hotel> findHotelByName(String name) throws Exception {
        if (Session.getAuthorizedUser() == null)
            throw new AuthorizeException("User is not authorized");

        return hotelService.findHotelByName(name);
    }

    private void validateUserRights() throws AuthorizeException {
        if (Session.getAuthorizedUser() == null)
            throw new AuthorizeException("User is not authorized");

        if (Session.getAuthorizedUser().getUserTypeEnum() != UserType.ADMIN)
            throw new AuthorizeException("User with id " + Session.getAuthorizedUser().getId() + " has not enough rights");
    }
}
