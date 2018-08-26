package hibernate.lesson4.demo;

import hibernate.lesson4.controllers.RoomController;
import hibernate.lesson4.controllers.UserController;
import hibernate.lesson4.entities.*;
import hibernate.lesson4.utils.HibernateUtil;

public class RoomDemo {
    public static void main(String[] args) {
        UserController userController = new UserController();
        RoomController roomController = new RoomController();

        try {
            userController.login("Name", "Password");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        Hotel hotel = new Hotel();
        hotel.setId(22L);
        hotel.setCity("Kopengagen");
        hotel.setCountry("Denmark");
        hotel.setName("Victory");
        hotel.setStreet("Main");

        Room room = new Room();
        room.setBreakfastIncluded(true);
        room.setPetsAllowed(true);
        room.setHotel(hotel);
        room.setNumberOfGuests(4);
        room.setPrice(500);

//        try {
//            roomController.addRoom(room);
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }

//        try {
//            roomController.deleteRoom(22);
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }

        Filter filter = new Filter(null, null, null, null, null, null, null);

        try {
            System.out.println(roomController.findRooms(filter));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        HibernateUtil.shutDownSessionFactory();
    }
}
