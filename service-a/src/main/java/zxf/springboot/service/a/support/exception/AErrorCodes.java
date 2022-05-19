package zxf.springboot.service.a.support.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zxf.springboot.support.exception.BusinessError;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AErrorCodes implements BusinessError {
    A_SYS_ERR_000("A_SYS_ERR_000", "Unexpected Exception"),

    A_BUS_ERR_001("A_BUS_ERR_001", "User not found");

    private final String code;
    private final String description;
    private BusinessError cause;
}
