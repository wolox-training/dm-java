package wolox.training.exceptions;

import java.util.Date;

public class ExceptionResponse {

  private String message;
  private String origin;
  private Date timestamp;

  public ExceptionResponse(String message, String origin) {
    super();
    this.timestamp = new Date();
    this.message = message;
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
}
