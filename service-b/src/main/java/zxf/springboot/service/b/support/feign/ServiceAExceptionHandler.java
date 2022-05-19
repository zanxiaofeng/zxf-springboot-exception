package zxf.springboot.service.b.support.feign;

import org.springframework.stereotype.Component;
import zxf.springboot.service.b.support.exception.BErrorCodes;
import zxf.springboot.support.exception.BusinessException;
import zxf.springboot.service.a.feign.IServiceAExceptionHandler;

@Component
public class ServiceAExceptionHandler implements IServiceAExceptionHandler {
    @Override
    public void handleException(Exception ex) {
        throw new BusinessException(BErrorCodes.B_BUS_ERR_002, ex);
    }
}
