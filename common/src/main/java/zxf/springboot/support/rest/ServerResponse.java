package zxf.springboot.support.rest;

import lombok.Getter;
import zxf.springboot.support.exception.BusinessError;

@Getter
public class ServerResponse<T> {
    private BusinessError error;
    private T data;

    public static ServerResponse error(BusinessError error) {
        ServerResponse response = new ServerResponse();
        response.error = error;
        return response;
    }

    public static <T> ServerResponse<T> success(T data) {
        ServerResponse<T> response = new ServerResponse<>();
        response.data = data;
        return response;
    }
}
