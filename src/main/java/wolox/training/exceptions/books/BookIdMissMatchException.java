package wolox.training.exceptions.books;

public class BookIdMissMatchException extends RuntimeException {

    public BookIdMissMatchException() {
        super("Parameter ID miss match with body Book ID");
    }
}
