package zxf.springboot.service.b.support.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zxf.springboot.support.exception.Error;
import zxf.springboot.support.exception.SystemError;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SysErrorCodes implements SystemError {
    B_SYS_ERR_000("B_SYS_ERR_000", "Unexpected Exception");

    private final String code;
    private final String description;
    private Error cause;
}
