package zxf.springboot.support.exception;

public class SystemException extends RuntimeException {
    private final SystemError error;

    public SystemException(SystemError error) {
        super(error.exceptionMessage());
        this.error = error;
    }

    public SystemException(SystemError error, Throwable cause) {
        super(error.exceptionMessage(), cause);
        this.error = error;
    }

    public SystemError getErrorCode() {
        return error;
    }
}
