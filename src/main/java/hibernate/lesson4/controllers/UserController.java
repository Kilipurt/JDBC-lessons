package hibernate.lesson4.controllers;

import hibernate.lesson4.entities.User;
import hibernate.lesson4.exceptions.AuthorizeException;
import hibernate.lesson4.services.UserService;

public class UserController {
    private UserService userService = new UserService();

    public void registerUser(User user) throws NullPointerException {
        userService.registerUser(user);
    }

    public void login(String userName, String password) throws Exception {
        User user = userService.login(userName, password);

        if (user == null)
            throw new AuthorizeException("User with user name " + userName + " does not exist or password is wrong");

        Session.setAuthorizedUser(user);
        System.out.println("Welcome " + userName);
    }

    public void logout() throws AuthorizeException {
        if (Session.getAuthorizedUser() == null)
            throw new AuthorizeException("User is not authorized");

        Session.setAuthorizedUser(null);
        System.out.println("Good luck");
    }
}
