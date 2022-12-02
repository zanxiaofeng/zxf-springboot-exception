package zxf.springboot.service.c.support.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zxf.springboot.support.exception.BusinessError;
import zxf.springboot.support.exception.Error;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum BizErrorCodes implements BusinessError {
    C_BIZ_ERR_001("C_BIZ_ERR_001", "Call service a error"),
    C_BIZ_ERR_002("C_BIZ_ERR_002", "Create user error"),
    C_BIZ_ERR_003("C_BIZ_ERR_003", "Find user error");

    private final String code;
    private final String description;
    private Error cause;
}
