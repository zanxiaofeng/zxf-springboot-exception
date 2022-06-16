package zxf.springboot.support.exception;

import lombok.extern.slf4j.Slf4j;
import org.everit.json.schema.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import zxf.springboot.support.rest.ServerResponse;

@Slf4j
@ControllerAdvice("zxf.springboot")
public class GlobalExceptionHandler {
    @Autowired
    private BusinessError unexpectedErrorCode;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ServerResponse> handleBusinessException(BusinessException businessException) {
        log.error("Business exception", businessException);
        BusinessError errorCode = businessException.getErrorCode();
        return ResponseEntity.ok(ServerResponse.error(errorCode.copy()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ServerResponse> handleValidationException(ValidationException validationException) {
        return handleBusinessException(new BusinessException(GlobalErrorCodes.GLB_SYS_ERR_000, validationException));
    }

    @ExceptionHandler
    public ResponseEntity<ServerResponse> handleUnexpectedException(Exception unexpectedException) {
        return handleBusinessException(new BusinessException(unexpectedErrorCode, unexpectedException));
    }
}
