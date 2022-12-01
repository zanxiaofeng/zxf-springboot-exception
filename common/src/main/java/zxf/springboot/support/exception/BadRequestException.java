package zxf.springboot.support.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
