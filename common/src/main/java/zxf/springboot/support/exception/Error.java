package zxf.springboot.support.exception;

public interface Error {
    String getCode();

    String getDescription();

    Error getCause();

    Error copy();
}
