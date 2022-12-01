package zxf.springboot.service.b.support.feign;

import org.springframework.stereotype.Component;
import zxf.springboot.service.a.feign.ErrorCodes;
import zxf.springboot.service.b.support.exception.BizErrorCodes;
import zxf.springboot.support.exception.BusinessException;
import zxf.springboot.service.a.feign.IServiceAErrorHandler;
import zxf.springboot.support.feign.ClientResponse;

@Component
public class ServiceAErrorHandler implements IServiceAErrorHandler {
    @Override
    public void handleException(Exception ex) {
        throw new BusinessException(BizErrorCodes.B_BIZ_ERR_002, ex);
    }

    @Override
    public ClientResponse handleErrorResponse(ClientResponse response) {
        if (response.hasError(ErrorCodes.A_BIZ_ERR_001)){
            return response;
        }
        throw new BusinessException(BizErrorCodes.B_BIZ_ERR_002.withCause(response.getError()));
    }
}
