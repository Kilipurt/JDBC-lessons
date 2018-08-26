package hibernate.lesson4.services;

import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.entities.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public void registerUser(User user) throws NullPointerException {
        validateUser(user);

        userDAO.save(user);
    }

    public User login(String userName, String password) throws NullPointerException {
        if (userName == null)
            throw new NullPointerException("Login is failed. Wrong enter user name");

        if (password == null)
            throw new NullPointerException("Login is failed. Wrong enter password");

        return userDAO.findUserByNameAndPassword(userName, password);
    }

    private void validateUser(User user) throws NullPointerException {
        if (user.getUserName() == null || user.getUserName().isEmpty())
            throw new NullPointerException("Wrong enter user name");

        if (user.getPassword() == null || user.getPassword().isEmpty())
            throw new NullPointerException("Wrong enter password");

        if (user.getCountry() == null || user.getCountry().isEmpty())
            throw new NullPointerException("Wrong enter country");

        if (user.getUserType() == null)
            throw new NullPointerException("Wrong enter user type");
    }
}
