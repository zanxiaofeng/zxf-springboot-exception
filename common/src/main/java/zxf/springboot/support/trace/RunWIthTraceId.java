package zxf.springboot.support.trace;

import org.slf4j.MDC;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static zxf.springboot.support.trace.TraceConstant.TRACE_ID;

public class RunWIthTraceId {

    public static Runnable wrap(Runnable runnable) {
        String traceId = MDC.get(TRACE_ID);
        return () -> {
            MDC.put(TRACE_ID, traceId);
            runnable.run();
        };
    }

    public static <T, R> Function<T, R> wrap(Function<T, R> function) {
        String traceId = MDC.get(TRACE_ID);
        return (T t) -> {
            MDC.put(TRACE_ID, traceId);
            return function.apply(t);
        };
    }

    public static <T> Consumer<T> wrap(Consumer<T> consumer) {
        String traceId = MDC.get(TRACE_ID);
        return (T t) -> {
            MDC.put(TRACE_ID, traceId);
            consumer.accept(t);
        };
    }

    public static <T> Supplier<T> wrap(Supplier<T> consumer) {
        String traceId = MDC.get(TRACE_ID);
        return () -> {
            MDC.put(TRACE_ID, traceId);
            return consumer.get();
        };
    }
}
