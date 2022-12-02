package zxf.springboot.service.b.feign;

import zxf.springboot.support.feign.ClientResponse;

public interface IServiceBErrorHandler {
    void handleException(Exception ex) throws Exception;

    ClientResponse handleErrorResponse(ClientResponse response);
}
