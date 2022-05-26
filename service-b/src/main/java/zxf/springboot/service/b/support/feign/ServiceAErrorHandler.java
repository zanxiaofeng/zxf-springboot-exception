package zxf.springboot.service.b.support.feign;

import org.springframework.stereotype.Component;
import zxf.springboot.service.b.support.exception.BErrorCodes;
import zxf.springboot.support.exception.BusinessException;
import zxf.springboot.service.a.feign.IServiceAErrorHandler;
import zxf.springboot.support.feign.ClientResponse;

@Component
public class ServiceAErrorHandler implements IServiceAErrorHandler {
    @Override
    public void handleException(Exception ex) {
        throw new BusinessException(BErrorCodes.B_BUS_ERR_002, ex);
    }

    @Override
    public void handleErrorResponse(ClientResponse response) {
        throw new BusinessException(BErrorCodes.B_BUS_ERR_002.withCause(response.getError()));
    }
}
