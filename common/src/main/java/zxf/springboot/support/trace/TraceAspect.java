package zxf.springboot.support.trace;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static zxf.springboot.support.trace.TraceConstant.*;

@Slf4j
@Aspect
@Component
public class TraceAspect {

    @Around("execution(public * zxf.springboot.service.*.rest..*Controller.*(..))")
    public Object aroundNewRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        try {
            MDC.put(TRACE_ID, TraceIdGenerator.generateTraceId(WEB_REQUEST));
            log.info("===> New request {}, Parameters : {}", getSignature(joinPoint), Arrays.toString(joinPoint.getArgs()));
            return joinPoint.proceed();
        } finally {
            long end = System.currentTimeMillis();
            log.info("<=== End request {} in {}", getSignature(joinPoint), getTime(end - start));
            MDC.clear();
        }
    }

    private String getSignature(JoinPoint joinPoint) {
        return joinPoint.getSignature().toShortString();
    }

    private String getTime(long mills) {
        return mills <= MILLIS_PER_SECOND ? String.format("%s ms", mills)
                : String.format("%s s", mills / MILLIS_PER_SECOND);
    }
}