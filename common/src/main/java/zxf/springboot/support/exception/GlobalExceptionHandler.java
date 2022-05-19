package zxf.springboot.support.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice("zxf.springboot")
public class GlobalExceptionHandler {
    @Autowired
    private ErrorCode unexpectedErrorCode;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException businessException) {
        ErrorCode errorCode = businessException.getErrorCode();
        log.error("Business exception, error code = {}, error message = {}",
                errorCode.getCode(), errorCode.getDescription(), businessException);
        return new ResponseEntity<>(ErrorResponse.of(errorCode.getCode(), errorCode.getDescription()),
                errorCode.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception unexpectedException) {
        return handleBusinessException(new BusinessException(unexpectedErrorCode, unexpectedException));
    }
}
