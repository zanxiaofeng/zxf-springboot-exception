package zxf.springboot.support.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum GlobalErrorCodes implements BusinessError {
    GLB_SYS_ERR_000("GLB_SYS_ERR_000", "Json schema validation failed");

    private final String code;
    private final String description;
    private BusinessError cause;
}

