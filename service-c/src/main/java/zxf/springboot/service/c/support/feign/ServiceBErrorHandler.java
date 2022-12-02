package zxf.springboot.service.c.support.feign;

import org.springframework.stereotype.Component;
import zxf.springboot.service.b.feign.IServiceBErrorHandler;
import zxf.springboot.support.feign.ClientResponse;

@Component
public class ServiceBErrorHandler implements IServiceBErrorHandler {
    @Override
    public void handleException(Exception ex) throws Exception {
        throw ex;
    }

    @Override
    public ClientResponse handleErrorResponse(ClientResponse response) {
        return response;
    }
}
