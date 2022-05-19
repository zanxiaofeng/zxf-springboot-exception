package zxf.springboot.service.a.support.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zxf.springboot.service.a.support.exception.AErrorCodes;
import zxf.springboot.support.exception.ErrorCode;

@Configuration
public class ExceptionConfig {
    @Bean
    ErrorCode unexpectedErrorCode() {
        return AErrorCodes.A_ERR_SYS_000;
    }
}
