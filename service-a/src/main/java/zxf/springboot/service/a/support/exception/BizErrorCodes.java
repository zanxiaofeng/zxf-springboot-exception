package zxf.springboot.service.a.support.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zxf.springboot.support.exception.BusinessError;
import zxf.springboot.support.exception.Error;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum BizErrorCodes implements BusinessError {
    A_BIZ_ERR_001("A_BIZ_ERR_001", "User not found"),
    A_BIZ_ERR_002("A_BIZ_ERR_002", "User is locked");

    private final String code;
    private final String description;
    private Error cause;
}
