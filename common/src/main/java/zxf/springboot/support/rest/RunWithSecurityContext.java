package zxf.springboot.support.rest;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class RunWithSecurityContext {
    public static Runnable wrap(Runnable runnable) {
        SecurityContext context = SecurityContextHolder.getContext();
        return () -> {
            try {
                SecurityContextHolder.setContext(context);
                runnable.run();
            } finally {
                SecurityContextHolder.clearContext();
            }
        };
    }

    public static <T, R> Function<T, R> wrap(Function<T, R> function) {
        SecurityContext context = SecurityContextHolder.getContext();
        return (T t) -> {
            try {
                SecurityContextHolder.setContext(context);
                return function.apply(t);
            } finally {
                SecurityContextHolder.clearContext();
            }
        };
    }

    public static <T> Consumer<T> wrap(Consumer<T> consumer) {
        SecurityContext context = SecurityContextHolder.getContext();
        return (T t) -> {
            try {
                SecurityContextHolder.setContext(context);
                consumer.accept(t);
            } finally {
                SecurityContextHolder.clearContext();
            }
        };
    }

    public static <T> Supplier<T> wrap(Supplier<T> consumer) {
        SecurityContext context = SecurityContextHolder.getContext();
        return () -> {
            try {
                SecurityContextHolder.setContext(context);
                return consumer.get();
            } finally {
                SecurityContextHolder.clearContext();
            }
        };
    }
}
