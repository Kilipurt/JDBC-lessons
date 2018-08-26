package hibernate.lesson3;

import hibernate.lesson3.entities.Hotel;
import hibernate.lesson3.entities.Room;
import hibernate.lesson3.repositories.HotelDAO;
import hibernate.lesson3.repositories.RoomDAO;
import org.hibernate.LazyInitializationException;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        HotelDAO hotelDAO = new HotelDAO();

        Hotel hotel = new Hotel();
        hotel.setCountry("Germany");
        hotel.setCity("Berlin");
        hotel.setStreet("Germanska street");
        hotel.setName("Germanskii hotel");

//        hotelDAO.save(null);

//        hotelDAO.delete(2);

//        hotel.setId(4);
//        hotel.setName("New Germanskii hotel");

//        hotelDAO.update(null);

//        try {
//            System.out.println(hotelDAO.findById(5).toString());
//        }catch(NullPointerException e){
//            System.err.println(e.getMessage());
//        }

        RoomDAO roomDAO = new RoomDAO();

        Room room = new Room();
        room.setBreakfastIncluded(0);
        room.setDateAvailableFrom(new Date());
        room.setHotel(hotel);
        room.setNumberOfGuests(5);
        room.setPetsAllowed(0);
        room.setPrice(1000);

        Room room1 = new Room();
        room.setBreakfastIncluded(0);
        room.setDateAvailableFrom(new Date());
        room.setHotel(hotel);
        room.setNumberOfGuests(5);
        room.setPetsAllowed(0);
        room.setPrice(1000);

//        roomDAO.save(room1);

//        room.setPrice(5000);
//        room.setId(12);
//        room.setHotel(null);

//        roomDAO.update(null);


        try {
            System.out.println(roomDAO.findById(12).toString());
        }  catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }

//        try {
//            System.out.println(roomDAO.findById(2).toString());
//        }catch(LazyInitializationException e){
//            System.err.println(e.getMessage());
//        }catch(NullPointerException e){
//            System.err.println(e.getMessage());
//        }

//        roomDAO.delete(2);
    }
}
