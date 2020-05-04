package wolox.training.exceptions.users;

public class BookAlreadyOwnedException extends RuntimeException {

    public BookAlreadyOwnedException(String message) {
        super(message);
    }
}
