package zxf.springboot.support.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageDispatcher {
    private final MessageHandlerProvider handlerProvider;

    public void onMessage(String queueName, MessageWrapper wrapper, @Headers Map<String, String> headers) {
        log.info("Receive message from {}, message = {}, header = {}", queueName, wrapper, headers);

        List<MessageHandler> handlers = handlerProvider.getHandlers(wrapper.getType());
        if (CollectionUtils.isEmpty(handlers)) {
            log.error("No corresponding handlers for message = {}", wrapper);
        } else {
            handlers.forEach(handler -> handler.handle(wrapper.getMessage()));
        }
    }

    public void onErrorMessage(String queueName, MessageWrapper wrapper, @Headers Map<String, String> headers) {
        log.info("Receive message from {}}, message = {}, header = {}", queueName, wrapper, headers);

        List<ErrorMessageHandler> handlers = handlerProvider.getErrorHandlers(wrapper.getType());
        if (CollectionUtils.isEmpty(handlers)) {
            log.error("No corresponding handlers for message = {}", wrapper);
        } else {
            handlers.forEach(handler -> handler.handle(wrapper.getMessage()));
        }
    }
}
