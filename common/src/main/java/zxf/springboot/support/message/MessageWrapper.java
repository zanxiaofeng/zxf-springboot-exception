package zxf.springboot.support.message;

import lombok.Data;

@Data
public class MessageWrapper {
    private MessageType type;
    private String message;
}
