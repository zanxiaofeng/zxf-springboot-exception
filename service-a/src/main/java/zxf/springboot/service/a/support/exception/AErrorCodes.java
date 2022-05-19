package zxf.springboot.service.a.support.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import zxf.springboot.support.exception.ErrorCode;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AErrorCodes implements ErrorCode {
    A_ERR_SYS_000("A_ERR_SYS_000", "Unexpected Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    A_ERR_BUS_001("A_ERR_BUS_001", "User not found", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String description;
    private final HttpStatus httpStatus;
}
