package zxf.functional.core.checked;

@FunctionalInterface
public interface CheckedTriFunction<T, U, P, R> {
    R apply(T t, U u, P p) throws Exception;
}