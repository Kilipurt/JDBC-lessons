package hibernate.lesson4.demo;

import hibernate.lesson4.controllers.HotelController;
import hibernate.lesson4.controllers.UserController;
import hibernate.lesson4.entities.Hotel;
import hibernate.lesson4.utils.HibernateUtil;

public class HotelDemo {
    public static void main(String[] args) {
        UserController userController = new UserController();
        HotelController hotelController = new HotelController();

        try {
            userController.login("Name", "Password");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        Hotel hotel = new Hotel();
        hotel.setCity("Kopengagen");
        hotel.setCountry("Denmark");
        hotel.setName("Victory");
        hotel.setStreet("Main");

        try {
            hotelController.addHotel(hotel);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

//        try {
//            hotelController.deleteHotel(21);
//        } catch(Exception e){
//            System.err.println(e.getMessage());
//        }

//        try {
//            System.out.println(hotelController.findHotelByCity("Kopengagen"));
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }

//        try{
//            System.out.println(hotelController.findHotelByName("Victor"));
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }

        HibernateUtil.shutDownSessionFactory();
    }
}
