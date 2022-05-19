package zxf.springboot.service.a.support.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zxf.springboot.service.a.support.exception.AErrorCodes;
import zxf.springboot.support.exception.BusinessError;

@Configuration
public class ExceptionConfig {
    @Bean
    BusinessError unexpectedErrorCode() {
        return AErrorCodes.A_SYS_ERR_000;
    }
}
