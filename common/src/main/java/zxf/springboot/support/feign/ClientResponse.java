package zxf.springboot.support.feign;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zxf.springboot.support.exception.Error;

import java.util.Optional;

@Data
public class ClientResponse<T> {
    private APIError error;
    private T data;

    public boolean isSuccess() {
        return error == null;
    }

    public boolean hasError(String errorCode) {
        return Optional.ofNullable(error)
                .map(APIError::getCode)
                .map(errorCode::equalsIgnoreCase)
                .orElse(false);
    }

    @Getter
    @RequiredArgsConstructor
    public static class APIError implements Error {
        private final String code;
        private final String description;
        private final APIError cause;

        @Override
        public Error copy() {
            return new APIError(code, description, cause);
        }
    }
}
