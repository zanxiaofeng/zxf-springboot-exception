package zxf.functional.core.checked;

@FunctionalInterface
public interface CheckedConsumer<T> {
    void accept(T t) throws Exception;
}