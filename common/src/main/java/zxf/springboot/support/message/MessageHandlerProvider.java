package zxf.springboot.support.message;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MessageHandlerProvider {
    private final List<MessageHandler> handlers;
    private final List<ErrorMessageHandler> errorHandlers;

    public List<MessageHandler> getHandlers(MessageType type) {
        return CollectionUtils.isEmpty(handlers) ? Collections.emptyList()
                : handlers.stream().filter(handler -> handler.canHandle(type)).collect(Collectors.toList());
    }

    public List<ErrorMessageHandler> getErrorHandlers(MessageType type) {
        return CollectionUtils.isEmpty(errorHandlers) ? Collections.emptyList()
                : errorHandlers.stream().filter(handler -> handler.canHandle(type)).collect(Collectors.toList());
    }
}
