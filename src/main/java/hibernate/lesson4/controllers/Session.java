package hibernate.lesson4.controllers;

import hibernate.lesson4.entities.User;

public class Session {
    private static User authorizedUser;

    public static User getAuthorizedUser() {
        return authorizedUser;
    }

    public static void setAuthorizedUser(User authorizedUser) {
        Session.authorizedUser = authorizedUser;
    }
}
