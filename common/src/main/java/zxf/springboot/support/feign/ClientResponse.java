package zxf.springboot.support.feign;

import lombok.*;
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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class APIError implements Error {
        private String code;
        private String description;
        private APIError cause;

        @Override
        public Error copy() {
            return new APIError(code, description, cause);
        }
    }
}
