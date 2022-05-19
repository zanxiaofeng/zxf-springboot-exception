package zxf.springboot.service.b.support.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import zxf.springboot.support.exception.ErrorCode;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum BErrorCodes implements ErrorCode {
    B_SYS_ERR_000("A_ERR_SYS_000", "Unexpected Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    B_BUS_ERR_001("A_ERR_BUS_001", "Product not found", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String description;
    private final HttpStatus httpStatus;
}
