package zxf.springboot.service.c.support.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zxf.springboot.service.c.support.exception.SysErrorCodes;
import zxf.springboot.support.exception.SystemError;

@Configuration
public class ExceptionConfig {
    @Bean
    SystemError unexpectedErrorCode() {
        return SysErrorCodes.C_SYS_ERR_000;
    }
}
