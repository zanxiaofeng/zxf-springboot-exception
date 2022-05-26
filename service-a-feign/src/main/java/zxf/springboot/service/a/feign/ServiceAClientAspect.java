package zxf.springboot.service.a.feign;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zxf.springboot.support.exception.BusinessException;
import zxf.springboot.support.feign.ClientResponse;

@Slf4j
@Aspect
@Component
public class ServiceAClientAspect {
    @Autowired
    private IServiceAErrorHandler serviceAExceptionHandler;

    @Around("execution(public * zxf.springboot.service.a.feign.ServiceAClient.*(..))")
    public Object httpRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            ClientResponse response = (ClientResponse) joinPoint.proceed();
            if (!response.isSuccess()) {
                serviceAExceptionHandler.handleErrorResponse(response);
            }
            return response;
        } catch (BusinessException businessException) {
            throw businessException;
        } catch (Exception ex) {
            serviceAExceptionHandler.handleException(ex);
        }
        return null;
    }
}
