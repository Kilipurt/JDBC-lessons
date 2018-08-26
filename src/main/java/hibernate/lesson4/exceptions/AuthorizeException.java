package hibernate.lesson4.exceptions;

public class AuthorizeException extends Exception {
    public AuthorizeException(String message) {
        super(message);
    }
}
