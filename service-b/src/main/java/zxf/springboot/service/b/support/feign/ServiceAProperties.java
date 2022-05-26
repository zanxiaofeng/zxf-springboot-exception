package zxf.springboot.service.b.support.feign;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import zxf.springboot.service.a.feign.IServiceAErrorHandler;
import zxf.springboot.service.b.support.exception.BErrorCodes;
import zxf.springboot.support.exception.BusinessException;
import zxf.springboot.support.feign.ClientResponse;

@Data
public class ServiceAProperties {
    private String username;
    private String password;
}
