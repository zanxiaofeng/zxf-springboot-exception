package zxf.springboot.service.a.feign;

import zxf.springboot.support.feign.ClientResponse;

public interface IServiceAErrorHandler {
    void handleException(Exception ex);

    void handleErrorResponse(ClientResponse response);
}
