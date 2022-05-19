package zxf.springboot.service.b.support.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zxf.springboot.support.exception.BusinessError;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum BErrorCodes implements BusinessError {
    B_SYS_ERR_000("B_SYS_ERR_000", "Unexpected Exception"),

    B_BUS_ERR_001("B_BUS_ERR_001", "Product not found"),
    B_BUS_ERR_002("B_BUS_ERR_002", "Call service a error");

    private final String code;
    private final String description;
    private BusinessError cause;
}
