package zxf.springboot.support.feign;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zxf.springboot.support.exception.BusinessError;

@Data
public class ClientResponse<T> {
    private APIError error;
    private T data;

    public boolean isSuccess() {
        return error == null;
    }

    @Getter
    @RequiredArgsConstructor
    public static class APIError implements BusinessError {
        private final String code;
        private final String description;
        private final BusinessError cause;
    }
}
