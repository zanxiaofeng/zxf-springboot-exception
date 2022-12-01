package zxf.springboot.service.b.support.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zxf.springboot.support.exception.BusinessError;
import zxf.springboot.support.exception.Error;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum BizErrorCodes implements BusinessError {
    B_BIZ_ERR_001("B_BIZ_ERR_001", "Product not found"),
    B_BIZ_ERR_002("B_BIZ_ERR_002", "Call service a error");

    private final String code;
    private final String description;
    private Error cause;
}
