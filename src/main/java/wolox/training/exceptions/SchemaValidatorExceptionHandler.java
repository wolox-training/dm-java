package wolox.training.exceptions;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class SchemaValidatorExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ExceptionResponse validationError(MethodArgumentNotValidException ex) {
        List<ErrorDetail> errorsDetail = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            FieldError fieldError = (FieldError) error;
            String field = fieldError.getField();
            Object value = fieldError.getRejectedValue();
            String message = fieldError.getDefaultMessage();
            errorsDetail.add(new ErrorDetail(field, value, message));
        });
        return new ExceptionResponse(errorsDetail, "SCHEMA_ORIGIN");
    }
}
