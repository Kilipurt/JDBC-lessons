package hibernate.lesson4.demo;

import hibernate.lesson4.controllers.OrderController;
import hibernate.lesson4.controllers.UserController;
import hibernate.lesson4.utils.HibernateUtil;

import java.util.Date;

public class OrderDemo {
    public static void main(String[] args) {
        UserController userController = new UserController();
        OrderController orderController = new OrderController();

        try {
            userController.login("Name", "Password");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            orderController.bookRoom(23, new Date(118, 9, 1), new Date(118, 9, 5));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

//        try {
//            orderController.cancelReservation(23, new Date(118, 9, 1));
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }

        HibernateUtil.shutDownSessionFactory();
    }
}