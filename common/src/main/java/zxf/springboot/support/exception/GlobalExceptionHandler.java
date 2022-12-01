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
    private SystemError unexpectedErrorCode;

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ServerResponse> handleBadRequestException(BadRequestException badRequestException) {
        log.error("BadRequest exception", badRequestException);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ServerResponse> handleBusinessException(BusinessException businessException) {
        log.error("Business exception", businessException);
        BusinessError errorCode = businessException.getErrorCode();
        return ResponseEntity.ok().body(ServerResponse.error(errorCode.copy()));
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ServerResponse> handleSystemException(SystemException systemException) {
        log.error("System exception", systemException);
        SystemError errorCode = systemException.getErrorCode();
        return ResponseEntity.ok().body(ServerResponse.error(errorCode.copy()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ServerResponse> handleValidationException(ValidationException validationException) {
        return handleBadRequestException(new BadRequestException(validationException));
    }

    @ExceptionHandler
    public ResponseEntity<ServerResponse> handleUnexpectedException(Exception unexpectedException) {
        return handleSystemException(new SystemException(unexpectedErrorCode, unexpectedException));
    }
}
