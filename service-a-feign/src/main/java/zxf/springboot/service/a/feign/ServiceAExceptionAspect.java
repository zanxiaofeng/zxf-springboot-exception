package zxf.springboot.service.a.feign;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ServiceAExceptionAspect {
    @Autowired
    private IServiceAExceptionHandler serviceAExceptionHandler;

    @Around("execution(public * zxf.springboot.service.a.feign.ServiceAClient.*(..))")
    public Object httpRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Exception ex) {
            serviceAExceptionHandler.handleException(ex);
        }
        return null;
    }
}
