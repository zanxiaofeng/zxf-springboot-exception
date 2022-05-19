package zxf.springboot.support.exception;

public class BusinessException extends RuntimeException {
    private final BusinessError error;

    public BusinessException(BusinessError error) {
        super(error.exceptionMessage());
        this.error = error;
    }

    public BusinessException(BusinessError error, Throwable cause) {
        super(error.exceptionMessage(), cause);
        this.error = error;
    }

    public BusinessError getErrorCode() {
        return error;
    }
}
