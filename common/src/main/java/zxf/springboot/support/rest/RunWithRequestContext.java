package zxf.springboot.support.rest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class RunWithRequestContext {
    public static Runnable wrap(Runnable runnable) {
        RequestAttributes context = RequestContextHolder.getRequestAttributes();
        return () -> {
            try {
                RequestContextHolder.setRequestAttributes(context);
                runnable.run();
            } finally {
                RequestContextHolder.resetRequestAttributes();
            }
        };
    }

    public static <T, R> Function<T, R> wrap(Function<T, R> function) {
        RequestAttributes context = RequestContextHolder.getRequestAttributes();
        return (T t) -> {
            try {
                RequestContextHolder.setRequestAttributes(context);
                return function.apply(t);
            } finally {
                RequestContextHolder.resetRequestAttributes();
            }
        };
    }

    public static <T> Consumer<T> wrap(Consumer<T> consumer) {
        RequestAttributes context = RequestContextHolder.getRequestAttributes();
        return (T t) -> {
            try {
                RequestContextHolder.setRequestAttributes(context);
                consumer.accept(t);
            } finally {
                RequestContextHolder.resetRequestAttributes();
            }
        };
    }

    public static <T> Supplier<T> wrap(Supplier<T> consumer) {
        RequestAttributes context = RequestContextHolder.getRequestAttributes();
        return () -> {
            try {
                RequestContextHolder.setRequestAttributes(context);
                return consumer.get();
            } finally {
                RequestContextHolder.resetRequestAttributes();
            }
        };
    }
}
