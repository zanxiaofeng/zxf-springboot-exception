package zxf.springboot.support.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
}
