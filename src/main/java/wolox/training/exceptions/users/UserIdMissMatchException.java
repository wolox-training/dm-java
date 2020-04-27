package wolox.training.exceptions.users;

public class UserIdMissMatchException extends RuntimeException {

    public UserIdMissMatchException() {
        super("Parameter ID miss match with body Book ID");
    }
}
