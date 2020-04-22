package wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import wolox.training.exceptions.books.BookIdMissMatchException;
import wolox.training.exceptions.books.BookNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

  private final String BOOK_ORIGIN = "book_controller";

  @ExceptionHandler(BookNotFoundException.class)
  public final ResponseEntity<ExceptionResponse> handleBookNotFound(BookNotFoundException exception) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), BOOK_ORIGIN);
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BookIdMissMatchException.class)
  public final ResponseEntity<ExceptionResponse> handleBookIdMissMatch(BookIdMissMatchException exception) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), BOOK_ORIGIN);
    return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
  }
}