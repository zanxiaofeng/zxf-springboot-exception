package zxf.springboot.service.a.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import zxf.springboot.support.message.MessageDispatcher;
import zxf.springboot.support.message.MessageHandler;
import zxf.springboot.support.message.MessageWrapper;

import java.util.List;
import java.util.Map;

@Component
public class MessageListener {
    @Autowired
    private MessageDispatcher messageDispatcher;

    @RabbitListener(queues = "service-a-queue")
    public void onMessage(MessageWrapper wrapper, @Headers Map<String, String> headers) {
        messageDispatcher.onMessage("service-a-queue", wrapper, headers);
    }

    @RabbitListener(queues = "service-a-queue_dlq")
    public void onErrorMessage(MessageWrapper wrapper, @Headers Map<String, String> headers) {
        messageDispatcher.onErrorMessage("service-a-queue_dlq", wrapper, headers);
    }
}
