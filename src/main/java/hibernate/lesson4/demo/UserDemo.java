package hibernate.lesson4.demo;

import hibernate.lesson4.controllers.UserController;
import hibernate.lesson4.entities.User;
import hibernate.lesson4.entities.UserType;
import hibernate.lesson4.exceptions.AuthorizeException;
import hibernate.lesson4.utils.HibernateUtil;

public class UserDemo {
    public static void main(String[] args) {
        UserController userController = new UserController();

//        User newUser = new User();
//        newUser.setCountry("Ukraine");
//        newUser.setPassword("Password");
//        newUser.setUserName("Name");
//        newUser.setUserType(UserType.ADMIN);
//
//        userController.registerUser(newUser);

//        User newUser1 = new User();
//        newUser1.setCountry("Ukraine");
//        newUser1.setPassword("Password");
//        newUser1.setUserName("Name1");
//        newUser1.setUserType(UserType.USER);
//
//        userController.registerUser(newUser1);

        try {
            userController.login("Name", "Password");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            userController.logout();
        } catch (AuthorizeException e) {
            System.err.println(e.getMessage());
        }

        HibernateUtil.shutDownSessionFactory();
    }
}
