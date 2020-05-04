package wolox.training.exceptions;

public class ErrorDetail {

    private String message;
    private Object value;
    private String field;

    public ErrorDetail(String field, Object value, String message) {
        this.field = field;
        this.value = value;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Object getValue() {
        return value;
    }

    public String getField() {
        return field;
    }
}
