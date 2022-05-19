package zxf.springboot.support.message;

public interface ErrorMessageHandler {
    MessageType supportType();

    void handle(Object data);

    default boolean canHandle(MessageType type) {
        return supportType().equals(type);
    }
}
