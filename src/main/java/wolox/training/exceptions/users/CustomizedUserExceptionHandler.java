package wolox.training.exceptions.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import wolox.training.exceptions.ExceptionResponse;

@ControllerAdvice
@RestController
public class CustomizedUserExceptionHandler extends ResponseEntityExceptionHandler {

    private final String USER_ORIGIN = "user_controller";

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFound(UserNotFoundException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), USER_ORIGIN);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookAlreadyOwnedException.class)
    public final ResponseEntity<ExceptionResponse> handleBookAlreadyOwned(BookAlreadyOwnedException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), USER_ORIGIN);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

}
