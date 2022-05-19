package zxf.springboot.service.b.support.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import zxf.springboot.service.b.support.exception.BErrorCodes;
import zxf.springboot.support.exception.ErrorCode;

@Configuration
public class ExceptionConfig {
    @Bean
    ErrorCode unexpectedErrorCode() {
        return BErrorCodes.B_SYS_ERR_000;
    }
}
