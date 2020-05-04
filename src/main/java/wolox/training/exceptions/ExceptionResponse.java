package wolox.training.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class ExceptionResponse {

    private List<ErrorDetail> errors;
    private String message;
    private String origin;
    private Date timestamp;

    public ExceptionResponse(String message, String origin) {
        super();
        this.timestamp = new Date();
        this.message = message;
        this.origin = origin;
    }

    public ExceptionResponse(List<ErrorDetail> errors, String origin) {
        this.errors = errors;
        this.timestamp = new Date();
        this.origin = origin;
    }

    public String getMessage() {
        return message;
    }

    public String getOrigin() {
        return origin;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }
}
