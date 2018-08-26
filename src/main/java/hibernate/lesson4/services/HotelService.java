package hibernate.lesson4.services;

import hibernate.lesson4.dao.HotelDAO;
import hibernate.lesson4.entities.Hotel;
import hibernate.lesson4.exceptions.BadRequestException;

import java.util.List;

public class HotelService {
    private HotelDAO hotelDAO = new HotelDAO();

    public void addHotel(Hotel hotel) throws NullPointerException {
        validateHotel(hotel);
        hotelDAO.save(hotel);
    }

    public void deleteHotel(long hotelId) throws BadRequestException {
        if (hotelId <= 0)
            throw new BadRequestException("Hotel with id " + hotelId + " was not found");

        hotelDAO.delete(hotelId);
    }

    public List<Hotel> findHotelByCity(String city) throws NullPointerException {
        if (city == null || city.isEmpty())
            throw new NullPointerException("Wrong enter city");

        return hotelDAO.findHotelByCity(city);
    }

    public List<Hotel> findHotelByName(String name) throws BadRequestException {
        if (name == null || name.isEmpty())
            throw new BadRequestException("Wrong enter name");

        return hotelDAO.findHotelByName(name);
    }

    private void validateHotel(Hotel hotel) throws NullPointerException {
        if (hotel == null)
            throw new NullPointerException("Data is empty");

        if (hotel.getName() == null || hotel.getName().isEmpty())
            throw new NullPointerException("Wrong enter hotel name");

        if (hotel.getCountry() == null || hotel.getCountry().isEmpty())
            throw new NullPointerException("Wrong enter hotel country");

        if (hotel.getCity() == null || hotel.getCity().isEmpty())
            throw new NullPointerException("Wrong enter hotel city");

        if (hotel.getStreet() == null || hotel.getStreet().isEmpty())
            throw new NullPointerException("Wrong enter hotel street");
    }
}
