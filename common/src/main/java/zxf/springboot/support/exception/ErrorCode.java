package zxf.springboot.support.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String getCode();

    String getDescription();

    HttpStatus getHttpStatus();

    default String getExceptionMessage() {
        return String.format("%d, %s, %s", getHttpStatus().value(), getCode(), getDescription());
    }

    default ErrorCode newDescription(String newDescription) {
        String code = this.getCode();
        HttpStatus httpStatus = this.getHttpStatus();
        return new ErrorCode() {
            @Override
            public String getCode() {
                return code;
            }

            @Override
            public String getDescription() {
                return newDescription;
            }

            @Override
            public HttpStatus getHttpStatus() {
                return httpStatus;
            }
        };
    }

    default ErrorCode newHttpStatus(HttpStatus newHttpStatus) {
        String code = this.getCode();
        String description = this.getDescription();
        return new ErrorCode() {
            @Override
            public String getCode() {
                return code;
            }

            @Override
            public String getDescription() {
                return description;
            }

            @Override
            public HttpStatus getHttpStatus() {
                return newHttpStatus;
            }
        };
    }
}
