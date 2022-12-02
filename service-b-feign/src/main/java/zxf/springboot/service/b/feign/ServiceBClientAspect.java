package zxf.springboot.service.b.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zxf.springboot.support.feign.ClientResponse;

@Slf4j
@Aspect
@Component
public class ServiceBClientAspect {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IServiceBErrorHandler serviceAExceptionHandler;

    @Around("execution(public * zxf.springboot.service.b.feign.ServiceBClient.*(..))")
    public Object httpRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        ClientResponse response = null;
        try {
            response = (ClientResponse) joinPoint.proceed();
        } catch (FeignException.InternalServerError internalServerError) {
            response = objectMapper.readValue(internalServerError.contentUTF8(), ClientResponse.class);
        } catch (Exception ex) {
            serviceAExceptionHandler.handleException(ex);
        }

        if (response != null && response.isSuccess()) {
            return response;
        }

        return serviceAExceptionHandler.handleErrorResponse(response);
    }
}
